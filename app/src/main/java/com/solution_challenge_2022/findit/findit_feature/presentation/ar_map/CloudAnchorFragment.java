package com.solution_challenge_2022.findit.findit_feature.presentation.ar_map;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.ar.core.Anchor;
import com.google.ar.core.ArCoreApk;
import com.google.ar.core.Camera;
import com.google.ar.core.Config;
import com.google.ar.core.Frame;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.core.Point;
import com.google.ar.core.Point.OrientationMode;
import com.google.ar.core.PointCloud;
import com.google.ar.core.Pose;
import com.google.ar.core.Session;
import com.google.ar.core.Trackable;
import com.google.ar.core.TrackingState;
import com.google.ar.core.exceptions.CameraNotAvailableException;
import com.google.ar.core.exceptions.UnavailableApkTooOldException;
import com.google.ar.core.exceptions.UnavailableArcoreNotInstalledException;
import com.google.ar.core.exceptions.UnavailableDeviceNotCompatibleException;
import com.google.ar.core.exceptions.UnavailableSdkTooOldException;
import com.google.ar.core.exceptions.UnavailableUserDeclinedInstallationException;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.solution_challenge_2022.findit.R;
import com.solution_challenge_2022.helpers.helpers.CameraPermissionHelper;
import com.solution_challenge_2022.helpers.helpers.CloudAnchorManager;
import com.solution_challenge_2022.helpers.helpers.DisplayRotationHelper;
import com.solution_challenge_2022.helpers.helpers.SnackbarHelper;
import com.solution_challenge_2022.helpers.helpers.TapHelper;
import com.solution_challenge_2022.helpers.helpers.TrackingStateHelper;
import com.solution_challenge_2022.helpers.rendering.BackgroundRenderer;
import com.solution_challenge_2022.helpers.rendering.ObjectRenderer;
import com.solution_challenge_2022.helpers.rendering.PlaneRenderer;
import com.solution_challenge_2022.helpers.rendering.PointCloudRenderer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Main Fragment for the Cloud Anchors Codelab.
 *
 * <p>This is where the AR Session and the Cloud Anchors are managed.
 */
public class CloudAnchorFragment extends Fragment implements GLSurfaceView.Renderer {

    private static final String TAG = CloudAnchorFragment.class.getSimpleName();
    private static final String SEARCHING_PLANE_MESSAGE = "Searching for surfaces...";
    private final SnackbarHelper messageSnackbarHelper = new SnackbarHelper();
    private final CloudAnchorManager cloudAnchorManager = new CloudAnchorManager();
    private final BackgroundRenderer backgroundRenderer = new BackgroundRenderer();
    private final ObjectRenderer virtualObject = new ObjectRenderer();
    private final ObjectRenderer virtualObjectStart = new ObjectRenderer();
    private final ObjectRenderer virtualObjectEnd = new ObjectRenderer();
    private final PlaneRenderer planeRenderer = new PlaneRenderer();
    private final PointCloudRenderer pointCloudRenderer = new PointCloudRenderer();
    // Temporary matrix allocated here to reduce number of allocations for each frame.
    private final float[] anchorMatrix = new float[16];
    private final float[] andyColor = {139.0f, 195.0f, 74.0f, 255.0f};
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<String> anchorIdList = new ArrayList<>();
    // Rendering. The Renderers are created here, and initialized when the GL surface is created.
    private GLSurfaceView surfaceView;
    private Button resolveButton;
    private AppCompatImageButton exitArMap;
    private boolean installRequested;
    private Session session;
    private DisplayRotationHelper displayRotationHelper;
    private TrackingStateHelper trackingStateHelper;
    private TapHelper tapHelper;

    @Nullable
    private Anchor currentAnchor = null;
    private ArrayList<Anchor> currentAnchorList = new ArrayList<>();
    private boolean isOverridingAvailable = false;
    private float totalDistance = 0;
    private int initPoint = 0; // Starting point
    private String src = "temp_src", des = "temp_des";


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        tapHelper = new TapHelper(context);
        trackingStateHelper = new TrackingStateHelper(requireActivity());
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate from the Layout XML file.
        View rootView = inflater.inflate(R.layout.cloud_anchor_fragment, container, false);
        GLSurfaceView surfaceView = rootView.findViewById(R.id.surfaceView);
        this.surfaceView = surfaceView;
        displayRotationHelper = new DisplayRotationHelper(requireContext());
        surfaceView.setOnTouchListener(tapHelper);

