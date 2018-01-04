package com.iitbhu.technex18.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.iitbhu.technex18.R;
import com.iitbhu.technex18.activities.CorporateConclave;
import com.iitbhu.technex18.activities.Exhibitions;
import com.iitbhu.technex18.activities.Gallery;
import com.iitbhu.technex18.activities.Hospitality;
import com.iitbhu.technex18.activities.InstituteDay;
import com.iitbhu.technex18.activities.Pronites;
import com.iitbhu.technex18.activities.StartupFair;
import com.iitbhu.technex18.activities.ThinkTalks;
import com.iitbhu.technex18.activities.Workshop;
import com.iitbhu.technex18.database.DbMethods;
import com.iitbhu.technex18.network.URLs;
import com.iitbhu.technex18.utils.Constants;

import java.util.HashMap;
import java.util.logging.Handler;

/**
 * Created by root on 18/9/16.
 */
public class HomeFragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener, Constants, URLs {
    public HomeFragment() {
    }

    View rootView;
    ViewPager viewPager;
    private ObservableScrollView mScrollView;

    private MaterialViewPager mViewPager;

    private ProgressDialog progress;

    boolean isCardTapped = false;

    Thread mThread;


    private SliderLayout mCarousel;
    LinearLayout carousel_control;
    FrameLayout carousel_home;
    FloatingActionButton play, stop, list;
    SharedPreferences myprefs;
    DbMethods dbMethods;
    RequestQueue queue;
    int STORE_GUEST_LECTURES=0, STORE_EVENTS=1, STORE_WORKSHOPS=2, STORE_STARTUP_FAIR=3, STORE_PRONITES=4, STORE_INSTITURE_DAY=5, STORE_CORPORATE_CONCLAVE=6, STORE_EXHIBITIONS=7, STORE_HOSPITALITY=8;
//    private String MY_PREFS_NAME="MyPrefsFile";

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    /*ArrayList<String> Category_Name = new ArrayList<>();
    ArrayList<String> Category_Desc = new ArrayList<>();
    ArrayList<String> Category_Class = new ArrayList<>();
    ArrayList<Color> Category_Color = new ArrayList<>();;
    ArrayList<Bitmap> Category_Icon = new ArrayList<>();
    final String Package_Class = "com.iitbhu.technex18.activities";
    final String Package_Frag = "com.iitbhu.technex18.fragments";*/

    public int open_stat= 0;
    public int play_stat= 1;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mScrollView = (ObservableScrollView) view.findViewById(R.id.scrollView);
        MaterialViewPagerHelper.registerScrollView(getActivity(), mScrollView, null);

        dbMethods = new DbMethods(getActivity());
        myprefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        rootView = view;


        progress = new ProgressDialog(getActivity());
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setMessage("Fetching details. Please wait...");
        progress.setCancelable(false);
        progress.setIndeterminate(true);

        mViewPager = (MaterialViewPager)getActivity().findViewById(R.id.materialViewPager);
        viewPager=mViewPager.getViewPager();


        carousel_home = (FrameLayout) rootView.findViewById(R.id.carousel_home);
        carousel_control = (LinearLayout)rootView.findViewById(R.id.carousel_frame);
        carousel_control.setVisibility(View.GONE);
        open_stat=0;

        queue = Volley.newRequestQueue(getActivity());

        play = (FloatingActionButton)rootView.findViewById(R.id.play);
        //  stop = (FloatingActionButton)rootView.findViewById(R.id.stop);
        // list = (FloatingActionButton)rootView.findViewById(R.id.list);

