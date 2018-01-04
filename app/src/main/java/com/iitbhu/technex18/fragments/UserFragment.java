package com.iitbhu.technex18.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import org.json.JSONException;
import org.json.JSONObject;
import com.iitbhu.technex18.R;
import com.iitbhu.technex18.activities.LoginActivity;
import com.iitbhu.technex18.activities.QRActivity;
import com.iitbhu.technex18.database.DbMethods;
import com.iitbhu.technex18.network.URLs;
import com.iitbhu.technex18.utils.BoldModTextView;
import com.iitbhu.technex18.utils.Constants;
import com.iitbhu.technex18.utils.ModTextView;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by root on 18/9/16.
 */
public class UserFragment extends Fragment implements Constants, URLs {

   /* LinearLayout nameLayout, emailLayout, contactLayout, collegeLayout, yearLayout;
    ModTextView boldName, nameHeader, nameValue, emailHeader, emailValue, contactHeader, contactValue, collegeHeader, collegeValue, yearHeader, yearValue;
    */

    BoldModTextView tvInitial, tvName;

    ModTextView tvTechnexId, tvCollege, tvYear, tvEmail, tvContact, tvHostel, tvVerified;

    SharedPreferences myprefs;

    Button btnQR, btnLogout, btnPin;

    RequestQueue queue;

    private ProgressDialog progress;

    LinearLayout layoutIdCard;

    DbMethods dbMethods;

    ImageView ivTechnex;
    int logoStat=0;

    Timer timer;
    TimerTask timerTask;

    public UserFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static UserFragment newInstance() {
        UserFragment fragment = new UserFragment();
        return fragment;
    }

    @Override
    public void onPause() {
        super.onPause();
        timer.cancel();
    }

    @Override
    public void onResume() {
        super.onResume();

        timer = new Timer();
        timerTask = new TimerTask() {

            @Override
            public void run() {
                animate(logoStat);
                logoStat++;
            }
        };

        timer.scheduleAtFixedRate(timerTask, 0, 250);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_usertab, container, false);


        tvInitial = (BoldModTextView)rootView.findViewById(R.id.tv_user_initial);
        tvName = (BoldModTextView)rootView.findViewById(R.id.tv_user_name);
        tvVerified = (ModTextView)rootView.findViewById(R.id.tv_verified);

        tvCollege = (ModTextView)rootView.findViewById(R.id.tv_user_college);
        tvYear = (ModTextView)rootView.findViewById(R.id.tv_user_year);
        tvEmail = (ModTextView)rootView.findViewById(R.id.tv_user_email);
        tvContact = (ModTextView)rootView.findViewById(R.id.tv_user_contact);
        tvTechnexId = (ModTextView)rootView.findViewById(R.id.tv_user_technex_id);
        tvHostel = (ModTextView)rootView.findViewById(R.id.tv_hostel);

        btnQR = (Button) rootView.findViewById(R.id.btn_qr);
        btnLogout = (Button)rootView.findViewById(R.id.btn_user_logout);
        btnPin = (Button)rootView.findViewById(R.id.btn_pin);

        ivTechnex = (ImageView)rootView.findViewById(R.id.ivTechnex) ;

        layoutIdCard = (LinearLayout) rootView.findViewById(R.id.layout_id_card);

        //  MaterialViewPagerHelper.registerScrollView(getActivity(), mScrollView, null);

        myprefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        dbMethods = new DbMethods(getActivity());

        if (myprefs.getBoolean(VERIFIED, false)) {
            tvVerified.setVisibility(View.VISIBLE);
            btnLogout.setVisibility(View.GONE);
            btnPin.setVisibility(View.GONE);
            tvHostel.setText(myprefs.getString(HOSTEL, ""));
            layoutIdCard.setBackgroundResource(R.drawable.gradientgreendark);
            ivTechnex.setVisibility(View.VISIBLE);
        }

        queue = Volley.newRequestQueue(getActivity());


        tvInitial.setText(myprefs.getString(NAME, "a").substring(0,1));
        tvName.setText(myprefs.getString(NAME, ""));
        tvCollege.setText(myprefs.getString(COLLEGE, ""));
        tvYear.setText("Year : " + myprefs.getString(YEAR, ""));
        tvEmail.setText(myprefs.getString(EMAIL, ""));
        tvContact.setText(myprefs.getString(CONTACT, ""));
        tvTechnexId.setText(myprefs.getString(TECHNEX_ID, ""));


