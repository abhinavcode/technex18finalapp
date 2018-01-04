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

public class Hospitality extends AppCompatActivity implements DbConstants{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitality);
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

        String intro = "Technex put its best efforts to make the visit to IIT(BHU) Varanasi, most comfortable and memorable, through its hospitality team which caters to the needs of participants as soon as they arrive the campus.";
        String content = "A. Accommodation will be provided from 23rd February, 4 p.m. for four days.\n" +
                "B. Participants are required to report at Registration Desk as soon as they arrive at the campus. \n" +
                "C. All students must carry valid College photo ID card and bring 2 passport sized photos.\n" +
                "D. Facilities provided:\n" +
                "    1: Passes to all exhibitions.\n" +
                "    2: Passes to all think talks.\n" +
                "    3: Passes to Kaleidoscope.\n" +
                "    4: Hospitality kit\n" +
                "E. Technex will not be responsible for the security of belongings of participants.\n" +
                "F. Participants are to bring the materials for their respective events.\n" +
                "G. The decision of Technex team will be final in all disputes.\n" +
                "\n" +
                "Contacts:\n" +
                "Rohit Negi- +919459494009\n" +
                "Rahul Yadav- +919956443498";

        Cursor cursor = dbMethods.queryHospitality(null, null, null, null, null, COL_HOSPITALITY_ID);

        while (cursor.moveToNext()) {
            intro = cursor.getString(cursor.getColumnIndex(COL_HOSPITALITY_INTRODUCTION));
            content = cursor.getString(cursor.getColumnIndex(COL_HOSPITALITY_CONTENT));
        }


        ModTextView tvIntro = (ModTextView)findViewById(R.id.tv_hospitality_intro);
        ModTextView tvContent = (ModTextView)findViewById(R.id.tv_hospitality_content);

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