        carousel_control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_stat=0;
                if(play_stat==0){
                    carousel_control.setVisibility(View.VISIBLE);
           //         Toast.makeText(getActivity(),"Slideshow Paused",Toast.LENGTH_SHORT).show();
                }
                else{
                    play.setImageResource(R.drawable.ic_play);
                    carousel_control.setVisibility(View.VISIBLE);
                    mCarousel.stopAutoCycle();
             //       Toast.makeText(getActivity(),"Slideshow Paused",Toast.LENGTH_SHORT).show();
                    play_stat=0;
                    // carousel_control.setVisibility(View.GONE);
                }

            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_stat=0;
                play.setImageResource(R.drawable.ic_play);
                carousel_control.setVisibility(View.GONE);
                mCarousel.startAutoCycle();
           //     Toast.makeText(getActivity(),"Slideshow Resumed",Toast.LENGTH_SHORT).show();
                play_stat=1;
            }
        });

        carousel();
        setupNavigRecyclerView();


        /*
        // To be taken from api
        Category_Name.add("Events");Category_Name.add("Think Talks");
        Category_Name.add("Workshops");Category_Name.add("Startup Fair");
        Category_Name.add("Pronites");Category_Name.add("Institute Day");
        Category_Name.add("Corporate Conclave");Category_Name.add("Exhibitions");
        Category_Name.add("Hospitality");Category_Name.add("Gallery");

        Category_Desc.add("Ready. Aim. Fire for Glory.");Category_Desc.add("Soak in the Wisdom.");
        Category_Desc.add("Learn by Fun.");Category_Desc.add("Startup Fair");
        Category_Desc.add("Pronites");Category_Desc.add("Institute Day");
        Category_Desc.add("Corporate Conclave");Category_Desc.add("Exhibitions");
        Category_Desc.add("Hospitality");Category_Desc.add("Gallery");

        Category_Name.add("Events");Category_Name.add("Think Talks");
        Category_Name.add("Wprkshops");Category_Name.add("Startup Fair");
        Category_Name.add("Pronites");Category_Name.add("Institute Day");
        Category_Name.add("Corporate Conclave");Category_Name.add("Exhibitions");
        Category_Name.add("Hospitality");Category_Name.add("Gallery");
        */


        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        long lastupdate=myprefs.getLong(LAST_UPDATE_TIME,0);
        if(System.currentTimeMillis()/1000-lastupdate>86400){
            getGuestLectures();
//          TODO a timestamp for a day
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (progress.isShowing()) {
            progress.dismiss();
        }
    }

    private void carousel(){

        mCarousel = (SliderLayout)rootView.findViewById(R.id.slider);

        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Kaleidoscope", R.drawable.carousel02);
        file_maps.put("Experience the Thrill", R.drawable.carousel04);
        file_maps.put("Creativity at its Best", R.drawable.carousel05);
        file_maps.put("Welcome", R.drawable.carousel01);
        file_maps.put("Unmatchable Events", R.drawable.carousel03);
        file_maps.put("The Magnificent Benares", R.drawable.carousel06);

        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mCarousel.addSlider(textSliderView);
        }
        mCarousel.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mCarousel.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mCarousel.setCustomAnimation(new DescriptionAnimation());
        mCarousel.setDuration(4000);
        mCarousel.addOnPageChangeListener(this);

    }


    @Override
    public void onStart() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mCarousel.startAutoCycle();
        play_stat=1;
        super.onStart();
    }

    @Override
    public void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mCarousel.stopAutoCycle();
        play_stat=0;
        super.onStop();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}

    @Override
    public void onSliderClick(BaseSliderView baseSliderView) {
        Log.d("OnSliderClick","True");
        if(open_stat==0){
            carousel_control.setVisibility(View.VISIBLE);
            open_stat=1;
            Log.d("OnSliderClick","True,0");

        }
        else if(open_stat==1){
            carousel_control.setVisibility(View.GONE);
            open_stat=0;
            mCarousel.startAutoCycle();
            Log.d("OnSliderClick","True,1");
        }
    }

    private void setupNavigRecyclerView() {

        /*
        LIST OF CARDS (CV WISE)
        1. Events
        2. Think Talks
        3. Workshops
        4. Startup Fair
        5. Hospitality
        6. Technex Go!
        7. Gallery
        8. Video Wall
        9. Pronites
        10. Exhibitions
        11. Corporate Conclave
        12. Institute Day
        */

        FrameLayout cv1;   //Events
        cv1=(FrameLayout)rootView.findViewById(R.id.cv1);
        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(2);
                isCardTapped = true;
                getEvents();

//                if(myprefs.getBoolean(IS_EVENT_FETCHED, false)) {
//
//                } else {
//                }
            }
        });

        CardView cv2;   //Think Talks
        cv2=(CardView)rootView.findViewById(R.id.cv2);
        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress.show();
                isCardTapped = true;
                getGuestLectures();
//                if (myprefs.getBoolean(IS_TALKS_FETCHED, false)) {
                Intent intent = new Intent(getActivity(), ThinkTalks.class);
                startActivity(intent);
