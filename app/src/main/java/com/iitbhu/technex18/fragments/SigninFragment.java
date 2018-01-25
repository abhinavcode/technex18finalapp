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
public class SigninFragment extends Fragment implements Constants, URLs{
    public SigninFragment() {

    }

    public static SigninFragment newInstance() {
        SigninFragment fragment = new SigninFragment();
        return fragment;
    }

    private static final int CAMERA_REQUEST = 1888;
//    private ImageView imageView;
//    private String MY_PREFS_NAME="MyPrefsFile";
    ViewPager viewPager;

    LinearLayout registerLayout;
    EditText etNameFirst, etNameLast, etEmail, etPassword, etPasswordConfirm, etContact, etCollege, etYear;

    SharedPreferences myprefs;

    DbMethods dbMethods;

    private RequestQueue queue;
    final int PIC_CROP = 2;
    private Uri picUri;


    private ProgressDialog progress;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_signin, container, false);

        viewPager = (ViewPager)getActivity().findViewById(R.id.container);
//        this.imageView = (ImageView)rootView.findViewById(R.id.imageviewreg);
//        imageView.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                try {  Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(cameraIntent, CAMERA_REQUEST);
//
//                Intent goEdit;
//                goEdit = new Intent(getActivity(), CropActivity.class);}
//
//                catch(ActivityNotFoundException anfe){
//                    //display an error message
//                    String errorMessage = "Whoops - your device doesn't support capturing images!";
//                    Toast toast = Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT);
//                    toast.show();
//                }
////                goEdit.putExtra("image-path", path);
////                goEdit.putExtra("scale", true);
////                goEdit.putExtra("fileName", nameFromPath);
////                //finish();
////                checkEdit = true;
////                startActivityForResult(goEdit,0);
//
//            }
//        });


        registerLayout = (LinearLayout)rootView.findViewById(R.id.layout_register);
//        progressLayout = (LinearLayout)rootView.findViewById(R.id.progress_layout_register);

        etNameFirst = (EditText)rootView.findViewById(R.id.et_name_first);
        etNameLast = (EditText)rootView.findViewById(R.id.et_name_last);
        etEmail = (EditText)rootView.findViewById(R.id.et_email_reg);
        etPassword = (EditText)rootView.findViewById(R.id.et_password_reg);
        etPasswordConfirm = (EditText)rootView.findViewById(R.id.et_password_confirm);
        etContact = (EditText)rootView.findViewById(R.id.et_contact);
        etCollege = (EditText)rootView.findViewById(R.id.et_college);
        etYear = (EditText)rootView.findViewById(R.id.et_year);

        myprefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        dbMethods = new DbMethods(getActivity());

        if(myprefs.getBoolean(REGISTERED,false)){
            Intent intent = new Intent(getActivity(), HomeActivity.class);
            startActivity(intent);
        }


