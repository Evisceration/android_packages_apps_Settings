package com.android.settings.alex;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemProperties;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.provider.Settings;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;

public class Graphics extends SettingsPreferenceFragment {

    private static final String KEY_ALEX_WALLPAPER_USE_DITHER		= "alex_wallpaper_use_dither";
    private static final String KEY_ALEX_WALLPAPER_PIXELFORMAT_565	= "alex_wallpaper_pixelformat_565";

    private static final String USE_16BPP_ALPHA_PREF 			= "pref_use_16bpp_alpha";
    private static final String USE_16BPP_ALPHA_PROP 			= "persist.sys.use_16bpp_alpha";

    private CheckBoxPreference mWallpaperDither;
    private CheckBoxPreference mWallpaperPixelformat565;
    private CheckBoxPreference mUse16bppAlphaPref;

    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.alex_graphics_settings);
        PreferenceScreen prefSet = getPreferenceScreen();
        mContext = getActivity();

        mWallpaperDither = (CheckBoxPreference) prefSet.findPreference(KEY_ALEX_WALLPAPER_USE_DITHER);
        mWallpaperDither.setChecked(Settings.System.getInt(mContext.getContentResolver(),
                Settings.System.ALEX_WALLPAPER_USE_DITHER, 0) == 1);

        mWallpaperPixelformat565 = (CheckBoxPreference) prefSet.findPreference(KEY_ALEX_WALLPAPER_PIXELFORMAT_565);
        mWallpaperPixelformat565.setChecked(Settings.System.getInt(mContext.getContentResolver(),
                Settings.System.ALEX_WALLPAPER_PIXELFORMAT_565, 0) == 1);

        mUse16bppAlphaPref = (CheckBoxPreference) prefSet.findPreference(USE_16BPP_ALPHA_PREF);
        String use16bppAlpha = SystemProperties.get(USE_16BPP_ALPHA_PROP, "0");
        mUse16bppAlphaPref.setChecked("1".equals(use16bppAlpha));

    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        if (preference == mWallpaperDither) {
            Settings.System.putInt(mContext.getContentResolver(),
                    Settings.System.ALEX_WALLPAPER_USE_DITHER, mWallpaperDither.isChecked()
                    ? 1 : 0);
        } else if (preference == mWallpaperPixelformat565) {
            Settings.System.putInt(mContext.getContentResolver(),
                    Settings.System.ALEX_WALLPAPER_PIXELFORMAT_565, mWallpaperPixelformat565.isChecked()
                    ? 1 : 0);
        } else if (preference == mUse16bppAlphaPref) {
            SystemProperties.set(USE_16BPP_ALPHA_PROP,
                    mUse16bppAlphaPref.isChecked() ? "1" : "0");
        } else {
            // If we didn't handle it, let preferences handle it.
            return super.onPreferenceTreeClick(preferenceScreen, preference);
        }
        return true;
    }

}