//                } else {
//                    Intent intent = new Intent(getActivity(), LoadingActivity.class);
//                    startActivity(intent);
//                }
            }
        });

        CardView cv3;   //Workshops
        cv3=(CardView)rootView.findViewById(R.id.cv3);
        cv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress.show();
                isCardTapped = true;
                getWorkshops();
//                if (myprefs.getBoolean(IS_WORKSHOPS_FETCHED, false)) {
                Intent intent = new Intent(getActivity(), Workshop.class);
                startActivity(intent);
//                } else {
//                    Intent intent = new Intent(getActivity(), LoadingActivity.class);
//                    startActivity(intent);
//                }
            }
        });

        CardView cv4;   //Startup Fair
        cv4=(CardView)rootView.findViewById(R.id.cv4);
        cv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress.show();
//                if (myprefs.getBoolean(IS_STARTUP_FAIR_FETCHED, false)) {
//                } else {
////                    Intent intent = new Intent(getActivity(), LoadingActivity.class);
////                    startActivity(intent);
//                }
                isCardTapped = true;
                getStartupFair();
                Intent intent = new Intent(getActivity(), StartupFair.class);
                startActivity(intent);
            }
        });

        CardView cv5;   //Hospitality
        cv5=(CardView)rootView.findViewById(R.id.cv5);
        cv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress.show();
//                if (myprefs.getBoolean(IS_HOSPITALITY_FETCHED, false)) {
//                } else {
////                    Intent intent = new Intent(getActivity(), LoadingActivity.class);
////                    startActivity(intent);
//                }
                isCardTapped = true;
                getHospitality();
                Intent intent = new Intent(getActivity(), Hospitality.class);
                startActivity(intent);
            }
        });

        /*
        CardView cv6;   //Technex Go!
        cv6=(CardView)rootView.findViewById(R.id.cv6);
        cv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent workshop_intent=new Intent(getActivity(), WaitAcitivity.class);
                startActivity(workshop_intent);
            }
        });*/

        CardView cv7;   //gallery
        cv7=(CardView)rootView.findViewById(R.id.cv7);
        cv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress.show();
                Intent workshop_intent=new Intent(getActivity(), Gallery.class);
                startActivity(workshop_intent);
            }
        });

        /*
        CardView cv8;   //Video Wall
        cv8=(CardView)rootView.findViewById(R.id.cv8);
        cv8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent workshop_intent=new Intent(getActivity(), WaitAcitivity.class);
                startActivity(workshop_intent);
            }
        });*/

        CardView cv9;   //Pronites
        cv9=(CardView)rootView.findViewById(R.id.cv9);
        cv9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress.show();
//                if (myprefs.getBoolean(IS_KALEIDOSCOPE_FETCHED, false)) {
//                } else {
////                    Intent intent = new Intent(getActivity(), LoadingActivity.class);
////                    startActivity(intent);
//                }
                isCardTapped = true;
                getPronites();
                Intent intent = new Intent(getActivity(), Pronites.class);
                startActivity(intent);
            }
        });

        CardView cv10;   //Exhibitions
        cv10=(CardView)rootView.findViewById(R.id.cv10);
        cv10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress.show();
//                if (myprefs.getBoolean(IS_EXHIBITIONS_FETCHED, false)) {
//                } else {
////                    Intent intent = new Intent(getActivity(), LoadingActivity.class);
////                    startActivity(intent);
//                }
                isCardTapped = true;
                getExhibitions();
                Intent intent = new Intent(getActivity(), Exhibitions.class);
                startActivity(intent);
            }
        });

        CardView cv11;   //Corporate Conclave
        cv11=(CardView)rootView.findViewById(R.id.cv11);
        cv11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress.show();
//                if (myprefs.getBoolean(IS_CORPORATE_CONCLAVE_FETCHED, false)) {
//                } else {
////                    Intent intent = new Intent(getActivity(), LoadingActivity.class);
////                    startActivity(intent);
//                }
                isCardTapped = true;
                getCorporateConclave();
                Intent intent = new Intent(getActivity(), CorporateConclave.class);
                startActivity(intent);
            }
        });

        CardView cv12;   //Institute Day
        cv12=(CardView)rootView.findViewById(R.id.cv12);
        cv12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress.show();