//        Button regButton = (Button)rootView.findViewById(R.id.reg_but);
//        regButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), HomeActivity.class);
//                startActivity(intent);
//                SharedPreferences.Editor editor = getActivity().getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE).edit();
//                editor.putBoolean("LoginStat",true);
//                editor.apply();
//                Toast.makeText(getActivity(),"Registed Successfully",Toast.LENGTH_SHORT).show();
//                getActivity().finish();
//            }
//        });

        Button regButton = (Button)rootView.findViewById(R.id.reg_but);
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                register();
//
//                Intent intent = new Intent(getActivity(), HomeActivity.class);
//                startActivity(intent);
//                getActivity().finish();
            }
        });


        Button logBut = (Button)rootView.findViewById(R.id.log_but);
        logBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0);
            }
        });

        return rootView;
    }


    public void register() {

        String nameFirst = etNameFirst.getText().toString();
        String nameLast = etNameLast.getText().toString();
        String email = etEmail.getText().toString();
        String contact =etContact.getText().toString();
        String password =etPassword.getText().toString();
        String passwordConfirm =etPasswordConfirm.getText().toString();
        String year = etYear.getText().toString();
        String college = etCollege.getText().toString();


        boolean cancel = false;

        if(nameFirst.length()==0){
            etNameFirst.setError("Name cannot be empty!");
            cancel=true;
        }

        if(email.length()==0){
            etEmail.setError("Email is required!");
            cancel=true;
        }
        if(password.length()==0){
            etPassword.setError("Password is required!");
            cancel=true;
        }

        if(passwordConfirm.length()==0){
            etPasswordConfirm.setError("Enter the password here again!");
            cancel=true;
        }

        if(contact.length()==0){
            etContact.setError("Mobile no. cannot be empty !");
            cancel=true;
        }

        if (!password.equals(passwordConfirm)) {
            etPasswordConfirm.setError("Passwords do not match!");
            cancel = true;
        }

        if (!email.contains("@")) {
            etEmail.setError("Enter a valid email!");
            cancel = true;
        }

        if(contact.length()<10  || contact.length()>12){
            etContact.setError("Please enter your 10 digit phone number !");
            cancel = true;
        }

        if(college.length()==0){
            etCollege.setError("College name cannot be empty!");
            cancel=true;
        }

        if(year.length()==0){
            etNameFirst.setError("Year cannot be empty!");
            cancel=true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.

        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
//            showProgress(true);
            registerLayout.setVisibility(View.GONE);

            progress = new ProgressDialog(getActivity());
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setMessage("Registering and Signing in...");
            progress.setCancelable(false);
            progress.setIndeterminate(true);
            progress.show();
//            progressLayout.setVisibility(View.VISIBLE);
//            String regId=myprefs.getString(PROPERTY_REG_ID, "");
            actionRegister(nameFirst + " " + nameLast, email, password, contact, college, year);
        }

    }

    public void actionRegister(final String name, final String email, final String password,
                               final String contact, final String college, final String year) {

        final String TAG = "TECHNEX NETWORK REGISTER";

        queue = Volley.newRequestQueue(getActivity());


        String url = REGISTER_URL;

        Map<String, String> params = new HashMap<String, String>();
        params.put("email",email.trim());
        params.put("name", name);
        params.put("password",password);
        params.put("college",college);
        params.put("year",year);
        params.put("mobileNumber",contact);
        Log.d("PARAMS REGISTER",params.toString());

        JsonObjectRequest strReq = new JsonObjectRequest(Request.Method.POST,
                url, new JSONObject(params), new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject resp) {
                Log.d(TAG, resp.toString());


                try {
                    int status = resp.getInt("status");
                    if(status == 1){
                        //save and and to my server

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
                    } else if (status == 2) {
                        Toast.makeText(getActivity(),"Email already registered!",Toast.LENGTH_LONG).show();
                        registerLayout.setVisibility(View.VISIBLE);
                        progress.dismiss();
                    } else{
                        Toast.makeText(getActivity(),"Some error occurred. Please Try Again!",Toast.LENGTH_LONG).show();
                        registerLayout.setVisibility(View.VISIBLE);
                        progress.dismiss();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(),"Some error occurred. Please Try Again!",Toast.LENGTH_SHORT).show();
                    registerLayout.setVisibility(View.VISIBLE);
                    progress.dismiss();
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getActivity(),"Network Unreachable!",Toast.LENGTH_SHORT).show();
                registerLayout.setVisibility(View.VISIBLE);
                progress.dismiss();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                Log.d("HEADERS REGISTER", headers.toString());
                return headers;
            }
        };
        strReq.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(strReq);



    }


//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//     //   super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == CAMERA_REQUEST && resultCode == getActivity().RESULT_OK) {
//            Bitmap photo = (Bitmap) data.getExtras().get("data");
//            picUri = data.getData();
//
//            performCrop();
//            photo=getRoundedCornerBitmap(photo);
//            imageView.setImageBitmap(photo);
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setMaxWidth(50);
//            imageView.setMaxHeight(50);
//        }
//
//        if (requestCode == PIC_CROP) {
//            if (data != null) {
//                // get the returned data
//                Bundle extras = data.getExtras();
//                // get the cropped bitmap
//                Bitmap selectedBitmap = extras.getParcelable("data");
//
//                imageView.setImageBitmap(selectedBitmap);
//            }
//        }
//
//    }
//
//    private void performCrop() {
//        try {
//            Intent cropIntent = new Intent("com.android.camera.action.CROP");
//            // indicate image type and Uri
//            cropIntent.setDataAndType(picUri, "image/*");
//            // set crop properties here
//            cropIntent.putExtra("crop", true);
//            // indicate aspect of desired crop
//            cropIntent.putExtra("aspectX", 1);
//            cropIntent.putExtra("aspectY", 1);
//            // indicate output X and Y
//            cropIntent.putExtra("outputX", 128);
//            cropIntent.putExtra("outputY", 128);
//            // retrieve data on return
//            cropIntent.putExtra("return-data", true);
//            // start the activity - we handle returning in onActivityResult
//            startActivityForResult(cropIntent, PIC_CROP);
//        }
//        // respond to users whose devices do not support the crop action
//        catch (ActivityNotFoundException anfe) {
//            // display an error message
//            String errorMessage = "Whoops - your device doesn't support the crop action!";
//            Toast toast = Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT);
//            toast.show();
//        }
//    }
//
//
//    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
//        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
//                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(output);
//
//        final int color = 0xff424242;
//        final Paint paint = new Paint();
//        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
//        final RectF rectF = new RectF(rect);
//        final float roundPx = 12;
//
//        paint.setAntiAlias(true);
//        canvas.drawARGB(0, 0, 0, 0);
//        paint.setColor(color);
//        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
//
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//        canvas.drawBitmap(bitmap, rect, rect, paint);
//
//        return output;
//    }


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
