package com.android.settings.alex.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;

/**
 * Created by alex on 26.09.13.
 */
@SuppressWarnings("ALL")
public class DynamicSettingsFragment extends SettingsPreferenceFragment {

    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    public static final String PREF_FRAGMENT_NETWORK = "NETWORK";
    public static final String PREF_FRAGMENT_INTERFACE = "INTERFACE";
    public static final String PREF_FRAGMENT_DEVICE = "DEVICE";
    public static final String PREF_FRAGMENT_PERSONAL = "PERSONAL";
    public static final String PREF_FRAGMENT_ACCOUNT = "ACCOUNT";
    public static final String PREF_FRAGMENT_SYSTEM = "SYSTEM";
    public static final String PREF_FRAGMENT_ALEX = "ALEX";

    private PreferenceScreen prefSet;
    private Intent intent;


    private Context mContext;

    public static final DynamicSettingsFragment newInstance(String message) {
        DynamicSettingsFragment f = new DynamicSettingsFragment();
        Bundle bdl = new Bundle(1);
        bdl.putString(EXTRA_MESSAGE, message);
        f.setArguments(bdl);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String message = getArguments().getString(EXTRA_MESSAGE);
        mContext = getActivity();

        /* Add Preferences, depending on the EXTRA_MESSAGE*/
        if (message.equals(PREF_FRAGMENT_NETWORK)) {
            addPreferencesFromResource(R.xml.alex_fragments_settings_network);
        } else if (message.equals(PREF_FRAGMENT_INTERFACE)) {
            addPreferencesFromResource(R.xml.alex_fragments_settings_interface);
        } else if (message.equals(PREF_FRAGMENT_DEVICE)) {
            addPreferencesFromResource(R.xml.alex_fragments_settings_device);
        } else if (message.equals(PREF_FRAGMENT_PERSONAL)) {
            addPreferencesFromResource(R.xml.alex_fragments_settings_personal);
        } else if (message.equals(PREF_FRAGMENT_ACCOUNT)) {
            addPreferencesFromResource(R.xml.alex_fragments_settings_accounts);
        } else if (message.equals(PREF_FRAGMENT_SYSTEM)) {
            addPreferencesFromResource(R.xml.alex_fragments_settings_system);
        } else if (message.equals(PREF_FRAGMENT_ALEX)) {
            addPreferencesFromResource(R.xml.alex_fragments_settings_alex);
        }

        prefSet = getPreferenceScreen();

        if (message.equals(PREF_FRAGMENT_NETWORK)) {
            handleNetwork();
        } else if (message.equals(PREF_FRAGMENT_INTERFACE)) {
            handleInterface();
        } else if (message.equals(PREF_FRAGMENT_DEVICE)) {
            handleDevice();
        } else if (message.equals(PREF_FRAGMENT_PERSONAL)) {
            handlePersonal();
        } else if (message.equals(PREF_FRAGMENT_ACCOUNT)) {
            handleAccount();
        } else if (message.equals(PREF_FRAGMENT_SYSTEM)) {
            handleSystem();
        } else if (message.equals(PREF_FRAGMENT_ALEX)) {
            handleAlex();
        }


    }

    private void handleDevice() {
        // Empty
    }

    private void handlePersonal() {
        // Empty
    }

    private void handleAccount() {
        // Empty
    }

    private void handleSystem() {
        // Empty
    }

    private void handleAlex() {
        // Empty
    }

    private void handleNetwork() {
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_WIFI)) {
            prefSet.removePreference(findPreference("wireless_settings"));
        }
    }

    private void handleInterface() {
        /* Find default launcher and launch it, when pressing on the preference */
        Intent launcherIntent = new Intent(Intent.ACTION_MAIN);
        launcherIntent.addCategory(Intent.CATEGORY_HOME);
        launcherIntent.addCategory(Intent.CATEGORY_DEFAULT);

        Intent launcherPrefsIntent = new Intent(Intent.ACTION_MAIN);
        launcherPrefsIntent.addCategory("com.cyanogenmod.category.LAUNCHER_PREFERENCES");

        final PackageManager pm = getPackageManager();
        ActivityInfo defaultLauncher = pm.resolveActivity(launcherIntent,
                PackageManager.MATCH_DEFAULT_ONLY).activityInfo;

        launcherPrefsIntent.setPackage(defaultLauncher.packageName);
        ResolveInfo launcherPrefs = pm.resolveActivity(launcherPrefsIntent, 0);
        if (launcherPrefs != null) {
            prefSet.findPreference("alex_homescreen_settings").setIntent(new Intent().setClassName(
                    launcherPrefs.activityInfo.packageName,
                    launcherPrefs.activityInfo.name));
        }
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference
            preference) {
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }

}