//                if (myprefs.getBoolean(IS_INSTITUTE_DAY_FETCHED, false)) {
//                } else {
////                    Intent intent = new Intent(getActivity(), LoadingActivity.class);
////                    startActivity(intent);
//                }
                isCardTapped = true;
                getInstituteDay();
                Intent intent = new Intent(getActivity(), InstituteDay.class);
                startActivity(intent);
            }
        });
    }



    private void getGuestLectures(){
        final String TAG = "TECHNEX NETWORK";




        String url = GUEST_LECTURES_URL;
        // mViewPager.setVisibility(View.GONE);
        //menuProgressLayout.setVisibility(View.VISIBLE);

        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG + " GUEST LECTURES", response.toString());
                new StoreTask(response,STORE_GUEST_LECTURES).execute();


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());

            }
        });
        // Adding request to request queue
        queue.add(strReq);

    }


//    private void getPeople(){
//        final String TAG = "TECHNEX NETWORK";
//
//        String url = PEOPLE;
//        // mViewPager.setVisibility(View.GONE);
//        //menuProgressLayout.setVisibility(View.VISIBLE);
//
//        StringRequest strReq = new StringRequest(Request.Method.GET,
//                url, new Response.Listener<String>() {
//
//            @Override
//            public void onResponse(String response) {
//                Log.d(TAG, response.toString());
//                new StoreTask(response,STORE_PEOPLE).execute();
//
//
//            }
//        }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                VolleyLog.d(TAG, "Error: " + error.getMessage());
//
//            }
//        });
//        // Adding request to request queue
//        queue.add(strReq);
//    }

    private void getEvents(){
        final String TAG = "TECHNEX NETWORK";

        String url = EVENTS_URL;
        // mViewPager.setVisibility(View.GONE);
        //menuProgressLayout.setVisibility(View.VISIBLE);

        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG + " EVENTS", response.toString());
                new StoreTask(response,STORE_EVENTS).execute();


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());

            }
        });
        // Adding request to request queue
        queue.add(strReq);

    }

    private void getWorkshops(){
        final String TAG = "TECHNEX NETWORK";

        String url = WORKSHOPS_URL;
        // mViewPager.setVisibility(View.GONE);
        //menuProgressLayout.setVisibility(View.VISIBLE);

        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG + " WORKSHOPS", response.toString());
                new StoreTask(response,STORE_WORKSHOPS).execute();


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());

            }
        });
        // Adding request to request queue
        queue.add(strReq);

    }


    private void getStartupFair(){
        final String TAG = "TECHNEX NETWORK";

        String url = STARTUP_FAIR_URL;
        // mViewPager.setVisibility(View.GONE);
        //menuProgressLayout.setVisibility(View.VISIBLE);

        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG + " STARTUP FAIR", response.toString());
                new StoreTask(response,STORE_STARTUP_FAIR).execute();


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());

            }
        });
        // Adding request to request queue
        queue.add(strReq);

    }

    private void getPronites(){
        final String TAG = "TECHNEX NETWORK";

        String url = PRONITES_URL;
        // mViewPager.setVisibility(View.GONE);
        //menuProgressLayout.setVisibility(View.VISIBLE);

        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG + " PRONITES", response.toString());
                new StoreTask(response,STORE_PRONITES).execute();


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());

            }
        });
        // Adding request to request queue
        queue.add(strReq);

    }

    private void getInstituteDay(){
        final String TAG = "TECHNEX NETWORK";

        String url = INSTITUTE_DAY_URL;
        // mViewPager.setVisibility(View.GONE);
        //menuProgressLayout.setVisibility(View.VISIBLE);

        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG + " INSTITUTE DAY", response.toString());
                new StoreTask(response,STORE_INSTITURE_DAY).execute();


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());

            }
        });
        // Adding request to request queue
        queue.add(strReq);

    }

    private void getCorporateConclave(){
        final String TAG = "TECHNEX NETWORK";

        String url = CORPORATE_CONCLAVE_URL;
        // mViewPager.setVisibility(View.GONE);
        //menuProgressLayout.setVisibility(View.VISIBLE);

        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG + " CORPORATE", response.toString());
                new StoreTask(response,STORE_CORPORATE_CONCLAVE).execute();


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());

            }
        });
        // Adding request to request queue
        queue.add(strReq);

    }

    private void getExhibitions(){
        final String TAG = "TECHNEX NETWORK";

        String url = EXHIBITIONS_URL;
        // mViewPager.setVisibility(View.GONE);
        //menuProgressLayout.setVisibility(View.VISIBLE);

        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG + " EXHIBITIONS", response.toString());
                new StoreTask(response,STORE_EXHIBITIONS).execute();


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());

            }
        });
        // Adding request to request queue
        queue.add(strReq);

    }

    private void getHospitality(){
        final String TAG = "TECHNEX NETWORK";

        String url = HOSPITALITY_URL;
        // mViewPager.setVisibility(View.GONE);
        //menuProgressLayout.setVisibility(View.VISIBLE);

        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG + " HOSPITALITY", response.toString());
                new StoreTask(response,STORE_HOSPITALITY).execute();


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());

            }
        });
        // Adding request to request queue
        queue.add(strReq);

    }


    private class LoadingClass extends AsyncTask<Void, Void, Void> {

        public LoadingClass() {

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }



    private class StoreTask extends AsyncTask<Void,Void,Void> {

        String json;
        int table;

        public StoreTask(String json,int table){
            this.json=json;
            this.table=table;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }



        @Override
        protected Void doInBackground(Void... params) {

            if(table==STORE_GUEST_LECTURES) {
                try {
                    dbMethods.deleteAllGuestLecture();
                    JSONObject responseObject = new JSONObject(json);
                    JSONArray responseArray = responseObject.getJSONArray("lectures");
                    int status = responseObject.getInt("status");
                    for(int i=0;i<responseArray.length();i++){
                        JSONObject GLObject = responseArray.getJSONObject(i);
                        String lecturerBio = GLObject.getString("lecturerBio");
                        String lectureType = GLObject.getString("lectureType");
                        String lecturerName = GLObject.getString("lecturerName");
                        String title = GLObject.getString("title");
                        String description = GLObject.getString("description");
                        String designation = GLObject.getString("designation");
                        String image = GLObject.getString("photo");

                        dbMethods.insertGuestLecture(lecturerBio, lectureType, lecturerName, title, description, designation, image);

                    }

                    SharedPreferences.Editor editor = myprefs.edit();
                    editor.putBoolean(IS_TALKS_FETCHED, true);
                    editor.commit();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (isCardTapped) {
                    isCardTapped = false;
                } else {
                    getEvents();
                }
//            }else if(table==STORE_PEOPLE){
//                try {
//                    dbMethods.deleteAllPeople();
//                    //for events_resp
//                    JSONArray responseArray = new JSONArray(json);
//                    for(int i=0;i<responseArray.length();i++) {
//                        JSONObject responseObject = responseArray.getJSONObject(i);
//                        long Id = Long.parseLong(responseObject.getString("COL_PEOPLE_ID"));
//                        String name = responseObject.getString("COL_PEOPLE_NAME");
//                        String contact = responseObject.getString("COL_PEOPLE_CONTACT");
//                        String email = responseObject.getString("COL_PEOPLE_EMAIL");
//
//                        dbMethods.insertPeople(Id, name, contact, email);
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }else if(table==STORE_EVENTS){
                try {
                    dbMethods.deleteAllEvents();
                    dbMethods.deleteAllEventOptions();
                    //for events_resp
                    JSONObject response = new JSONObject(json);
                    JSONArray responseArray = response.getJSONArray("data");
                    int status = response.getInt("status");
                    for(int i=0;i<responseArray.length();i++) {
                        JSONObject responseObject = responseArray.getJSONObject(i);
                        String parentCategory = responseObject.getString("name");
                        String parentDescription = responseObject.getString("description");
                        JSONArray events = responseObject.getJSONArray("events");
                        for(int j=0; j<events.length(); j++) {
                            JSONObject currEvent = events.getJSONObject(j);
                            int eventOrder;
                            int teamSize;
                            String eventName = currEvent.getString("eventName");
                            int prizeMoney;
                            String description = currEvent.getString("eventDescription");
                            String deadline;
                            try {
                                eventOrder = Integer.parseInt(currEvent.getString("eventOrder"));
                            } catch (Exception e) {
                                eventOrder = 0;
                                e.printStackTrace();
                            }
                            try {
                                teamSize = Integer.parseInt(currEvent.getString("maxMembers"));
                            } catch (Exception e) {
                                teamSize = 0;
                                e.printStackTrace();
                            }
                            try {
                                prizeMoney = Integer.parseInt(currEvent.getString("prizeMoney"));
                            } catch (Exception e) {
                                prizeMoney = 0;
                                e.printStackTrace();
                            }
                            try {
                                deadline = currEvent.getString("deadLine");
                            } catch (Exception e) {
                                deadline = "";
                                e.printStackTrace();
                            }
                            long eventId = dbMethods.insertEvents(parentCategory, parentDescription, eventOrder, teamSize, eventName, prizeMoney, description, deadline);

//                            Map<String, String> eventOptionsMap = new HashMap<String, String>();
                            JSONArray eventOptions = currEvent.getJSONArray("eventOptions");
                            for (int k=0; k<eventOptions.length(); k++) {
                                JSONObject currEventOption = eventOptions.getJSONObject(k);
                                String optionName = currEventOption.getString("optionName");
                                String optionDescription = currEventOption.getString("optionDescription");
                                int optionOrder = Integer.parseInt(currEventOption.getString("eventOptionOrder"));
                                dbMethods.insertEventOptions(optionName, optionDescription, eventName, optionOrder);
//                                eventOptionsMap.put(optionName, optionDescription);
                            }
                        }
                    }

                    SharedPreferences.Editor editor = myprefs.edit();
                    editor.putBoolean(IS_EVENT_FETCHED, true);
                    editor.commit();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (isCardTapped) {
                    isCardTapped = false;
                } else {
                    getWorkshops();
                }
            }else if(table==STORE_WORKSHOPS) {
                try {
                    dbMethods.deleteAllWorkshop();
                    JSONObject response = new JSONObject(json);
                    JSONArray responseArray = response.getJSONArray("workshops");
                    int status = response.getInt("status");
                    for(int i=0; i<responseArray.length(); i++) {
                        JSONObject currWorkshop = responseArray.getJSONObject(i);
                        String title = currWorkshop.getString("title");
                        int order = Integer.parseInt(currWorkshop.getString("order"));
                        double fees = currWorkshop.getDouble("workshopFees");
                        String description = currWorkshop.getString("description");
                        String timestamp = currWorkshop.getString("dateTime");
                        String image = "http://www.technex.in" + currWorkshop.getString("image");
                        dbMethods.insertWorkshop(title, order, fees, description, timestamp, image);
                        //JSONArray workshopOptions = currWorkshop.getJSONArray("workshopOptions");
                    }
                    SharedPreferences.Editor editor = myprefs.edit();
                    editor.putBoolean(IS_WORKSHOPS_FETCHED, true);
                    editor.commit();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (isCardTapped) {
                    isCardTapped = false;
                } else {
                    getStartupFair();
                }
            }else if(table==STORE_STARTUP_FAIR) {
                try {
                    dbMethods.deleteAllStartupFair();
                    JSONObject response = new JSONObject(json);
                    JSONArray responseArray = response.getJSONArray("data");
                    JSONObject currObject = responseArray.getJSONObject(0);
                    String introduction = currObject.getString("introduction");
                    String content = currObject.getString("content");
                    dbMethods.insertStartupFair(introduction, content);
                    SharedPreferences.Editor editor = myprefs.edit();
                    editor.putBoolean(IS_STARTUP_FAIR_FETCHED, true);
                    editor.commit();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (isCardTapped) {
                    isCardTapped = false;
                } else {
                    getPronites();
                }
            }else if(table==STORE_PRONITES) {
                try {
                    dbMethods.deleteAllPronites();
                    JSONObject response = new JSONObject(json);
                    JSONArray responseArray = response.getJSONArray("data");
                    JSONObject currObject = responseArray.getJSONObject(0);
                    String introduction = currObject.getString("introduction");
                    String content = currObject.getString("content");
                    dbMethods.insertPronites(introduction, content);
                    SharedPreferences.Editor editor = myprefs.edit();
                    editor.putBoolean(IS_KALEIDOSCOPE_FETCHED, true);
                    editor.commit();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (isCardTapped) {
                    isCardTapped = false;
                } else {
                    getInstituteDay();
                }
            }else if(table==STORE_INSTITURE_DAY) {
                try {
                    dbMethods.deleteAllInstituteDay();
                    JSONObject response = new JSONObject(json);
                    JSONArray responseArray = response.getJSONArray("data");
                    JSONObject currObject = responseArray.getJSONObject(0);
                    String introduction = currObject.getString("introduction");
                    String content = currObject.getString("content");
                    dbMethods.insertInstituteDay(introduction, content);
                    SharedPreferences.Editor editor = myprefs.edit();
                    editor.putBoolean(IS_INSTITUTE_DAY_FETCHED, true);
                    editor.commit();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (isCardTapped) {
                    isCardTapped = false;
                } else {
                    getCorporateConclave();
                }
            }else if(table==STORE_CORPORATE_CONCLAVE) {
                try {
                    dbMethods.deleteAllCorporateConclave();
                    JSONObject response = new JSONObject(json);
                    JSONArray responseArray = response.getJSONArray("data");
                    JSONObject currObject = responseArray.getJSONObject(0);
                    String introduction = currObject.getString("introduction");
                    String content = currObject.getString("content");
                    dbMethods.insertCorporateConclave(introduction, content);
                    SharedPreferences.Editor editor = myprefs.edit();
                    editor.putBoolean(IS_CORPORATE_CONCLAVE_FETCHED, true);
                    editor.commit();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (isCardTapped) {
                    isCardTapped = false;
                } else {
                    getExhibitions();
                }
            }else if(table==STORE_EXHIBITIONS) {
                try {
                    dbMethods.deleteAllExhibitions();
                    JSONObject response = new JSONObject(json);
                    JSONArray responseArray = response.getJSONArray("data");
                    JSONObject currObject = responseArray.getJSONObject(0);
                    String introduction = currObject.getString("introduction");
                    String content = currObject.getString("content");
                    dbMethods.insertExhibitions(introduction, content);
                    SharedPreferences.Editor editor = myprefs.edit();
                    editor.putBoolean(IS_EXHIBITIONS_FETCHED, true);
                    editor.commit();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (isCardTapped) {
                    isCardTapped = false;
                } else {
                    getHospitality();
                }
            }else if(table==STORE_HOSPITALITY) {
                try {
                    dbMethods.deleteAllHospitality();
                    JSONObject response = new JSONObject(json);
                    JSONArray responseArray = response.getJSONArray("data");
                    JSONObject currObject = responseArray.getJSONObject(0);
                    String introduction = currObject.getString("introduction");
                    String content = currObject.getString("content");
                    dbMethods.insertHospitality(introduction, content);
                    SharedPreferences.Editor editor = myprefs.edit();
                    editor.putBoolean(IS_HOSPITALITY_FETCHED, true);
                    editor.commit();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (isCardTapped) {
                    isCardTapped = false;
                } else {
                    
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(table==STORE_GUEST_LECTURES){
//
//            }else if(table==STORE_PEOPLE){

            }else if(table==STORE_EVENTS) {
//                clubAdapter.changeCursor(dbMethods.queryClub(null, null, null, null, null, null));
//                mAdapter.notifyDataSetChanged();
            } else if(table==STORE_WORKSHOPS) {

            } else if (table==STORE_STARTUP_FAIR) {

            } else if (table==STORE_PRONITES) {

            } else if (table==STORE_INSTITURE_DAY) {

            } else if (table==STORE_CORPORATE_CONCLAVE) {

            } else if (table==STORE_EXHIBITIONS) {

            } else if (table==STORE_HOSPITALITY) {

                if (myprefs.getBoolean(IS_EVENT_FETCHED, false) &&
                        myprefs.getBoolean(IS_WORKSHOPS_FETCHED, false) &&
                        myprefs.getBoolean(IS_TALKS_FETCHED, false) &&
                        myprefs.getBoolean(IS_STARTUP_FAIR_FETCHED, false) &&
                        myprefs.getBoolean(IS_CORPORATE_CONCLAVE_FETCHED, false) &&
                        myprefs.getBoolean(IS_KALEIDOSCOPE_FETCHED, false) &&
                        myprefs.getBoolean(IS_INSTITUTE_DAY_FETCHED, false) &&
                        myprefs.getBoolean(IS_EXHIBITIONS_FETCHED, false) &&
                        myprefs.getBoolean(IS_HOSPITALITY_FETCHED, false)){
                    SharedPreferences.Editor editor = myprefs.edit();
                    editor.putLong(LAST_UPDATE_TIME, System.currentTimeMillis() / 1000);
                    editor.commit();
                    Log.d("LAST UPDATE TIME", "fafa");
                }
            }
        }
    }


}

