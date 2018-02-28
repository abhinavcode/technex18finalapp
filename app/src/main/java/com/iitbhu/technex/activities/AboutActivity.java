package com.iitbhu.technex18.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.iitbhu.technex18.R;
import com.iitbhu.technex18.adapters.TeamAdapter;
import com.iitbhu.technex18.database.DbMethods;

import java.util.ArrayList;

import static com.iitbhu.technex18.database.DbConstants.COL_DESIGNATION;
import static com.iitbhu.technex18.database.DbConstants.COL_PEOPLE_CONTACT;
import static com.iitbhu.technex18.database.DbConstants.COL_PEOPLE_EMAIL;
import static com.iitbhu.technex18.database.DbConstants.COL_PEOPLE_NAME;
import static com.iitbhu.technex18.database.DbConstants.COL_PEOPLE_PIC;

public class AboutActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    DbMethods dbMethods;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        dbMethods = new DbMethods(AboutActivity.this);

        Cursor cursor = dbMethods.queryPeople(null, null, null, null, null, null);


        ArrayList<String> nameList = new ArrayList<String>();
        ArrayList<String> numberList = new ArrayList<String>();
        ArrayList<String> emailList = new ArrayList<String>();
        ArrayList<String> imageList = new ArrayList<String>();
        ArrayList<String> designationList= new ArrayList<>();
        while (cursor.moveToNext()) {
            nameList.add(cursor.getString(cursor.getColumnIndex(COL_PEOPLE_NAME)));
            designationList.add(cursor.getString(cursor.getColumnIndex(COL_DESIGNATION)));
            emailList.add((cursor.getString(cursor.getColumnIndex(COL_PEOPLE_EMAIL))));
            imageList.add(cursor.getString(cursor.getColumnIndex(COL_PEOPLE_PIC)));
            numberList.add(cursor.getString(cursor.getColumnIndex(COL_PEOPLE_CONTACT)));
        }

        final String[] names = nameList.toArray(new String[nameList.size()]);
        final String[] number = numberList.toArray(new String[numberList.size()]);
        final String[] desg = designationList.toArray(new String[designationList.size()]);
        final String[] images = imageList.toArray(new String[imageList.size()]);
        final String[] email = emailList.toArray(new String[emailList.size()]);



        mRecyclerView = (RecyclerView)findViewById(R.id.PeopleList);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
//        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        mAdapter = new TeamAdapter(this,names,number,desg,email,images);
        mRecyclerView.setAdapter(mAdapter);

    }

}
