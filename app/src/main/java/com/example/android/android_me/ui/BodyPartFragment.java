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

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

import java.util.List;

public class BodyPartFragment extends Fragment {

    private static final String TAG = BodyPartFragment.class.getSimpleName();
    // COMPLETED (1) Create a setter method and class variable to set and store of a list of image resources
    private List<Integer> mBodyPartList;

    public void setBodyPartList(List<Integer> bodyPartList) {
        mBodyPartList = bodyPartList;
    }

    // COMPLETED (2) Create another setter method and variable to track and set the index of the list item to display
        // ex. index = 0 is the first image id in the given list , index 1 is the second, and so on
    private int mBodyPartIndex = 0;

    public void setBodyPartIndex(int bodyPartIndex) {
        mBodyPartIndex = bodyPartIndex;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the fragment
     */
    public BodyPartFragment() {
    }

    /**
     * Inflates the fragment layout file and sets the correct resource for the image to display
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the Android-Me fragment layout
        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);

        // Get a reference to the ImageView in the fragment layout
        ImageView imageView = (ImageView) rootView.findViewById(R.id.body_part_image_view);

        // COMPLETED (3) If a list of image ids exists, set the image resource to the correct item in that list
        if (mBodyPartList != null) {
            if (mBodyPartList.size() > mBodyPartIndex) {
                Log.i(TAG, String.format("Setting image: %s -> %d", mBodyPartList, mBodyPartIndex));
                imageView.setImageResource(mBodyPartList.get(mBodyPartIndex));
            } else {
                // Set the image to the first in our list of head images
                imageView.setImageResource(mBodyPartList.get(0));
                Log.i(TAG, String.format("Image resource not found: %s (%d > %d)", mBodyPartList, mBodyPartIndex, mBodyPartList.size()));
            }
        } else {
            // Otherwise, create a Log statement that indicates that the list was not found
            Log.i(TAG, "Image list not found");
        }

        // Return the rootView
        return rootView;
    }

}
