package com.iitbhu.technex18.fragments;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.iitbhu.technex18.R;
import com.iitbhu.technex18.activities.HomeActivity;
import com.iitbhu.technex18.activities.Splash;
import com.iitbhu.technex18.database.DbMethods;
import com.iitbhu.technex18.network.URLs;
import com.iitbhu.technex18.utils.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Abhinav on 02/01/2018.
 */
public class LoginFragment extends Fragment implements Constants, URLs {

    EditText etEmail, etPassword;
    Button btnRegister, btnLogin;
    LinearLayout layoutLogin;

    Button btnForgotSubmit;

    SharedPreferences myprefs;

    private RequestQueue queue;

    private ProgressDialog progress;

    DbMethods dbMethods;

    public LoginFragment() {
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    ViewPager viewPager;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        viewPager = (ViewPager)getActivity().findViewById(R.id.container);

//        Button logButton = (Button)rootView.findViewById(R.id.log_but);
//        logButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), HomeActivity.class);
//                SharedPreferences.Editor editor = getActivity().getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE).edit();
//                editor.putBoolean("LoginStat",true);
//                editor.apply();
//                Toast.makeText(getActivity(),"Logged In Successfully",Toast.LENGTH_SHORT).show();
//                startActivity(intent);
//                getActivity().finish();
//            }
//        });

//        Button signButton = (Button)rootView.findViewById(R.id.sign_but);
//        signButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                viewPager.setCurrentItem(1);
//            }
//        });

        dbMethods = new DbMethods(getActivity());

        Button fogButton = (Button)rootView.findViewById(R.id.btn_forgot_password);
        fogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

                // set title
                /*alertDialogBuilder.setTitle("CONFIRM COUPONS");*/

                // create alert dialog
                final AlertDialog alertDialog = alertDialogBuilder.create();
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.activity_forgot_password, null);
                alertDialog.setView(dialogView);

                btnForgotSubmit = (Button)dialogView.findViewById(R.id.btn_forgot_submit);
                etEmail = (EditText)dialogView.findViewById(R.id.et_forgot_email);

                btnForgotSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        progress = new ProgressDialog(getActivity());
                        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        progress.setMessage("Sending reset link. Please wait...");
                        progress.setCancelable(false);
                        progress.setIndeterminate(true);
                        progress.show();

                        String email = etEmail.getText().toString();

                        queue = Volley.newRequestQueue(getActivity());


                        String url = FORGOT_PASSWORD_URL;
                        // mViewPager.setVisibility(View.GONE);
                        //menuProgressLayout.setVisibility(View.VISIBLE);


                        Map<String, String> params = new HashMap<String, String>();
                        params.put("email", email.trim());
                        Log.d("PARAMS FORGOT PASSWORD", params.toString());

                        JsonObjectRequest strReq = new JsonObjectRequest(Request.Method.POST,
                                url, new JSONObject(params), new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject resp) {
                                Log.d("FORGOT PASSWORD", resp.toString());


                                try {
                                    Log.d("FORGOT PASSWORD", "Login parser executed!");

                                    int status = resp.getInt("status");
                                    progress.dismiss();
                                    if (status == 1) {
                                        Toast.makeText(getActivity(), "Password reset process initiated successfully! Please check your email for reset link.", Toast.LENGTH_LONG).show();
                                        alertDialog.dismiss();
                                    } else if (status == 0){
                                        Toast.makeText(getActivity(), "Email not found!", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(getActivity(), "Some error occurred. Please try again.", Toast.LENGTH_SHORT).show();
                                    }
                                    Log.d("FORGOT PASSWORD", "Password reset parser executed properly!");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    progress.dismiss();
                                    Log.d("FORGOT PASSWORD", "Password reset parser failed!");
                                }


                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                VolleyLog.d("FORGOT PASSWORD", "Error: " + error.getMessage());
                                progress.dismiss();
                                Toast.makeText(getActivity(),"Network Unreachable!",Toast.LENGTH_SHORT).show();
                            }
                        }){
                            @Override
                            public Map<String, String> getHeaders() throws AuthFailureError {
                                Map<String, String> headers = new HashMap<String, String>();
                                headers.put("Content-Type", "application/json");
                                Log.d("HEADERS FORGOT PASSWORD", headers.toString());
                                return headers;
                            }
                        };
                        strReq.setRetryPolicy(new DefaultRetryPolicy(
                                30000,
                                0,
                                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                        queue.add(strReq);

                    }
                });

                /*proceed=(Button)dialogView.findViewById(R.id.proceed);
                proceed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getActivity().finish();
                    }
                });
                cancel=(Button)dialogView.findViewById(R.id.cancel);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogInterface dialog = alertDialog;
                        dialog.cancel();
                    }
                });*/
                // show i
                alertDialog.show();

            }
        });


        myprefs= PreferenceManager.getDefaultSharedPreferences(getActivity());

        etEmail = (EditText) rootView.findViewById(R.id.et_email);
        etPassword = (EditText) rootView.findViewById(R.id.et_password);

        btnRegister = (Button)rootView.findViewById(R.id.btn_register);
        btnLogin = (Button)rootView.findViewById(R.id.btn_login);

        layoutLogin = (LinearLayout) rootView.findViewById(R.id.layout_login);
//        layoutProgress = (LinearLayout) rootView.findViewById(R.id.progress_layout_login);

        if(myprefs.getBoolean(REGISTERED,false)){
            Intent intent = new Intent(getActivity(), HomeActivity.class);
            startActivity(intent);
        }

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.container);
                viewPager.setCurrentItem(1);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login();