        /*boldName = (ModTextView)rootView.findViewById(R.id.bold_name);
        boldName.setText(myprefs.getString(NAME, "User"));

        nameLayout = (LinearLayout)rootView.findViewById(R.id.text_name);
        emailLayout = (LinearLayout)rootView.findViewById(R.id.text_email);
        contactLayout = (LinearLayout)rootView.findViewById(R.id.text_contact);
        collegeLayout = (LinearLayout)rootView.findViewById(R.id.text_college);
        yearLayout = (LinearLayout)rootView.findViewById(R.id.text_year);

        nameHeader = (ModTextView)nameLayout.findViewById(R.id.field_text);
        nameValue = (ModTextView)nameLayout.findViewById(R.id.value_text);
        emailHeader = (ModTextView)emailLayout.findViewById(R.id.field_text);
        emailValue = (ModTextView)emailLayout.findViewById(R.id.value_text);
        contactHeader = (ModTextView)contactLayout.findViewById(R.id.field_text);
        contactValue = (ModTextView)contactLayout.findViewById(R.id.value_text);
        collegeHeader = (ModTextView)collegeLayout.findViewById(R.id.field_text);
        collegeValue = (ModTextView)collegeLayout.findViewById(R.id.value_text);
        yearHeader = (ModTextView)yearLayout.findViewById(R.id.field_text);
        yearValue = (ModTextView)yearLayout.findViewById(R.id.value_text);

        nameHeader.setText("Name");
        nameValue.setText(myprefs.getString(NAME, "Name"));
        emailHeader.setText("Email");
        emailValue.setText(myprefs.getString(EMAIL, "Email"));
        contactHeader.setText("Contact");
        contactValue.setText(myprefs.getString(CONTACT, "Contact"));
        collegeHeader.setText("College");
        collegeValue.setText(myprefs.getString(COLLEGE, "College"));
        yearHeader.setText("Year");
        yearValue.setText(myprefs.getString(YEAR, "Year"));*/


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //                SharedPreferences.Editor editor = getActivity().getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE).edit();
//                editor.putBoolean("LoginStat",false);
//                editor.apply();


                progress = new ProgressDialog(getActivity());
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.setMessage("Logging out...");
                progress.setCancelable(false);
                progress.setIndeterminate(true);
                progress.show();

                String url = LOGOUT_URL;
                // mViewPager.setVisibility(View.GONE);
                //menuProgressLayout.setVisibility(View.VISIBLE);


                Map<String, String> params = new HashMap<String, String>();
                params.put("email", myprefs.getString(EMAIL, ""));
                Log.d("PARAMS LOGOUT", params.toString());

