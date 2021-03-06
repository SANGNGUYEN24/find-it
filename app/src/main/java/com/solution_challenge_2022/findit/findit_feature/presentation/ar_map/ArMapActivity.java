package com.solution_challenge_2022.findit.findit_feature.presentation.ar_map;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.solution_challenge_2022.findit.R;
import com.solution_challenge_2022.helpers.helpers.FullScreenHelper;


/**
 * Main Activity for the Cloud Anchors Codelab.
 *
 * <p>The bulk of the logic resides in the {@link CloudAnchorFragment}. The activity only creates
 * the fragment and attaches it to this Activity.
 */
public class ArMapActivity extends AppCompatActivity {
    final String BUILDING_DETAIL_TO_AR_MAP_CURRENT_BUILDING_ID = "building_detail_to_ar_map_current_building_id";
    final String BUILDING_DETAIL_TO_AR_MAP_DESTINATION_ID = "building_detail_to_ar_map_destination_id";

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        overridePendingTransition(
                com.google.android.material.R.anim.abc_fade_in,
                com.google.android.material.R.anim.abc_fade_out
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        overridePendingTransition(
                com.google.android.material.R.anim.abc_fade_in,
                com.google.android.material.R.anim.abc_fade_out
        );

        setContentView(R.layout.activity_ar_map);

        final String currentBuildingId = getIntent().getStringExtra(BUILDING_DETAIL_TO_AR_MAP_CURRENT_BUILDING_ID);
        final String destinationId = getIntent().getStringExtra(BUILDING_DETAIL_TO_AR_MAP_DESTINATION_ID);

        Bundle sendDataToArFragment = new Bundle();
        sendDataToArFragment.putString("currentBuildingId", currentBuildingId);
        sendDataToArFragment.putString("destinationId", destinationId);

        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.fragment_container);
        if (frag == null) {
            frag = new CloudAnchorFragment();
            frag.setArguments(sendDataToArFragment);
            fm.beginTransaction().add(R.id.fragment_container, frag).commit();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        FullScreenHelper.setFullScreenOnWindowFocusChanged(this, hasFocus);
    }

}