//                Intent intent = new Intent(getActivity(), HomeActivity.class);
//                startActivity(intent);
//                getActivity().finish();
            }
        });


        return rootView;
    }

    public void login() {

        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        boolean cancel = false;

        if (email.length() == 0) {
            etEmail.setError("Email is required!");
            cancel = true;
        }

        if (password.length() == 0) {
            etPassword.setError("Password is required!");
            cancel = true;
        }

        else if (!email.contains("@")) {
            etEmail.setError("Enter a valid email address!");
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.

        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            layoutLogin.setVisibility(View.GONE);
//            btnRegister.setVisibility(View.GONE);
//            layoutProgress.setVisibility(View.VISIBLE);
            progress = new ProgressDialog(getActivity());
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setMessage("Signing In...");
            progress.setCancelable(false);
            progress.setIndeterminate(true);
            progress.show();
//            String regId=myprefs.getString(PROPERTY_REG_ID, "");
//            registerRemote(regId, name, email, password, contact);
            actionLogin(email, password);
        }
    }

    public void actionLogin(final String email, final String password) {

        final String TAG = "TECHNEX NETWORK LOGIN";

        queue = Volley.newRequestQueue(getActivity());


        String url = LOGIN_URL;
        // mViewPager.setVisibility(View.GONE);
        //menuProgressLayout.setVisibility(View.VISIBLE);


        Map<String, String> params = new HashMap<String, String>();
        params.put("email", email.trim());
        params.put("password", password.trim());
        Log.d("PARAMS LOGIN", params.toString());

        JsonObjectRequest strReq = new JsonObjectRequest(Request.Method.POST,
                url, new JSONObject(params), new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject resp) {
                Log.d(TAG, resp.toString());


                try {
                    Log.d(TAG, "Login parser executed!");

                    int status = resp.getInt("status");
                    //save and and to my server
                    if (status == 1) {
                        SharedPreferences.Editor editor=myprefs.edit();
                        editor.putBoolean(REGISTERED,true);
                        editor.putString(EMAIL, email);
                        editor.putString(PASSWORD, password);
                        editor.putString(CONTACT, resp.getString("mobileNumber"));
                        editor.putString(NAME, resp.getString("name"));
                        editor.putString(COLLEGE, resp.getString("college"));
                        editor.putString(YEAR, resp.getString("year"));
                        editor.putString(PIN, resp.getString("pin"));
                        editor.putString(TECHNEX_ID, resp.getString("technexId"));
                        editor.commit();

                        progress.dismiss();

                        Intent intent = new Intent(getActivity(), HomeActivity.class);
                        startActivity(intent);
                        getActivity().finish();

                        String notifTitle1, notifTitle2, notifTitle3, notifDesc1, notifDesc2, notifDesc3;

                        notifTitle1 = "Welcome to Technex '18";
                        notifDesc1 = "Hi " + myprefs.getString(NAME, "Technex User") + ", experience Technex like never before with the  Official Technex '18 Android Application.";

                        notifTitle3 = "Important Guidelines";
                        notifDesc3 = "Your e-ID Card will be verified by the officials at the registration desk through a secret PIN. You cannot log in on more than one device at a time, so to avoid any conflicts, we recommend you to log out only through the logout button. Tampering and duplicating the e-ID Card will be penalised accordingly.";

                        notifTitle2 = "The e-ID Card";
                        notifDesc2 = "This App features the new secure e-ID Card. This e-ID Card, once verified, will serve as the entrance pass for Exhibitions, Think Talks and other events and activities of the fest.";



                        long id1 = dbMethods.insertUpdates(notifDesc1, System.currentTimeMillis(), notifTitle1);
                        sendNotification(id1, notifDesc1, notifTitle1);


                        long id2 = dbMethods.insertUpdates(notifDesc2, System.currentTimeMillis(), notifTitle2);
                        sendNotification(id2, notifDesc2, notifTitle2);


                        long id3 = dbMethods.insertUpdates(notifDesc3, System.currentTimeMillis(), notifTitle3);
                        sendNotification(id3, notifDesc3, notifTitle3);

                    } else if (status == 3) {
                        layoutLogin.setVisibility(View.VISIBLE);
                        progress.dismiss();
                        Toast.makeText(getActivity(), "User already logged in on another device. Try again after logging out.", Toast.LENGTH_LONG).show();
                    } else if (status == 0) {
                        layoutLogin.setVisibility(View.VISIBLE);
                        progress.dismiss();
                        Toast.makeText(getActivity(), "Wrong email/password",Toast.LENGTH_LONG).show();
                    }

                    else{
                        layoutLogin.setVisibility(View.VISIBLE);
                        progress.dismiss();
                        Toast.makeText(getActivity(), "Some error occurred. Please try again.",Toast.LENGTH_SHORT).show();
                    }
                    Log.d(TAG, "Login parser executed properly!");
                } catch (JSONException e) {
                    progress.dismiss();
                    e.printStackTrace();
                    Log.d(TAG, "Login parser failed!");
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                layoutLogin.setVisibility(View.VISIBLE);
                progress.dismiss();
                Toast.makeText(getActivity(),"Network Unreachable!",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                Log.d("HEADERS LOGIN", headers.toString());
                return headers;
            }
        };
        strReq.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(strReq);

    }

    private void sendNotification(long id, String messageBody, String title) {
        Intent intent = new Intent(getActivity(), Splash.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        long[] pattern = {500,500,500,500,500};

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getActivity())
                .setSmallIcon(R.drawable.logo_white)
                .setContentTitle(title)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(messageBody))
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setVibrate(pattern)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify((int)id, notificationBuilder.build());
    }
}

