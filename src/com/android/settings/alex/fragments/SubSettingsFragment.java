package com.android.settings.alex.fragments;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.android.settings.AccessibilitySettings;
import com.android.settings.DataUsageSummary;
import com.android.settings.DateTimeSettings;
import com.android.settings.DevelopmentSettings;
import com.android.settings.DeviceInfoSettings;
import com.android.settings.DisplaySettings;
import com.android.settings.LocationSettings;
import com.android.settings.PrivacySettings;
import com.android.settings.SecuritySettings;
import com.android.settings.SoundSettings;
import com.android.settings.WirelessSettings;
import com.android.settings.applications.ManageApplications;
import com.android.settings.bluetooth.BluetoothSettings;
import com.android.settings.cyanogenmod.ButtonSettings;
import com.android.settings.cyanogenmod.LockscreenInterface;
import com.android.settings.cyanogenmod.MoreDeviceSettings;
import com.android.settings.cyanogenmod.PerformanceSettings;
import com.android.settings.cyanogenmod.SystemUiSettings;
import com.android.settings.deviceinfo.Memory;
import com.android.settings.fuelgauge.PowerUsageSummary;
import com.android.settings.inputmethod.InputMethodAndLanguageSettings;
import com.android.settings.profiles.ProfilesSettings;
import com.android.settings.wifi.WifiSettings;

/**
 * Created by alex on 26.09.13.
 */
public class SubSettingsFragment extends PreferenceActivity {

    // wirelesssettings

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        String action = getIntent().getStringExtra("ID");

        /* Wireless */
        if (action.equals("wifisettings")) {
            getFragmentManager().beginTransaction().replace(android.R.id.content, new WifiSettings()).commit();
        } else if (action.equals("bluetoothsettings")) {
            getFragmentManager().beginTransaction().replace(android.R.id.content, new BluetoothSettings()).commit();
        } else if (action.equals("datausagesettings")) {
            getFragmentManager().beginTransaction().replace(android.R.id.content, new DataUsageSummary()).commit();
        } else if (action.equals("wirelesssettings")) {
            getFragmentManager().beginTransaction().replace(android.R.id.content, new WirelessSettings()).commit();
        }
        /* Interface */
        else if (action.equals("lockscreensettings")) {
            getFragmentManager().beginTransaction().replace(android.R.id.content, new LockscreenInterface()).commit();
        } else if (action.equals("systemsettings")) {
            getFragmentManager().beginTransaction().replace(android.R.id.content, new SystemUiSettings()).commit();
        }
        /* Device */
        else if (action.equals("soundsettings")) {
            getFragmentManager().beginTransaction().replace(android.R.id.content, new SoundSettings()).commit();
        } else if (action.equals("displaysettings")) {
            getFragmentManager().beginTransaction().replace(android.R.id.content, new DisplaySettings()).commit();
        } else if (action.equals("buttonsettings")) {
            getFragmentManager().beginTransaction().replace(android.R.id.content, new ButtonSettings()).commit();
        } else if (action.equals("storagesettings")) {
            getFragmentManager().beginTransaction().replace(android.R.id.content, new Memory()).commit();
        } else if (action.equals("batterysettings")) {
            getFragmentManager().beginTransaction().replace(android.R.id.content, new PowerUsageSummary()).commit();
        } else if (action.equals("applicationsettings")) {
            getFragmentManager().beginTransaction().replace(android.R.id.content, new ManageApplications()).commit();
        } else if (action.equals("moredevicesettings")) {
            getFragmentManager().beginTransaction().replace(android.R.id.content, new MoreDeviceSettings()).commit();
        }
        /* Personal */
        else if (action.equals("profilesettings")) {
            getFragmentManager().beginTransaction().replace(android.R.id.content, new ProfilesSettings()).commit();
        } else if (action.equals("locationsettings")) {
            getFragmentManager().beginTransaction().replace(android.R.id.content, new LocationSettings()).commit();
        } else if (action.equals("securitysettings")) {
            getFragmentManager().beginTransaction().replace(android.R.id.content, new SecuritySettings()).commit();
        } else if (action.equals("languagesettings")) {
            getFragmentManager().beginTransaction().replace(android.R.id.content, new InputMethodAndLanguageSettings()).commit();
        } else if (action.equals("privacysettings")) {
            getFragmentManager().beginTransaction().replace(android.R.id.content, new PrivacySettings()).commit();
        }
        /* Accounts */
        //=============================
        /* System */
        else if (action.equals("datetimesettings")) {
            getFragmentManager().beginTransaction().replace(android.R.id.content, new DateTimeSettings()).commit();
        } else if (action.equals("accessibilitysettings")) {
            getFragmentManager().beginTransaction().replace(android.R.id.content, new AccessibilitySettings()).commit();
        } else if (action.equals("developmentsettings")) {
            getFragmentManager().beginTransaction().replace(android.R.id.content, new DevelopmentSettings()).commit();
        } else if (action.equals("superusersettings")) {
            // dont handle
        } else if (action.equals("performancesettings")) {
            getFragmentManager().beginTransaction().replace(android.R.id.content, new PerformanceSettings()).commit();
        } else if (action.equals("aboutsettings")) {
            getFragmentManager().beginTransaction().replace(android.R.id.content, new DeviceInfoSettings()).commit();
        }
        /* Default */
        else if (action.equals("")) {
            // dont handle
        } else {
            // dont handle
        }
    }
}
