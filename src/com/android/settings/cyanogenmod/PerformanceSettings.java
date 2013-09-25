/*
 * Copyright (C) 2012 The CyanogenMod Project
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

package com.android.settings.cyanogenmod;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;

/**
 * Performance Settings
 */
public class PerformanceSettings extends SettingsPreferenceFragment {
    private static final String TAG = "PerformanceSettings";

    private AlertDialog alertDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.performance_settings);

        /* Display the warning dialog */
        alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setTitle(R.string.performance_settings_warning_title);
        alertDialog.setMessage(getResources().getString(R.string.performance_settings_warning));
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,
                getResources().getString(com.android.internal.R.string.ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
        alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
                PerformanceSettings.this.finish();
            }
        });
        alertDialog.show();
    }
}
