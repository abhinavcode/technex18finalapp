package com.iitbhu.technex18.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.iitbhu.technex18.R;
import com.iitbhu.technex18.database.DbConstants;
import com.iitbhu.technex18.database.DbMethods;
import com.iitbhu.technex18.utils.ModTextView;

public class Pronites extends AppCompatActivity implements DbConstants {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pronites);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DbMethods dbMethods = new DbMethods(this);

        String intro = "Technex brings to you the most awaited nights- ‘Kaleidoscope’, so feel the adrenaline rush and get ready to burst with laughter.";
        String content = "Sorabh Pant- one of the most successful comedians- has done over 250 shows in over 17 cities and has been rated amongst India’s top 10 stand-up comedians by Times of India. He is also famous for comedy specials like ‘Pant on fire’, ‘Travelling Pants’, and many others.\n" +
                "\n" +
                "Vipul Goyal, Abhishek Banerjee(Bhushi) and Rasika Dugal(Kayva)- the famous cast of TVF’s ‘Humorously Yours’ will also light up the stage at Technex’17.\n" +
                "\n" +
                "Live, feel and rejoice this Technex!!!";

        Cursor cursor = dbMethods.queryPronites(null, null, null, null, null, COL_PRONITES_ID);

        while (cursor.moveToNext()) {
            intro = cursor.getString(cursor.getColumnIndex(COL_PRONITES_INTRODUCTION));
            content = cursor.getString(cursor.getColumnIndex(COL_PRONITES_CONTENT));
        }


        ModTextView tvIntro = (ModTextView)findViewById(R.id.tv_pronites_intro);
        ModTextView tvContent = (ModTextView)findViewById(R.id.tv_pronites_content);

        if (!intro.equals("")) {
            tvIntro.setText(intro);
            tvContent.setText(content);
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
