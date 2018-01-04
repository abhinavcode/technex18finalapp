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

public class StartupFair extends AppCompatActivity implements DbConstants{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup_fair);
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

        String intro = "The biggest national convention for young entrepreneurs is organized by IIT(BHU) Varanasi in association with MCIIE. It features a plethora of events including guest lectures, elevator pitch, workshops, Networking Meet and panel discussions.";
        String content = "The goal of Startup Fair is to give something to the startup community by introducing emerging startups to resources, mentorship, and investment opportunities. It is most interested in working with entrepreneurs using data management to solve public policy, human development, and business challenges.\n" +
                "\n" +
                "The VC’s include- Mr. Rajiv Jamkhedkar, Mr. G.S. Naveen Kumar, Mr. Vinod Dham, Dr P.K. Mishra and Mr. Anil Joshi.\n" +
                "For more details visit: http://www.technex.in/startupfair/";

        Cursor cursor = dbMethods.queryStartupFair(null, null, null, null, null, COL_STARTUP_FAIR_ID);

        while (cursor.moveToNext()) {
            intro = cursor.getString(cursor.getColumnIndex(COL_STARTUP_FAIR_INTRODUCTION));
            content = cursor.getString(cursor.getColumnIndex(COL_STARTUP_FAIR_CONTENT));
        }


        ModTextView tvIntro = (ModTextView)findViewById(R.id.tv_startup_fair_intro);
        ModTextView tvContent = (ModTextView)findViewById(R.id.tv_startup_fair_content);

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
