package com.iitbhu.technex18.activities;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
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
import android.widget.Toast;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;

import com.iitbhu.technex18.R;
import com.iitbhu.technex18.database.DbConstants;
import com.iitbhu.technex18.database.DbMethods;
import com.iitbhu.technex18.fragments.EventDetailFragment;
import com.iitbhu.technex18.utils.BoldModTextView;

import java.util.ArrayList;

public class EventDetail extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DbConstants {

    private MaterialViewPager mViewPager;
    private Toolbar toolbar;
    private String ACTIVITY_NAME = "Robowars";
    private DrawerLayout mDrawerLayout;

    DbMethods dbMethods;

    ArrayList<String> nameList = new ArrayList<String>();
    ArrayList<String> descList = new ArrayList<String>();
    ArrayList<Integer> orderList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        setTitle("");

        mViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);

        ACTIVITY_NAME = getIntent().getStringExtra("EVENTNAME");

        BoldModTextView titletext = (BoldModTextView)findViewById(R.id.logo_white);
        titletext.setText(ACTIVITY_NAME);

        toolbar = mViewPager.getToolbar();

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        //   mViewPager.getToolbar().setNavigationIcon(false);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        dbMethods = new DbMethods(this);

        Cursor cursor = dbMethods.queryEventOptions(null, COL_EVENTS_OPTIONS_EVENT+" = ?", new String[]{getIntent().getStringExtra("EVENTNAME")}, null, null, COL_EVENTS_OPTIONS_ORDER);

        while (cursor.moveToNext()) {
            nameList.add(cursor.getString(cursor.getColumnIndex(COL_EVENTS_OPTIONS_NAME)));
            descList.add(cursor.getString(cursor.getColumnIndex(COL_EVENTS_OPTIONS_DESCRIPTION)));
//            orderList.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_EVENTS_OPTIONS_ORDER))));
        }

        final String[] names = nameList.toArray(new String[nameList.size()]);
        final String[] desc = descList.toArray(new String[descList.size()]);
//        final  Integer[] orders = orderList.toArray(new Integer[orderList.size()]);


        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                // getItem is called to instantiate the fragment for the given page.
                // Return a EventDetailFragment (defined as a static inner class below).
//                if(nameList.get(position).substring(0,4).equals("Cont")) {
//                    return EventContactFragment.newInstance();
//                }
//            else if(nameList.get(position).substring(0,4).equals("Prob")){
//                return EventProbStatementFragment.newInstance();
//            }
//            else if(position==2){
//                return EventProbStatementFragment.newInstance();
//            }
//            else if(position==3){
//                return EventRegFragment.newInstance();
//            }
//            else if(position==4){
//                return EventContactFragment.newInstance();
//            }
//                else{
//                    return EventDetailFragment.newInstance(nameList.get(position), descList.get(position));
//                }
                    return EventDetailFragment.newInstance(position,nameList.get(position), descList.get(position));

            }

            @Override
            public int getCount() {
                // Show 5 total pages.
                return nameList.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
//            switch (position) {
//                case 0:
//                    return "INTRODUCTION";
//                case 1:
//                    return "EVENT STRUCTURE";
//                case 2:
//                    return "PROBLEM STATEMENT";
//                case 3:
//                    return "REGISTER";
//                case 4:
//                    return "CONTACT US";
//            }
//            return null;
                return nameList.get(position);
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

        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());

        View logo = findViewById(R.id.logo_white);
        if (logo != null) {
            logo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.notifyHeaderChanged();
                    Toast.makeText(getApplicationContext(), "Yes, the title is clickable", Toast.LENGTH_SHORT).show();
                }
            });
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