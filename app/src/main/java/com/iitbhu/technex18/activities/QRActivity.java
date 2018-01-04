package com.iitbhu.technex18.activities;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;

import com.iitbhu.technex18.R;
import com.iitbhu.technex18.utils.Constants;
import com.iitbhu.technex18.utils.Contents;
import com.iitbhu.technex18.utils.QRCodeEncoder;

public class QRActivity extends AppCompatActivity implements Constants {

    SharedPreferences myprefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView tvTechnexId = (TextView)findViewById(R.id.tv_qr_technex_id);

        ImageView imgQR = (ImageView)findViewById(R.id.img_qr);

        myprefs = PreferenceManager.getDefaultSharedPreferences(this);

        tvTechnexId.setText(myprefs.getString(TECHNEX_ID, ""));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String QRInput = myprefs.getString(TECHNEX_ID, "");
        Log.d("QR Activity", QRInput);

        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        int height = point.y;
        int smallerDimension = width < height ? width : height;

        QRCodeEncoder qrCodeEncoder = new QRCodeEncoder(QRInput,
                null,
                Contents.Type.TEXT,
                BarcodeFormat.QR_CODE.toString(),
                smallerDimension);
        try {
            Bitmap bitmap = qrCodeEncoder.encodeAsBitmap();
            imgQR.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
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

}
