package com.android.settings.alex;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;

import java.util.ArrayList;
import java.util.List;

public class MainMenu extends Activity {

    CustomPageAdapter pageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alex_main_menu);

        List<SettingsPreferenceFragment> fragments = getFragments();

        pageAdapter = new CustomPageAdapter(getFragmentManager(), fragments);

        ViewPager pager = (ViewPager) findViewById(R.id.alex_custom_settings_viewpager);

        pager.setAdapter(pageAdapter);

    }

    private List<SettingsPreferenceFragment> getFragments() {
        List<SettingsPreferenceFragment> fList = new ArrayList<SettingsPreferenceFragment>();

        fList.add(new Halo());
        fList.add(new Graphics());

        return fList;
    }

    class CustomPageAdapter extends FragmentPagerAdapter {

        private List<SettingsPreferenceFragment> fragments;


        public CustomPageAdapter(FragmentManager fm, List<SettingsPreferenceFragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return this.fragments.get(position);
        }


        @Override
        public int getCount() {
            return this.fragments.size();
        }

    }

}