                JsonObjectRequest strReq = new JsonObjectRequest(Request.Method.POST,
                        url, new JSONObject(params), new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject resp) {
                        Log.d("LOGOUT", resp.toString());

                        progress.dismiss();

                        try {
                            Log.d("LOGOUT", "Logout parser executed!");

                            int status = resp.getInt("status");
                            if (status == 1) {
                                SharedPreferences.Editor editor=myprefs.edit();
                                editor.clear();
                                editor.putLong(LAST_UPDATE_TIME,0);
                                editor.commit();
                                dbMethods.deleteAllUpdates();
                                Intent intent = new Intent(getActivity(), LoginActivity.class);
                                startActivity(intent);
                                getActivity().finish();

                                Toast.makeText(getActivity(), "Logged out successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), "Some error occured. Please try again later.", Toast.LENGTH_SHORT).show();
                            }
                            Log.d("LOGOUT", "Logout parser executed properly!");
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("LOGOUT", "Logout parser failed!");
                        }


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d("LOGOUT", "Error: " + error.getMessage());
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




//        Button morebutton = (Button)rootView.findViewById(R.id.btn_more);
//        morebutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
//
//                // set title
//                /*alertDialogBuilder.setTitle("CONFIRM COUPONS");*/
//
//                // create alert dialog
//                final AlertDialog alertDialog = alertDialogBuilder.create();
//                LayoutInflater inflater = getActivity().getLayoutInflater();
//                View dialogView = inflater.inflate(R.layout.activity_moreoptions, null);
//                alertDialog.setView(dialogView);
//
////                final Button proceed, cancel;
//
//                /*proceed=(Button)dialogView.findViewById(R.id.proceed);
//                proceed.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        getActivity().finish();
//                    }
//                });
//                cancel=(Button)dialogView.findViewById(R.id.cancel);
//                cancel.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        DialogInterface dialog = alertDialog;
//                        dialog.cancel();
//                    }
//                });*/
//                // show i
//                alertDialog.show();
//
//            }
//        });

        btnQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), QRActivity.class);
                startActivity(intent);
            }
        });

        btnPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                final AlertDialog alertDialog = alertDialogBuilder.create();
                LayoutInflater inflater = getActivity().getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.dialog_pin, null);
                alertDialog.setView(dialogView);
                alertDialog.show();

                final EditText etPin = (EditText) dialogView.findViewById(R.id.et_pin);

                final Button btnSubmitPin = (Button) dialogView.findViewById(R.id.btn_pin_submit);
                btnSubmitPin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Log.d("SUBMIT PIN", etPin.getText().toString() + "   " + myprefs.getString(PIN, "as"));

                        boolean cancel = false;

                        if (etPin.getText().length() != 5) {
                            etPin.setError("Invalid PIN Format");
                            cancel = true;
                        }

                        if (cancel) {

                        } else {

                            String pin = etPin.getText().toString(), hostelCode = "", code = "";

                            int position = 0;

                            boolean cancel2 = true;

                            try {
                                cancel2 = false;
                                position = Integer.parseInt(pin.substring(4, 5)) % 4;
                            } catch (Exception e) {
                                cancel2 = true;
                                e.printStackTrace();
                            }
                            for (int i = 0; i < 4; i++) {
                                if (i == position) {
                                    hostelCode = pin.substring(i, i + 1).toUpperCase();
                                } else {
                                    code = code + pin.substring(i, i + 1);
                                }
                            }

                            Log.d("CODE PIN", code);

                            if (code.equals(myprefs.getString(PIN, ""))) {
                                layoutIdCard.setBackgroundResource(R.drawable.gradientgreendark);
                                btnPin.setVisibility(View.GONE);
                                ivTechnex.setVisibility(View.VISIBLE);
                                Toast.makeText(getActivity(), "Successfully verified!", Toast.LENGTH_LONG).show();
                                String hostel = "No accomodation";
                                if (hostelMap.containsKey(hostelCode.toUpperCase())) {
                                    hostel = hostelMap.get(hostelCode.toUpperCase());
                                }
                                if (cancel2) {
                                    etPin.setError("Invalid PIN Format");
                                } else {
                                    tvHostel.setText(hostel);
                                    tvVerified.setVisibility(View.VISIBLE);
                                    btnLogout.setVisibility(View.GONE);
                                    SharedPreferences.Editor editor = myprefs.edit();
                                    editor.putBoolean(VERIFIED, true);
                                    editor.putString(HOSTEL, hostel);
                                    editor.commit();
                                    alertDialog.dismiss();
                                }
                            } else {
                                etPin.setError("Enter correct PIN");
                            }
                        }
                    }
                });
            }
        });



        return rootView;

    }

    public void animate(final int stat) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                switch (stat%6){
                    case 0:
                        ivTechnex.setBackgroundResource(R.drawable.ic_tech_6);
                        break;
                    case 1:
                        ivTechnex.setBackgroundResource(R.drawable.ic_tech_5);
                        break;
                    case 2:
                        ivTechnex.setBackgroundResource(R.drawable.ic_tech_4);
                        break;
                    case 3:
                        ivTechnex.setBackgroundResource(R.drawable.ic_tech_3);
                        break;
                    case 4:
                        ivTechnex.setBackgroundResource(R.drawable.ic_tech_2);
                        break;
                    case 5:
                        ivTechnex.setBackgroundResource(R.drawable.ic_tech_1);
                        break;
                    default:
                        ivTechnex.setBackgroundResource(R.drawable.ic_tech_1);
                        break;

                }

            }
        });

    }
}
