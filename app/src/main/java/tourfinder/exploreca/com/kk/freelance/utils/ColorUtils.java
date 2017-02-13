/*
 * Copyright (C) 2016 The Android Open Source Project
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
package tourfinder.exploreca.com.kk.freelance.utils;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import tourfinder.exploreca.com.kk.freelance.R;

/**
 * ColorUtils is a class with one method, used to color the ViewHolders in
 * the RecyclerView. I put in a separate class in an attempt to keep the
 * code organized.
 *
 * We aren't going to go into detail about how this method works, but feel
 * free to explore!
 */
public class ColorUtils {
    static final String LOG_TAG = ColorUtils.class.getSimpleName();
    /**
     * This method returns the appropriate shade of green to form the gradient
     * seen in the list, based off of the order in which the
     * {@link tourfinder.exploreca.com.kk.TourListRVAdapter.TourListViewHolder}
     * instance was created.
     *
     * This method is used to show how ViewHolders are recycled in a RecyclerView.
     * At first, the colors will form a nice, consistent gradient. As the
     * RecyclerView is scrolled, the
     * {@link tourfinder.exploreca.com.kk.TourListRVAdapter.TourListViewHolder}'s will be
     * recycled and the list will no longer appear as a consistent gradient.
     *
     * @param context     Context for getting colors
     * @param instanceNum Order in which the calling ViewHolder was created
     *
     * @return A shade of green based off of when the calling ViewHolder
     * was created.
     */
    public static int getViewHolderBackgroundColorFromInstance(Context context, int instanceNum) {
        Log.i(LOG_TAG, "TEST: getViewHolderBackgroundColorFromInstance() called...");
        switch (instanceNum) {
            case 0:
                return ContextCompat.getColor(context, R.color.material50Skin);
            case 1:
                return ContextCompat.getColor(context, R.color.material100Skin);
            case 2:
                return ContextCompat.getColor(context, R.color.material150Skin);
            case 3:
                return ContextCompat.getColor(context, R.color.material200Skin);
            case 4:
                return ContextCompat.getColor(context, R.color.material250Skin);
            case 5:
                return ContextCompat.getColor(context, R.color.material300Skin);
            case 6:
                return ContextCompat.getColor(context, R.color.material350Skin);
            case 7:
                return ContextCompat.getColor(context, R.color.material400Skin);
            case 8:
                return ContextCompat.getColor(context, R.color.material450Skin);
            case 9:
                return ContextCompat.getColor(context, R.color.material500Skin);
            case 10:
                return ContextCompat.getColor(context, R.color.material550Skin);
            case 11:
                return ContextCompat.getColor(context, R.color.material600Skin);

            default:
                return Color.WHITE;
        }
    }
}
