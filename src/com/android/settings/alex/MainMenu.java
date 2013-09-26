package com.android.settings.alex;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;

import java.util.ArrayList;
import java.util.List;

public class MainMenu extends Activity {

    CustomPageAdapter pageAdapter;
    PagerTabStrip mPagerTabStrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alex_main_menu);

        List<SettingsPreferenceFragment> fragments = getFragments();

        pageAdapter = new CustomPageAdapter(getFragmentManager(), fragments);

        ViewPager pager = (ViewPager) findViewById(R.id.alex_custom_settings_viewpager);
        pager.setAdapter(pageAdapter);

        mPagerTabStrip = (PagerTabStrip) findViewById(R.id.alex_custom_settings_pagerTabStrip);
        mPagerTabStrip.setBackgroundColor(getResources().getColor(R.color.alex_light_gray));
        mPagerTabStrip.setTabIndicatorColor(getResources().getColor(R.color.alex_blue));
        mPagerTabStrip.setDrawFullUnderline(true);

    }

    private List<SettingsPreferenceFragment> getFragments() {
        List<SettingsPreferenceFragment> fList = new ArrayList<SettingsPreferenceFragment>();

        fList.add(new Halo());
        fList.add(new Graphics());

        return fList;
    }

    private List<String> getTitles() {
        List<String> titleList = new ArrayList<String>();

        titleList.add(getString(R.string.alex_halo_category_title));
        titleList.add(getString(R.string.alex_graphics_category_title));

        return titleList;
    }

    class CustomPageAdapter extends FragmentPagerAdapter {

        private List<SettingsPreferenceFragment> fragments;
        private List<String> titles = getTitles();

        public CustomPageAdapter(FragmentManager fm, List<SettingsPreferenceFragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return this.titles.get(position);
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
