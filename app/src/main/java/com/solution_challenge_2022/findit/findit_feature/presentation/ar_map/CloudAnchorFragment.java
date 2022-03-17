/*
 * Copyright 2019 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.solution_challenge_2022.findit.findit_feature.presentation.ar_map;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.solution_challenge_2022.helpers.helpers.FirebaseManager;
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
    // private final ObjectRenderer virtualObjectShadow = new ObjectRenderer();
    private final PlaneRenderer planeRenderer = new PlaneRenderer();
    private final PointCloudRenderer pointCloudRenderer = new PointCloudRenderer();
    // Temporary matrix allocated here to reduce number of allocations for each frame.
    private final float[] anchorMatrix = new float[16];
    private final float[] andyColor = {139.0f, 195.0f, 74.0f, 255.0f};
    // Rendering. The Renderers are created here, and initialized when the GL surface is created.
    private GLSurfaceView surfaceView;
    private Button resolveButton;
    private boolean installRequested;
    private Session session;
    private DisplayRotationHelper displayRotationHelper;
    private TrackingStateHelper trackingStateHelper;
    private TapHelper tapHelper;
    //  private final StorageManager storageManager = new StorageManager();
//    private FirebaseManager firebaseManager;
    @Nullable
    private Anchor currentAnchor = null;

    private ArrayList<Anchor> currentAnchorList = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<String> anchorIdList = new ArrayList<>();
    private String src, des;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        tapHelper = new TapHelper(context);
        trackingStateHelper = new TrackingStateHelper(requireActivity());
        src = "a4";
        des = "b4";
//        firebaseManager = new FirebaseManager(context);
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

            virtualObject.createOnGlThread(getContext(), "models/Locator.obj", "models/andy.png");
            virtualObject.setMaterialProperties(0.0f, 2.0f, 0.5f, 6.0f);

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
                for (Anchor anchor : currentAnchorList){
                    anchor.getPose().toMatrix(anchorMatrix,0);
                    virtualObject.updateModelMatrix(anchorMatrix, 1f);
                    //virtualObjectShadow.updateModelMatrix(anchorMatrix, 1f);

                    virtualObject.draw(viewmtx, projmtx, colorCorrectionRgba, andyColor);
                    //virtualObjectShadow.draw(viewmtx, projmtx, colorCorrectionRgba, andyColor);
                }
            }
        } catch (Throwable t) {
            // Avoid crashing the application due to unhandled exceptions.
            Log.e(TAG, "Exception on the OpenGL thread", t);
        }
    }

    // Handle only one tap per frame, as taps are usually low frequency compared to frame rate.
    private void handleTap(Frame frame, Camera camera) {
//        if (currentAnchor != null) {
//            return; // Do nothing if there was already an anchor.
//        }

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

                    // Adding an Anchor tells ARCore that it should track this position in
                    // space. This anchor is created on the Plane to place the 3D model
                    // in the correct position relative both to the world and to the plane.
                    currentAnchor = hit.createAnchor();
                    currentAnchorList.add(currentAnchor);

                    getActivity().runOnUiThread(() -> resolveButton.setEnabled(false));

                    messageSnackbarHelper.showMessage(getActivity(), "Now hosting anchor...");
                    cloudAnchorManager.hostCloudAnchor(session, currentAnchor, /* ttl= */ 300, this::onHostedAnchorAvailable);

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
    }

    private synchronized void onHostedAnchorAvailable(Anchor anchor) {
        Anchor.CloudAnchorState cloudState = anchor.getCloudAnchorState();
        if (cloudState == Anchor.CloudAnchorState.SUCCESS) {
            String cloudAnchorId = anchor.getCloudAnchorId();
            anchorIdList.add(cloudAnchorId);
            Map<String, Object> docData = new HashMap<>();
            docData.put("anchorIdList", anchorIdList);
//            docData.put("num", currentAnchorList.size());
            messageSnackbarHelper.showMessage(getActivity(), anchorIdList.size() + " Cloud Anchor are hosted." );

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

//            firebaseManager.nextShortCode(shortCode -> {
//                if (shortCode != null) {
//                    firebaseManager.storeUsingShortCode(shortCode, cloudAnchorId);
//                    messageSnackbarHelper.showMessage(getActivity(), "Cloud Anchor Hosted. Short code: " + shortCode);
//                } else {
//                    // Firebase could not provide a short code.
//                    messageSnackbarHelper.showMessage(getActivity(), "Cloud Anchor Hosted, but could not "
//                            + "get a short code from Firebase.");
//                }
//            });
//            currentAnchor = anchor;
        } else {
            messageSnackbarHelper.showMessage(getActivity(), "Error while hosting: " + cloudState.toString());
        }
    }

    private synchronized void onResolveButtonPressed() {
//        ResolveDialogFragment dialog = ResolveDialogFragment.createWithOkListener(
//                this::onShortCodeEntered);
//        dialog.show(getActivity().getSupportFragmentManager(), "Resolve");
        resolveButton.setEnabled(false);
        db.collection("campus").document("hcmut")
                .collection("arPath")
                .document(src + "-" + des)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            ArrayList<String> _anchorIdList = (ArrayList<String>)document.get("anchorIdList");
                            if (document.exists()) {
                                if (_anchorIdList != null) {
                                    for (String id : _anchorIdList){
                                        cloudAnchorManager.resolveCloudAnchor(
                                                session,
                                                id,
                                                anchor -> onResolvedAnchorAvailable(anchor));
                                    }
                                }
                                else {
                                    messageSnackbarHelper.showMessage(getActivity(),
                                            "No such anchor" );
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

//    private synchronized void onShortCodeEntered(int shortCode) {
//        firebaseManager.getCloudAnchorId(shortCode, cloudAnchorId -> {
//            if (cloudAnchorId == null || cloudAnchorId.isEmpty()) {
//                messageSnackbarHelper.showMessage(
//                        getActivity(),
//                        "A Cloud Anchor ID for the short code " + shortCode + " was not found.");
//                return;
//            }
//            resolveButton.setEnabled(false);
//            cloudAnchorManager.resolveCloudAnchor(
//                    session,
//                    cloudAnchorId,
//                    anchor -> onResolvedAnchorAvailable(anchor, shortCode));
//        });
//    }

//    private synchronized void onResolvedAnchorAvailable(Anchor anchor, int shortCode) {
//        Anchor.CloudAnchorState cloudState = anchor.getCloudAnchorState();
//        if (cloudState == Anchor.CloudAnchorState.SUCCESS) {
//            messageSnackbarHelper.showMessage(getActivity(), "Cloud Anchor Resolved. Short code: " + shortCode);
//            currentAnchor = anchor;
//            currentAnchorList.add(anchor);
//        } else {
//            messageSnackbarHelper.showMessage(
//                    getActivity(),
//                    "Error while resolving anchor with short code " + shortCode + ". Error: "
//                            + cloudState.toString());
//            resolveButton.setEnabled(true);
//        }
//    }
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
