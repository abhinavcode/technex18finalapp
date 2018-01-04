package com.iitbhu.technex18.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;

import com.iitbhu.technex18.R;
import com.iitbhu.technex18.fragments.EventFragment;
import com.iitbhu.technex18.fragments.HomeFragment;
import com.iitbhu.technex18.fragments.NewsFragment;
import com.iitbhu.technex18.fragments.UserFragment;
import com.iitbhu.technex18.utils.BoldModTextView;
import com.iitbhu.technex18.utils.Constants;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, Constants {

    private int pos, back_count = 0;
    ImageButton home, news, user, events;
    private MaterialViewPager mViewPager;
    private Toolbar toolbar;
    private String ACTIVITY_NAME = "Technex '18";

    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;

    SharedPreferences myprefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setTitle("");

        mViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);

        myprefs = PreferenceManager.getDefaultSharedPreferences(this);

        BoldModTextView titletext = (BoldModTextView)findViewById(R.id.logo_white);
        titletext.setText(ACTIVITY_NAME);

        toolbar = mViewPager.getToolbar();

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                switch (position % 4) {
                    case 0:
                        return HomeFragment.newInstance();
                    case 1:
                        return NewsFragment.newInstance();
                    case 2:
                        return EventFragment.newInstance();
                    default:
                        return UserFragment.newInstance();
                }
            }

            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position % 4) {
                    case 0:
                        return "HOME";
                    case 1:
                        return "NEWS";
                    case 2:
                        return "EVENTS";
                    case 3:
                        return "USER";
                }
                return "";
            }
        });

        //   toolbar.hideOverflowMenu();
//        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle mDrawerToggle;

        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);

            View headerView = navigationView.getHeaderView(0);

            TextView tvHeaderName = (TextView)headerView.findViewById(R.id.tv_header_name);
            TextView tvHeaderEmail = (TextView)headerView.findViewById(R.id.tv_header_email);

            tvHeaderName.setText(myprefs.getString(NAME, "Technex User"));
            tvHeaderEmail.setText(myprefs.getString(EMAIL, "user@technex.in"));
        }

        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.logoColor,
                                " ");
                    case 1:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.blue,
                                " ");
                    case 2:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.teal,
                                " ");
                    case 3:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.cyan,
                                " ");
                }

                //execute others actions if needed (ex : modify your header logo)

                return null;
            }
        });

        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());

        View logo = findViewById(R.id.logo_white);
        if (logo != null) {
            logo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.notifyHeaderChanged();
//                    Toast.makeText(getApplicationContext(), "Yes, the title is clickable", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    @Override
    public void onBackPressed() {

//      TODO Close drawer if open

        if (this.drawer.isDrawerOpen(GravityCompat.START)) {
            this.drawer.closeDrawer(GravityCompat.START);
        } else if (mViewPager.getViewPager().getCurrentItem() != 0) {
            mViewPager.getViewPager().setCurrentItem(0);
        } else if (back_count == 0) {
            back_count = 1;
            View view;
            view = findViewById(R.id.drawer_layout);
            Snackbar.make(view, "Tap back again to exit", Snackbar.LENGTH_LONG).show();
            Thread tim = new Thread() {
                public void run() {
                    try {
                        sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        back_count = 0;
                    }
                }
            };
            tim.start();

        } else {
            finish();
        }

    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {

            case R.id.nav_about_us:
                Intent intent = new Intent(HomeActivity.this, AboutActivity.class);
                startActivity(intent);
                break;
//            case R.id.nav_feedback:
//                Intent emailIntent = new Intent(Intent.ACTION_SEND);
//                emailIntent.setData(Uri.parse("mailto:soumyadeep.das.phy14@iitbhu.ac.in"));
//                emailIntent.setType("text/plain");
//                emailIntent.setClassName("com.google.android.gm", "com.google.android.gm.ConversationListActivity");
//                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"soumyadeep.das.phy14@iitbhu.ac.in"});
//                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Technex'17 Android App");
//                emailIntent.putExtra(Intent.EXTRA_TEXT, "");
//                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
//                break;
            case R.id.nav_rate_us:
                Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + "com.iitbhu.technex18"));
                startActivity(intent1);
                break;
            case R.id.nav_share_app:
                String url = "https://play.google.com/store/apps/details?id=com.iitbhu.technex18";
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Technex'17 - Towards Sustainability. Download Technex'17 Android App Now from " + url);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;

        }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
    }
}