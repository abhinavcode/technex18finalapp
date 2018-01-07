package com.iitbhu.technex18.utils;

/**
 * Created by mayank on 3/2/17.
 */

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.iitbhu.technex18.R;
import com.iitbhu.technex18.activities.Splash;
import com.iitbhu.technex18.database.DbMethods;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Map;
import java.util.Random;

/**
 * Created by Mayank on 2/4/2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    DbMethods dbMethods;
    int db=0;
    String title = "Technex '18";
    String body = "";
    String image="";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //Displaying data in log
        //It is optional
        Log.d(TAG, "From: " + remoteMessage.getFrom());
//        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());

        //Calling method to generate notification
//        sendNotification(remoteMessage.getNotification().getBody());
        dbMethods = new DbMethods(this);


        Map data = remoteMessage.getData();
        Log.d(TAG, data.toString());

        try {
            body = (String) data.get("body");
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("NOTIF BODY CATCH", e.toString());
        }
        try {
            title = (String) data.get("title");
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("NOTIF TITLE CATCH", e.toString());
        }

        try {
            image = (String) data.get("image");
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("NOTIF IMAGE CATCH", e.toString());
        }
        try {
            db = Integer.parseInt((String) data.get("db"));
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("NOTIF db CATCH", e.toString());
        }
        if (title.equals("")) {
            title = "Technex '18";
        }
        String TAG="NOTIF";
        Random r=new Random();
        long id= r.nextInt();
        Log.d("db val",""+db);
        if(db==1)
            id = dbMethods.insertUpdates(body, Calendar.getInstance().getTimeInMillis(), title);

        if(image==null) {
            Log.d(TAG,"without image");
            sendNotification(id, body, title);
        }
        else {
            Log.d(TAG,"with image");
            Bitmap bitmap = getBitmapfromUrl(image);

            sendNotification(id, body, title, bitmap);
        }
    }

    //This method is only generating push notification
    //It is same as we did in earlier posts
    private void sendNotification(long id, String messageBody, String title) {
        Intent intent = new Intent(this, Splash.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        long[] pattern = {500,500,500,500,500};


        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.logo_white)
                .setContentTitle(title)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(messageBody))
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setVibrate(pattern)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify((int)id, notificationBuilder.build());
    }

    //This method is only generating push notification
    //It is same as we did in earlier posts
    private void sendNotification(long id, String messageBody, String title,Bitmap image) {
        Intent intent = new Intent(this, Splash.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        long[] pattern = {500,500,500,500,500};


        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.logo_white)
                .setLargeIcon(image)
                .setContentTitle(title)
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(image).setSummaryText(messageBody))
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setTicker("Technex '18")
                .setVibrate(pattern)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify((int)id, notificationBuilder.build());
    }

    /*
   *To get a Bitmap image from the URL received
   * */
    public Bitmap getBitmapfromUrl(String imageUrl) {
        Log.d("BITMAP","image down");
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.d("BITMAP","image cant");
            e.printStackTrace();
            return null;

        }
    }
}