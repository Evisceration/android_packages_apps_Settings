package com.android.settings.alex;

import android.content.Context;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.provider.Settings;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;

/**
 * Created by alex on 26.09.13.
 */
public class ExtraSettings extends SettingsPreferenceFragment {

    private static final String KEY_ALEX_EXTRA_USE_NEW_SETTINGS = "alex_extra_use_new_settings";

    private CheckBoxPreference mNewSettings;

    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.alex_extra_settings);
        PreferenceScreen prefSet = getPreferenceScreen();
        mContext = getActivity();

        mNewSettings = (CheckBoxPreference) prefSet.findPreference(KEY_ALEX_EXTRA_USE_NEW_SETTINGS);
        mNewSettings.setChecked(Settings.System.getInt(mContext.getContentResolver(),
                Settings.System.ALEX_EXTRA_USE_NEW_SETTINGS, 0) == 1);

    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        if (preference == mNewSettings) {
            Settings.System.putInt(mContext.getContentResolver(),
                    Settings.System.ALEX_EXTRA_USE_NEW_SETTINGS, mNewSettings.isChecked()
                    ? 1 : 0);
        } else {
            // If we didn't handle it, let preferences handle it.
            return super.onPreferenceTreeClick(preferenceScreen, preference);
        }
        return true;
    }

}
