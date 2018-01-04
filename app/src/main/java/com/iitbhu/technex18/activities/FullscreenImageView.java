package com.iitbhu.technex18.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.iitbhu.technex18.R;
import com.iitbhu.technex18.fragments.FullscreenImageFragment;

import java.util.ArrayList;

public class FullscreenImageView extends AppCompatActivity {

    private ViewPager page;
    ArrayList<Integer> res; int position;
//    boolean oncreatestat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_image_view);

        if (savedInstanceState == null) {

//            oncreatestat = true;


            res = getIntent().getIntegerArrayListExtra("Resources");
            position = getIntent().getIntExtra("Position", 0);

        } else {
            res = savedInstanceState.getIntegerArrayList("res");
            position = savedInstanceState.getInt("pos");
        }
        PagerAdapter pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        page = (ViewPager) findViewById(R.id.pager);
        page.setAdapter(pagerAdapter);
        page.setCurrentItem(position);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int mposition) {
            FullscreenImageFragment fragment = new FullscreenImageFragment();
            fragment.setAsset(res.get(mposition));
            return fragment;
        }

        @Override
        public int getCount() {
            return res.size();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState) {
        saveInstanceState.putIntegerArrayList("res", res);
        saveInstanceState.putInt("pos", position);
        super.onSaveInstanceState(saveInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}