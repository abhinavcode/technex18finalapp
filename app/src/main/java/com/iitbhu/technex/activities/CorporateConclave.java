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

public class CorporateConclave extends AppCompatActivity implements DbConstants {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corporate_conclave);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.GONE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DbMethods dbMethods = new DbMethods(this);

        String intro = "Technex ’17 presents before you \"Corporate Conclave\", which brings together the biggest corporate stalwarts of present times.";
        String content = "The conclave aims to establish a platform through which the students will interact with the executives, to prepare themselves for the corporate battle ahead. The conclave will consist of interactive workshops and discussions between company executives and students, to help foster a better knowledge and awareness of the know-how of the industry.\n" +
                "\n" +
                "The corporate giants include Larsen & Toubro, Samsung, Citrix and Citi.";

        Cursor cursor = dbMethods.queryCorporateConclave(null, null, null, null, null, COL_CORPORATE_CONCLAVE_ID);

        while (cursor.moveToNext()) {
            intro = cursor.getString(cursor.getColumnIndex(COL_CORPORATE_CONCLAVE_INTRODUCTION));
            content = cursor.getString(cursor.getColumnIndex(COL_CORPORATE_CONCLAVE_CONTENT));
        }


        ModTextView tvIntro = (ModTextView)findViewById(R.id.tv_corporate_intro);
        ModTextView tvContent = (ModTextView)findViewById(R.id.tv_corporate_content);

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