        surfaceView.setPreserveEGLContextOnPause(true);
        surfaceView.setEGLContextClientVersion(2);
        surfaceView.setEGLConfigChooser(8, 8, 8, 8, 16, 0); // Alpha used for plane blending.
        surfaceView.setRenderer(this);
        surfaceView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
        surfaceView.setWillNotDraw(false);

        Button clearButton = rootView.findViewById(R.id.clear_button);
        clearButton.setOnClickListener(v -> onClearButtonPressed());

        resolveButton = rootView.findViewById(R.id.resolve_button);
        resolveButton.setOnClickListener(v -> onResolveButtonPressed());
        resolveButton.setOnClickListener(v -> onResolveButtonPressed());

        exitArMap = rootView.findViewById(R.id.editArMap);
        exitArMap.setOnClickListener(v -> requireActivity().finish());

        // Get input data: currentBuildingId and destinationId
        assert getArguments() != null;
        src = getArguments().getString("currentBuildingId");
        des = getArguments().getString("destinationId");

        Toast.makeText(getContext(), "Connected to AR Map!", Toast.LENGTH_LONG).show();

        // Show AR tutorial
        AlertDialog.Builder alertadd = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), R.style.AlertDialogCustom));
        LayoutInflater factory = LayoutInflater.from(getActivity());
        final View view = factory.inflate(R.layout.ar_tutorial, null);
        alertadd.setView(view);
        alertadd.setNeutralButton("OK", (dlg, sumthin) -> dlg.dismiss());
        alertadd.show();

        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();

        if (session == null) {
            Exception exception = null;
            String message = null;
            try {
                switch (ArCoreApk.getInstance().requestInstall(requireActivity(), !installRequested)) {
                    case INSTALL_REQUESTED:
                        installRequested = true;
                        return;
                    case INSTALLED:
                        break;
                }

                // ARCore requires camera permissions to operate. If we did not yet obtain runtime
                // permission on Android M and above, now is a good time to ask the user for it.
                if (!CameraPermissionHelper.hasCameraPermission(requireActivity())) {
                    CameraPermissionHelper.requestCameraPermission(requireActivity());
                    return;
                }

                // Create the session.
                session = new Session(requireActivity());

                Config config = new Config(session);
                config.setCloudAnchorMode(Config.CloudAnchorMode.ENABLED);
                session.configure(config);

            } catch (UnavailableArcoreNotInstalledException
                    | UnavailableUserDeclinedInstallationException e) {
                message = "Please install ARCore";
                exception = e;
            } catch (UnavailableApkTooOldException e) {
                message = "Please update ARCore";
                exception = e;
            } catch (UnavailableSdkTooOldException e) {
                message = "Please update this app";
                exception = e;
            } catch (UnavailableDeviceNotCompatibleException e) {
                message = "This device does not support AR";
                exception = e;
            } catch (Exception e) {
                message = "Failed to create AR session";
                exception = e;
            }

            if (message != null) {
                messageSnackbarHelper.showError(requireActivity(), message);
                Log.e(TAG, "Exception creating session", exception);
                return;
            }
        }

        // Note that order matters - see the note in onPause(), the reverse applies here.
        try {
            session.resume();
        } catch (CameraNotAvailableException e) {
            messageSnackbarHelper
                    .showError(requireActivity(), "Camera not available. Try restarting the app.");
            session = null;
            return;
        }

        surfaceView.onResume();
        displayRotationHelper.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (session != null) {
            // Note that the order matters - GLSurfaceView is paused first so that it does not try
            // to query the session. If Session is paused before GLSurfaceView, GLSurfaceView may
            // still call session.update() and get a SessionPausedException.
            displayRotationHelper.onPause();
            surfaceView.onPause();
            session.pause();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] results) {
        if (!CameraPermissionHelper.hasCameraPermission(requireActivity())) {
            Toast.makeText(requireActivity(), "Camera permission is needed to run this application",
                    Toast.LENGTH_LONG)
                    .show();
            if (!CameraPermissionHelper.shouldShowRequestPermissionRationale(requireActivity())) {
                // Permission denied with checking "Do not ask again".
                CameraPermissionHelper.launchPermissionSettings(requireActivity());
            }
            requireActivity().finish();
        }
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0.1f, 0.1f, 0.1f, 1.0f);

        // Prepare the rendering objects. This involves reading shaders, so may throw an IOException.
        try {
            // Create the texture and pass it to ARCore session to be filled during update().
            backgroundRenderer.createOnGlThread(getContext());
            planeRenderer.createOnGlThread(getContext(), "models/trigrid.png");
            pointCloudRenderer.createOnGlThread(getContext());

            virtualObject.createOnGlThread(getContext(), "models/Locator.obj", "models/color_warp.png");
            virtualObject.setMaterialProperties(0.0f, 2.0f, 0.5f, 6.0f);

            virtualObjectStart.createOnGlThread(getContext(), "models/triceratops.obj", "models/color_warp.png");
            virtualObjectStart.setMaterialProperties(0.0f, 2.0f, 0.5f, 6.0f);

            virtualObjectEnd.createOnGlThread(getContext(), "models/triceratops.obj", "models/color_warp.png");
            virtualObjectEnd.setMaterialProperties(0.0f, 2.0f, 0.5f, 6.0f);

//            virtualObjectShadow
//                    .createOnGlThread(getContext(), "models/andy_shadow.obj", "models/andy_shadow.png");
//            virtualObjectShadow.setBlendMode(ObjectRenderer.BlendMode.Shadow);
//            virtualObjectShadow.setMaterialProperties(1.0f, 0.0f, 0.0f, 1.0f);

        } catch (IOException e) {
            Log.e(TAG, "Failed to read an asset file", e);
        }
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        displayRotationHelper.onSurfaceChanged(width, height);
        GLES20.glViewport(0, 0, width, height);
    }

    public float distance2Points(float[] A, float[]  B){
        return (float) Math.sqrt(Math.pow(A[0] - B[0], 2) +
                Math.pow(A[1] - B[1], 2) +
                Math.pow(A[2] - B[2], 2));
    }
    @Override
    public void onDrawFrame(GL10 gl) {
        // Clear screen to notify driver it should not load any pixels from previous frame.
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        if (session == null) {
            return;
        }
        // Notify ARCore session that the view size changed so that the perspective matrix and
        // the video background can be properly adjusted.
        displayRotationHelper.updateSessionIfNeeded(session);

        try {
            session.setCameraTextureName(backgroundRenderer.getTextureId());

            // Obtain the current frame from ARSession. When the configuration is set to
            // UpdateMode.BLOCKING (it is by default), this will throttle the rendering to the
            // camera framerate.
            Frame frame = session.update();
            cloudAnchorManager.onUpdate();

            Camera camera = frame.getCamera();

            // Handle one tap per frame.
            handleTap(frame, camera);

            // If frame is ready, render camera preview image to the GL surface.
            backgroundRenderer.draw(frame);

            // Keep the screen unlocked while tracking, but allow it to lock when tracking stops.
            trackingStateHelper.updateKeepScreenOnFlag(camera.getTrackingState());

            // If not tracking, don't draw 3D objects, show tracking failure reason instead.
            if (camera.getTrackingState() == TrackingState.PAUSED) {
                messageSnackbarHelper.showMessage(
                        getActivity(), TrackingStateHelper.getTrackingFailureReasonString(camera));
                return;
            }

            // Get projection matrix.
            float[] projmtx = new float[16];
            camera.getProjectionMatrix(projmtx, 0, 0.1f, 100.0f);

            // Get camera matrix and draw.
            float[] viewmtx = new float[16];
            camera.getViewMatrix(viewmtx, 0);

            // Compute lighting from average intensity of the image.
            // The first three components are color scaling factors.
            // The last one is the average pixel intensity in gamma space.
            final float[] colorCorrectionRgba = new float[4];
            frame.getLightEstimate().getColorCorrection(colorCorrectionRgba, 0);

            // Visualize tracked points.
            // Use try-with-resources to automatically release the point cloud.
            try (PointCloud pointCloud = frame.acquirePointCloud()) {
                pointCloudRenderer.update(pointCloud);
                pointCloudRenderer.draw(viewmtx, projmtx);
            }

            // No tracking error at this point. If we didn't detect any plane, show searchingPlane message.
            if (!hasTrackingPlane()) {
                messageSnackbarHelper.showMessage(getActivity(), SEARCHING_PLANE_MESSAGE);
            }

            // Visualize planes.
            planeRenderer.drawPlanes(
                    session.getAllTrackables(Plane.class), camera.getDisplayOrientedPose(), projmtx);

            if (!currentAnchorList.isEmpty() && currentAnchorList.get(0).getTrackingState() == TrackingState.TRACKING) {
//                Log.d(TAG, "ALU Camera distance: " + hit.getDistance());
//                Log.d(TAG, "ALU Translation x, y, z: " + Arrays.toString(hit.getHitPose().getTranslation()));
//                Log.d(TAG, "ALU Rotation x, y, z: " + Arrays.toString(hit.getHitPose().getRotationQuaternion()));
//                Log.d(TAG, "ALU camera pose: " + camera.getDisplayOrientedPose());

                Anchor currAnchor = currentAnchorList.get(initPoint);
                float anchor2camera = distance2Points(currAnchor.getPose().getTranslation(), camera.getDisplayOrientedPose().getTranslation());
                float nextAnchor2camera = Float.POSITIVE_INFINITY;
                float prevAnchor2camera = Float.POSITIVE_INFINITY;

                if (initPoint < currentAnchorList.size() - 1) {
                    Anchor nextAnchor = currentAnchorList.get(initPoint + 1);
                    nextAnchor2camera = distance2Points(nextAnchor.getPose().getTranslation(), camera.getDisplayOrientedPose().getTranslation());
                }
                if (initPoint > 0) {
                    Anchor prevAnchor = currentAnchorList.get(initPoint - 1);
                    prevAnchor2camera = distance2Points(prevAnchor.getPose().getTranslation(), camera.getDisplayOrientedPose().getTranslation());
                }
                float min_distance = Math.min(anchor2camera,  Math.min(nextAnchor2camera, prevAnchor2camera));
                messageSnackbarHelper.showMessage(getActivity(),  initPoint + ":" + prevAnchor2camera + ":" + anchor2camera + ":" + nextAnchor2camera);
                if (Math.abs(min_distance - anchor2camera) < 10e-5){
                    totalDistance = anchor2camera;
//                    messageSnackbarHelper.showMessage(getActivity(), "anchor2camera " + "m, initpoint:" + initPoint);
                    Log.d(TAG, "RRRRRR anchor2camera "+  "initpoint:" + initPoint);
                }
                else if (Math.abs(min_distance - prevAnchor2camera) < 10e-5){
                    totalDistance = prevAnchor2camera;
                    initPoint--;
//                    messageSnackbarHelper.showMessage(getActivity(), "prevAnchor2camera " + "m, initpoint:" + initPoint);
                    Log.d(TAG, "RRRRRR prevAnchor2camera "+  "initpoint:" + initPoint);
                }
                else {
                    totalDistance = nextAnchor2camera;
                    initPoint++;
//                    messageSnackbarHelper.showMessage(getActivity(), "nextAnchor2camera " + "m, initpoint:" + initPoint);
                    Log.d(TAG, "RRRRRR nextAnchor2camera "+  "initpoint:" + initPoint);
                }
                //camera update here
                // table of distance need to store

                for (int i = 0; i < currentAnchorList.size(); i++) {
                    Anchor anchor = currentAnchorList.get(i);
                    // TODO: compute length from initPoint
                    if ((i < currentAnchorList.size() - 1) && (i >= initPoint)) {
                        Anchor nextAnchor = currentAnchorList.get(i + 1);
                        totalDistance += distance2Points(anchor.getPose().getTranslation(), nextAnchor.getPose().getTranslation());
                    }
                    anchor.getPose().toMatrix(anchorMatrix, 0);
                    virtualObject.updateModelMatrix(anchorMatrix, 1f);
                    //virtualObjectShadow.updateModelMatrix(anchorMatrix, 1f);
                    final float[] locatorColor = {245.0f, 39.0f, 39.0f, 230.0f};
                    virtualObject.draw(viewmtx, projmtx, colorCorrectionRgba, locatorColor);// andyColor);
                }
            }
        } catch (Throwable t) {
            // Avoid crashing the application due to unhandled exceptions.
            Log.e(TAG, "Exception on the OpenGL thread", t);
        }
    }
    private boolean isExisted = false;
    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    //Yes button clicked
                    isOverridingAvailable = true;
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    //No button clicked
                    isOverridingAvailable = false;
                    break;
            }
        }
    };

    // Handle only one tap per frame, as taps are usually low frequency compared to frame rate.
    //TODO: DO not allow to tap resolving
    private void handleTap(Frame frame, Camera camera) {
        MotionEvent tap = tapHelper.poll();
        if (tap != null && camera.getTrackingState() == TrackingState.TRACKING) {
            for (HitResult hit : frame.hitTest(tap)) {
                // Check if any plane was hit, and if it was hit inside the plane polygon
                Trackable trackable = hit.getTrackable();
                // Creates an anchor if a plane or an oriented point was hit.
                if ((trackable instanceof Plane
                        && ((Plane) trackable).isPoseInPolygon(hit.getHitPose())
                        && (PlaneRenderer.calculateDistanceToPlane(hit.getHitPose(), camera.getPose()) > 0))
                        || (trackable instanceof Point
                        && ((Point) trackable).getOrientationMode()
                        == OrientationMode.ESTIMATED_SURFACE_NORMAL)) {
                    // Hits are sorted by depth. Consider only closest hit on a plane or oriented point.

                    readData(anchorIsExisted -> {
                        Log.e(TAG, "onCallBack anchorIdExist = " + anchorIsExisted);
//                        if (isAddAnchorAvailable){
                        if (isOverridingAvailable){
                            // Adding an Anchor tells ARCore that it should track this position in
                            // space. This anchor is created on the Plane to place the 3D model
                            // in the correct position relative both to the world and to the plane.
                            currentAnchor = hit.createAnchor();
                            currentAnchorList.add(currentAnchor);
                            // TODO: update distance table whenever create new anchor (store on Database)
//                            if (currentAnchorList.size() == 0){
//                                totalDistance += distance2Points(currentAnchor.getPose().getTranslation(), camera.getDisplayOrientedPose().getTranslation());
//                            }
//                            else{
//                                Anchor prevAnchor = currentAnchorList.get(currentAnchorList.size() - 1);
//                                currentAnchorList.add(currentAnchor);
//                                totalDistance += distance2Points(prevAnchor.getPose().getTranslation(), currentAnchor.getPose().getTranslation());
//                            }

                            getActivity().runOnUiThread(() -> resolveButton.setEnabled(false));

                            messageSnackbarHelper.showMessage(getActivity(), "Now hosting anchor...");
                            cloudAnchorManager.hostCloudAnchor(session, currentAnchor,
                                    /* ttl= */ 300, this::onHostedAnchorAvailable);
                        }
                        else {
                            if (anchorIsExisted) {
                                getActivity().runOnUiThread(() -> {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                    builder.setMessage("The Path from " + src + " to " + des
                                        + " is already existed! Do you want to override it?")
                                        .setPositiveButton("Yes", dialogClickListener)
                                        .setNegativeButton("No", dialogClickListener).show();
                                    }
                                );
                            }
                        }
                    });
                    break;
                }
            }
        }
    }


    /**
     * Checks if we detected at least one plane.
     */
    private boolean hasTrackingPlane() {
        for (Plane plane : session.getAllTrackables(Plane.class)) {
            if (plane.getTrackingState() == TrackingState.TRACKING) {
                return true;
            }
        }
        return false;
    }

    private synchronized void onClearButtonPressed() {
        cloudAnchorManager.clearListeners();
        resolveButton.setEnabled(true);
        // Clear the anchor from the scene.
        currentAnchor = null;
        currentAnchorList.clear();
        anchorIdList.clear();
        isOverridingAvailable = false;
        totalDistance = 0;
        initPoint = 0;
    }

    private synchronized void onHostedAnchorAvailable(@NonNull Anchor anchor) {
        Anchor.CloudAnchorState cloudState = anchor.getCloudAnchorState();
        if (cloudState == Anchor.CloudAnchorState.SUCCESS) {
            String cloudAnchorId = anchor.getCloudAnchorId();
            anchorIdList.add(cloudAnchorId);
            Map<String, Object> docData = new HashMap<>();
            docData.put("anchorIdList", anchorIdList);
//            docData.put("num", currentAnchorList.size());
            messageSnackbarHelper.showMessage(getActivity(), anchorIdList.size() + " Cloud Anchor are hosted.");

            db.collection("campus").document("hcmut")
                    .collection("arPath")
                    .document(src + "-" + des)
                    .set(docData, SetOptions.merge())
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "DocumentSnapshot successfully written!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error writing document", e);
                        }
                    });
        } else {
            messageSnackbarHelper.showMessage(getActivity(), "Error while hosting: " + cloudState.toString());
        }
    }

    private void readData(FirestoreCallBack firestoreCallBack){
        boolean[] anchorExisted = {false};
        db.collection("campus").document("hcmut")
                .collection("arPath")
                .document(src + "-" + des)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    ArrayList<String> _anchorIdList =
                            (ArrayList<String>) document.get("anchorIdList");
                    if (document.exists()) {
                        if (_anchorIdList != null && _anchorIdList.size()>0) {
                            messageSnackbarHelper.showMessage(getActivity(),
                                    "Anchors are already existed");
                            anchorExisted[0] = true;
                            Log.e(TAG, "inside anchorIdExist = " + String.valueOf(anchorExisted[0]));

                        } else {
                            messageSnackbarHelper.showMessage(getActivity(),
                                    "Path from " + src + "to" + des +
                                            " is created but no such Anchor id");
                        }
                        Log.e(TAG, "DocumentSnapshot data: " + document.getData());
                    } else {
                        messageSnackbarHelper.showMessage(getActivity(),
                                "No such path is created");
                        Log.e(TAG, "No such document");
                    }
                    firestoreCallBack.onCallBack(anchorExisted[0]);
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }

    private interface FirestoreCallBack{
        void onCallBack(boolean anchorIsExisted);
    }

    private synchronized void onResolveButtonPressed() {
        resolveButton.setEnabled(false);
        initPoint = 0;
        db.collection("campus").document("hcmut")
                .collection("arPath")
                .document(src + "-" + des)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    ArrayList<String> _anchorIdList = (ArrayList<String>) document.get("anchorIdList");
                    if (document.exists()) {
                        if (_anchorIdList != null) {
                            for (String id : _anchorIdList) {
                                cloudAnchorManager.resolveCloudAnchor(
                                        session,
                                        id,
                                        anchor -> onResolvedAnchorAvailable(anchor));
                            }
                        } else {
                            messageSnackbarHelper.showMessage(getActivity(),
                                    "No such anchor");
                        }
                        Log.e(TAG, "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.e(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

    }

    private synchronized void onResolvedAnchorAvailable(Anchor anchor) {
        Anchor.CloudAnchorState cloudState = anchor.getCloudAnchorState();
        if (cloudState == Anchor.CloudAnchorState.SUCCESS) {
            messageSnackbarHelper.showMessage(getActivity(), "Cloud Anchor Resolved.");
            currentAnchor = anchor;
            currentAnchorList.add(anchor);
        } else {
            messageSnackbarHelper.showMessage(
                    getActivity(),
                    "Error while resolving anchor. Error: "
                            + cloudState.toString());
            resolveButton.setEnabled(true);
        }
    }
}