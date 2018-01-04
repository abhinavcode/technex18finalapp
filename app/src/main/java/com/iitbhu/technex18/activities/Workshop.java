package com.iitbhu.technex18.activities;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;

import com.iitbhu.technex18.R;
import com.iitbhu.technex18.database.DbConstants;
import com.iitbhu.technex18.database.DbMethods;
import com.iitbhu.technex18.fragments.WorkshopDetailFragment;
import com.iitbhu.technex18.fragments.WorkshopListFragment;
import com.iitbhu.technex18.utils.BoldModTextView;

import java.util.ArrayList;

public class Workshop extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DbConstants{


    private MaterialViewPager mViewPager;
    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private String ACTIVITY_NAME = "Workshops";

    FloatingActionButton fab;

    DbMethods dbMethods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workshop);

        setTitle("");

        mViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);

        BoldModTextView titletext = (BoldModTextView)findViewById(R.id.logo_white);
        titletext.setText(ACTIVITY_NAME);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        toolbar = mViewPager.getToolbar();
        dbMethods = new DbMethods(this);

        Cursor cursor = dbMethods.queryWorkshop(null, null, null, null, null, null);

        ArrayList<String> nameList = new ArrayList<String>();

        while (cursor.moveToNext()) {
            nameList.add(cursor.getString(cursor.getColumnIndex(COL_WORKSHOP_TITLE)));
        }

        final String[] names = nameList.toArray(new String[nameList.size()]);

        fab = (FloatingActionButton)findViewById(R.id.fab);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return WorkshopListFragment.newInstance();
                    default:
                        return WorkshopDetailFragment.newInstance(position);
                }

            }

            @Override
            public int getCount() {
                return (names.length)+1;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "HOME";
                    default:
                        return names[position-1];
                }
            }
        });

        //   toolbar.hideOverflowMenu();


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
                    case 5:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.md_yellow_700,
                                " ");
                    case 6:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.md_deep_orange_600,
                                " ");
                    case 7:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.md_red_800,
                                " ");
                    case 8:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.md_pink_700,
                                " ");
                    case 9:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.md_deep_purple_400,
                                " ");
                }

                //execute others actions if needed (ex : modify your header logo)

                return null;
            }
        });

        mViewPager.getViewPager().addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                animateFab(position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());

        if (fab.getVisibility() == View.VISIBLE) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://technex.in/workshops/"));
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        if (mViewPager.getViewPager().getCurrentItem() != 0) {
            mViewPager.getViewPager().setCurrentItem(0);
        }
        else {
            super.onBackPressed();
        }
    }

    private void animateFab(int position) {
        switch (position) {
            case 0:
                fab.setVisibility(View.GONE);
                break;

            default:
                fab.setVisibility(View.VISIBLE);

                break;
        }
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