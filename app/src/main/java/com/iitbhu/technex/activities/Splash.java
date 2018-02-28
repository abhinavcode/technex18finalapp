package com.iitbhu.technex18.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.iitbhu.technex18.R;
import com.iitbhu.technex18.database.DbMethods;
import com.iitbhu.technex18.network.URLs;
import com.iitbhu.technex18.utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Splash extends AppCompatActivity implements Constants, URLs {

    SharedPreferences myprefs;

    CoordinatorLayout layout;

    private RequestQueue queue;

    DbMethods dbMethods;
    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        layout=(CoordinatorLayout)findViewById(R.id.splash_layout) ;
        myprefs= PreferenceManager.getDefaultSharedPreferences(this);

        dbMethods = new DbMethods(this);
//        ImageView rocketImage = (ImageView) findViewById(R.id.splash);
//        rocketImage.setImageDrawable(R.drawable.splash);
//        animationDrawable = (AnimationDrawable) rocketImage.getBackground();
//        animationDrawable.start();
        YoYo.with(Techniques.Landing).duration(1000).playOn(findViewById(R.id.splash));
//        animationDrawable.
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(1000);
//                    runOnUiThread(new Runnable() {
//                        public void run() {
//                            layout.setBackgroundResource(R.drawable.splash7);
//                        }
//                    });
////
//                    sleep(200);
//                    runOnUiThread(new Runnable() {
//                        public void run() {
//                            layout.setBackgroundResource(R.drawable.splash6);
//                        }
//                    });
//                    sleep(200);
//                    runOnUiThread(new Runnable() {
//                        public void run() {
//                            layout.setBackgroundResource(R.drawable.splash5);
//                        }
//                    });
//                    sleep(200);
//                    runOnUiThread(new Runnable() {
//                        public void run() {
//                            layout.setBackgroundResource(R.drawable.splash4);
//                        }
//                    });
//                    sleep(200);
//                    runOnUiThread(new Runnable() {
//                        public void run() {
//                            layout.setBackgroundResource(R.drawable.splash3);
//                        }
//                    });
//                    sleep(200);
//                    runOnUiThread(new Runnable() {
//                        public void run() {
//                            layout.setBackgroundResource(R.drawable.splash2);
//                        }
//                    });
//                    sleep(200);
//                    runOnUiThread(new Runnable() {
//                        public void run() {
//                            layout.setBackgroundResource(R.drawable.splash1);
//                        }
//                    });
//
//                    sleep(400);



                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {

//
//                    readFromFile(getApplicationContext(), "event_data.txt");
//                    if (myprefs.getLong(LAST_UPDATE_TIME, 0) == 0) {

                    checkAndStoreToken();

                    populateInitialDB();

                    boolean loc=myprefs.getBoolean(REGISTERED, false);


                    if(loc){
                        Intent i = new Intent(Splash.this, HomeActivity.class);
                        startActivity(i);
                        finish();
                    }
                    else{
                        Intent i;
                        i=new Intent(Splash.this,LoginActivity.class);
                        startActivity(i);
                        finish();
                    }

                }

            }

        };
        timer.start();

    }

    public void checkAndStoreToken() {
        String token1 = myprefs.getString(FCM_TOKEN, null);
//        Log.d("token",token1);
        final String TAG = "SPLASH TOKEN";
        if ( ! (myprefs.getBoolean(IS_FCM_TOKEN_SENT_WITH_ID, false)) && (myprefs.getBoolean(REGISTERED, false)) ) {

            String token = myprefs.getString(FCM_TOKEN, null);

            if (token != null) {

                queue = Volley.newRequestQueue(this);


                Map<String, String> params = new HashMap<String, String>();
//                params.put("email", myprefs.getString(EMAIL, ""));
                params.put("notificationToken", token);
                params.put("technexId",myprefs.getString(TECHNEX_ID, "technexId"));
                Log.d("PARAMS TOKEN", params.toString());

                String url = TOKEN_URL;

                JsonObjectRequest strReq = new JsonObjectRequest(Request.Method.POST,
                        url, new JSONObject(params), new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject resp) {
                        Log.d(TAG, resp.toString());


                        try {
                            Log.d(TAG, "Token parser executed!");

                            int status = resp.getInt("status");

                            if (status == 1) {
                                SharedPreferences.Editor editor = myprefs.edit();
                                editor.putBoolean(IS_FCM_TOKEN_SENT_WITH_ID, true);
                                editor.commit();
                            }

                            Log.d(TAG, "Token parser executed properly!");
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d(TAG, "Token parser failed!");
                        }


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
//                Toast.makeText(getActivity(),"Network Unreachable!",Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json");
                        Log.d("HEADERS TOKEN", headers.toString());
                        return headers;
                    }
                };
                strReq.setRetryPolicy(new DefaultRetryPolicy(
                        30000,
                        10,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                queue.add(strReq);

            }
        }
       else if ( ! (myprefs.getBoolean(IS_FCM_TOKEN_SENT, false)) ) {

            String token = myprefs.getString(FCM_TOKEN, null);

            if (token != null) {

                queue = Volley.newRequestQueue(this);


                Map<String, String> params = new HashMap<String, String>();
//                params.put("email", myprefs.getString(EMAIL, ""));
                params.put("notificationToken", token);
                Log.d("PARAMS TOKEN", params.toString());

                String url = TOKEN_URL;

                JsonObjectRequest strReq = new JsonObjectRequest(Request.Method.POST,
                        url, new JSONObject(params), new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject resp) {
                        Log.d(TAG, resp.toString());


                        try {
                            Log.d(TAG, "Token parser executed!");

                            int status = resp.getInt("status");

                            if (status == 1) {
                                SharedPreferences.Editor editor = myprefs.edit();
                                editor.putBoolean(IS_FCM_TOKEN_SENT, true);
                                editor.commit();
                            }

                            Log.d(TAG, "Token parser executed properly!");
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d(TAG, "Token parser failed!");
                        }


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
//                Toast.makeText(getActivity(),"Network Unreachable!",Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json");
                        Log.d("HEADERS TOKEN", headers.toString());
                        return headers;
                    }
                };
                strReq.setRetryPolicy(new DefaultRetryPolicy(
                        30000,
                        10,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                queue.add(strReq);

            }
        }

    }



    public void populateInitialDB() {
        if (myprefs.getBoolean(IS_PEOPLE_FETCHED, false)) {

        } else {
            try {
                Log.d("INITIAL DB PEOPLE", "fafa");
                dbMethods.deleteAllGuestLecture();
                JSONObject responseObject = new JSONObject(readFromFile(Splash.this, "team.txt"));
                JSONArray responseArray = responseObject.getJSONArray("team");
                for (int i = 0; i < responseArray.length(); i++) {
                    JSONObject GLObject = responseArray.getJSONObject(i);
                    String name = GLObject.getString("name");
                    int order = GLObject.getInt("order");
                    String number = GLObject.getInt("number")+"";
                    String email = GLObject.getString("email");
                    String image = GLObject.getString("photo");
                    String des=GLObject.getString("designation");
                    dbMethods.insertPeople(order,name,number,email,image,des);
                }

//                SharedPreferences.Editor editor = myprefs.edit();
//                editor.putBoolean(IS_TALKS_FETCHED, true);
//                editor.commit();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }



        if (myprefs.getBoolean(IS_EVENT_FETCHED, false)) {

        } else {

            try {
                Log.d("INITIAL DB EVENTS", "fafa");
                dbMethods.deleteAllEvents();
                dbMethods.deleteAllEventOptions();
                //for events_resp
                JSONObject response = new JSONObject(readFromFile(Splash.this, "events.txt"));
                JSONArray responseArray = response.getJSONArray("data");
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
                        String description = "";
//                        currEvent.getString("eventDescription");
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
                        for (int k = 0; k < eventOptions.length(); k++) {
                            JSONObject currEventOption = eventOptions.getJSONObject(k);
                            String optionName = currEventOption.getString("optionName");
                            String optionDescription = currEventOption.getString("optionDescription");
                            int optionOrder = Integer.parseInt(currEventOption.getString("eventOptionOrder"));
                            dbMethods.insertEventOptions(optionName, optionDescription, eventName, optionOrder);
//                                eventOptionsMap.put(optionName, optionDescription);
                        }
                    }
                }

//                SharedPreferences.Editor editor = myprefs.edit();
//                editor.putBoolean(IS_EVENT_FETCHED, true);
//                editor.commit();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


        if (myprefs.getBoolean(IS_TALKS_FETCHED, false)) {

        } else {
            try {
                Log.d("INITIAL DB TALKS", "fafa");
                dbMethods.deleteAllGuestLecture();
                JSONObject responseObject = new JSONObject(readFromFile(Splash.this, "talks.txt"));
                JSONArray responseArray = responseObject.getJSONArray("lectures");
                for (int i = 0; i < responseArray.length(); i++) {
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

//                SharedPreferences.Editor editor = myprefs.edit();
//                editor.putBoolean(IS_TALKS_FETCHED, true);
//                editor.commit();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        if (myprefs.getBoolean(IS_WORKSHOPS_FETCHED, false)) {

        } else {
            try {
                Log.d("INITIAL DB WORKSHOPS", "fafa");
                dbMethods.deleteAllWorkshop();
                JSONObject response = new JSONObject(readFromFile(Splash.this, "workshops.txt"));
                JSONArray responseArray = response.getJSONArray("workshops");
                for (int i = 0; i < responseArray.length(); i++) {
                    JSONObject currWorkshop = responseArray.getJSONObject(i);
                    String title = currWorkshop.getString("title");
                    int order = Integer.parseInt(currWorkshop.getString("order"));
                    double fees = currWorkshop.getDouble("workshopFees");
                    String description = currWorkshop.getString("description");
                    String timestamp = currWorkshop.getString("dateTime");
                    String image = "http://www.technex.in" + currWorkshop.getString("image");
                    dbMethods.insertWorkshop(title, order, fees, description, timestamp, image);
                }
//                SharedPreferences.Editor editor = myprefs.edit();
//                editor.putBoolean(IS_WORKSHOPS_FETCHED, true);
//                editor.commit();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }





    public static String readFromFile(Context context, String file) {
        try {
            InputStream is = context.getAssets().open(file);
            int size = is.available();
            byte buffer[] = new byte[size];
            is.read(buffer);
            is.close();
            return new String(buffer);
        } catch (Exception e) {
            e.printStackTrace();
            return "" ;
        }
    }
}