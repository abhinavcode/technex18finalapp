package com.iitbhu.technex18.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;

import com.iitbhu.technex18.R;
import com.iitbhu.technex18.fragments.EventListFragment;
import com.iitbhu.technex18.utils.BoldModTextView;

public class EventListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private MaterialViewPager mViewPager;
    private Toolbar toolbar;
    private String ACTIVITY_NAME = "Events";
    private DrawerLayout mDrawerLayout;
    private ViewPager viewPager;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        setTitle("");

        int pos=getIntent().getIntExtra("POSITION",0);
        Log.d("POSITION",pos+"");

        mViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);
        viewPager=mViewPager.getViewPager();

        BoldModTextView titletext = (BoldModTextView)findViewById(R.id.logo_white);
        titletext.setText(ACTIVITY_NAME);

        toolbar = mViewPager.getToolbar();

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        mDrawerToggle = new ActionBarDrawerToggle (this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.setDrawerIndicatorEnabled(false);


        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                return EventListFragment.newInstance(position);
            }

            @Override
            public int getCount() {
                return 8;
            }


            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "Robonex";
                    case 1:
                        return "Ascension";
                    case 2:
                        return "Extreme Engineering";
                    case 3:
                        return "Supernova";
                    case 4:
                        return "Modex";
                    case 5:
                        return "Riqueza";
                    case 6:
                        return "Byte The Bits";
                    case 7:
                        return "Pahal";
                    default:
                        return "Byte The Bits";
                }
            }
        });

        //   toolbar.hideOverflowMenu();
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle mDrawerToggle;

        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();



        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page%9) {
                    case 0:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.fb_blue,
                                " ");
                    case 1:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.md_blue_500,
                                " ");
                    case 2:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.cyan,
                                " ");
                    case 3:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.md_teal_500,
                                " ");
                    case 4:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.md_green_500,
                                " ");
                    case 6:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.md_yellow_700,
                                " ");
                    case 7:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.md_deep_orange_600,
                                " ");
                    case 8:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.md_red_800,
                                " ");
                    case 9:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.md_pink_700,
                                " ");
//                    case 9:
//                        return HeaderDesign.fromColorResAndUrl(
//                                R.color.md_deep_purple_400,
//                                " ");
                }

                //execute others actions if needed (ex : modify your header logo)

                return null;
            }
        });

        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());

        viewPager.setCurrentItem(pos);


    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {

            case R.id.nav_about_us:
                //   Intent intent = new Intent(MainActivity.this, Developer.class);
                //  startActivity(intent);
                break;
//            case R.id.nav_feedback:
//                Intent emailIntent = new Intent(Intent.ACTION_SEND);
//                emailIntent.setData(Uri.parse("mailto:shriyansh.gautam.cse13@iitbhu.ac.in"));
//                emailIntent.setType("text/plain");
//                emailIntent.setClassName("com.google.android.gm", "com.google.android.gm.ConversationListActivity");
//                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"shriyansh.gautam.cse13@iitbhu.ac.in"});
//                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Technex 16 Android App");
//                emailIntent.putExtra(Intent.EXTRA_TEXT, "");
//                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
//                break;
            case R.id.nav_rate_us:
                Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + "com.shriyansh.technex"));
                startActivity(intent1);
                break;
            case R.id.nav_share_app:
                String url = "https://play.google.com/store/apps/details?id=com.shriyansh.technex";
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Share the magic. IIT BHU is #Celebrating Innovation. Download Technex 16 Android App Now " + url);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}