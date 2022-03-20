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

import android.os.Bundle;
import android.widget.Toast;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar_map);

        final String currentBuildingId = getIntent().getStringExtra(BUILDING_DETAIL_TO_AR_MAP_CURRENT_BUILDING_ID);
        final String destinationId = getIntent().getStringExtra(BUILDING_DETAIL_TO_AR_MAP_DESTINATION_ID);

        Toast.makeText(this, currentBuildingId + destinationId, Toast.LENGTH_SHORT).show();

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
