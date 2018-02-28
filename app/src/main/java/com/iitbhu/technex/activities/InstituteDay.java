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

public class InstituteDay extends AppCompatActivity implements DbConstants{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institute_day);
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

        String intro = "4th edition of Institute Day with Technex\\u201918";
        String content = "The institute is going to organize the 4th edition of Institute Day from 16th to 18th of February 2018 with Technex\\u201918 to showcase the research and innovation carried out by our students. It is also an occasion to show the strength of the departments and schools in terms of equipment and facilities available to the interested people from inside as well as outside. On this occasion all laboratories and other major facilities will be open to the visitors during working hours.";

        Cursor cursor = dbMethods.queryInstituteDay(null, null, null, null, null, COL_INSTITUTE_DAY_ID);

        while (cursor.moveToNext()) {
            intro = cursor.getString(cursor.getColumnIndex(COL_INSTITUTE_DAY_INTRODUCTION));
            content = cursor.getString(cursor.getColumnIndex(COL_INSTITUTE_DAY_CONTENT));
        }


        ModTextView tvIntro = (ModTextView)findViewById(R.id.tv_institute_day_intro);
        ModTextView tvContent = (ModTextView)findViewById(R.id.tv_institute_day_content);

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
