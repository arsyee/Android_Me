/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

// This activity is responsible for displaying the master list of all images
// Implement the MasterListFragment callback, OnImageClickListener
public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener{
    private static final String TAG = MainActivity.class.getSimpleName();

    public static final String KEY_HEAD = "head";
    public static final String KEY_BODY = "body";
    public static final String KEY_LEG = "leg";

    private int head = 0;
    private int body = 0;
    private int leg = 0;

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt(KEY_LEG, leg);
        outState.putInt(KEY_BODY, body);
        outState.putInt(KEY_HEAD, head);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(KEY_HEAD)) {
                head = savedInstanceState.getInt(KEY_HEAD);
            }
            if (savedInstanceState.containsKey(KEY_BODY)) {
                body = savedInstanceState.getInt(KEY_BODY);
            }
            if (savedInstanceState.containsKey(KEY_LEG)) {
                leg = savedInstanceState.getInt(KEY_LEG);
            }
        }

        // COMPLETED (4) Get a reference to the "Next" button and launch the intent when this button is clicked
        ((Button) findViewById(R.id.bt_next)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AndroidMeActivity.class);
                intent.putExtra(KEY_BODY, body);
                intent.putExtra(KEY_HEAD, head);
                intent.putExtra(KEY_LEG, leg);
                startActivity(intent);
                Log.d(TAG, String.format("Starting %s with %d %d %d", AndroidMeActivity.class.getSimpleName(), head, body, leg));
            }
        });
    }

    // Define the behavior for onImageSelected
    public void onImageSelected(int position) {
        // Create a Toast that displays the position that was clicked
        Toast.makeText(this, "Position clicked = " + position, Toast.LENGTH_SHORT).show();

        // COMPLETED (2) Based on where a user has clicked, store the selected list index for the head, body, and leg BodyPartFragments
        if (position < AndroidImageAssets.getHeads().size()) {
            head = position;
        } else {
            position -= AndroidImageAssets.getHeads().size();
            if (position < AndroidImageAssets.getBodies().size()) {
                body = position;
            } else {
                leg = position - AndroidImageAssets.getBodies().size();
            }
        }

        // DONTCARE (3) Put this information in a Bundle and attach it to an Intent that will launch an AndroidMeActivity
    }

}
