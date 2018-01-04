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

public class Exhibitions extends AppCompatActivity implements DbConstants {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhibitions);
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

        String intro = "Numerous exhibitions are held at Technex to showcase some of the most interesting things, students are capable of creating, which will dominate in the field of technology in the future world.";
        String content = "ORNITHOPTER\n" +
                "An aircraft that flies flapping its wings imitating the size and wing-flapping of birds, bats and insects.\n" +
                " \n" +
                "ROBO-HAND\n" +
                "An Arduino based project that can move its fingers and pick objects using concept of conductive material with the electronics.\n" +
                "\n" +
                "VR SIMULATOR\n" +
                "VR simulator is ready to bring an extravaganza of roller coaster ride and take the entertainment to another level.\n" +
                "\n" +
                "BOWLING ROBOT\n" +
                "\n" +
                "Bowling Bot is an autonomous robot made using Lego & Tetrix programable kit. It has the ability to sense the path and obstacles and move to the location to pick up the bowling ball then aim using sensors and then hit the pins.  \n" +
                "\n" +
                "GREEN BUILDING MATERIALS\n" +
                "Presenting Green building materials aiming towards sustainability. They are eco-friendly providing ambient climatic conditions, thus providing extreme comfort.\n" +
                "\n" +
                "GOLF SIMULATOR\n" +
                "\n" +
                "virtual golf simulator has all the latest features and technology which will make your golfing experience real and enjoyable.\n" +
                "\n" +
                "HUMANOID BOT: INDRO\n" +
                "\n" +
                "INDRO was not built in a laboratory or a high-end workshop, but instead it was built inside a house with minimal basic tools like aluminium, wood, cardboard, plastic etc.\n" +
                "\n" +
                "BRAIN-CONTROLLED DRONE\n" +
                "\n" +
                "The Puzzlebox Orbit is a brain-controlled helicopter.\n" +
                "\n" +
                "3D-PRINTER\n" +
                "Will synthesize a three-dimensional objects in which successive layers of material are formed under computer control to create an object.\n" +
                "\n" +
                "CUBE-SOLVER BOT\n" +
                "\n" +
                "Construction of a cube solving robot is a fascinating combination of image processing that uses\n" +
                "Graphical User Interface, analysis methods and the controller and motor to launch the computed movement to resolve the puzzle.\n" +
                "\n" +
                "BIO-BOT\n" +
                "\n" +
                "Bio-bot is a motorized bot which use different sensors like IR, ultrasonic, distance sensors and powerful actuators.\n" +
                "\n" +
                "INDUSTRIAL ROBOT\n" +
                "This robot is a prototype of industrial robots constructed using a basic concept of switch and algorithm based programming.\n" +
                "\n" +
                "NAO\n" +
                "\n" +
                "Introducing to you all “The humanoid robot NAO” which can walk, talk, move around and performs\n" +
                "user defined tasks.";

        Cursor cursor = dbMethods.queryExhibitions(null, null, null, null, null, COL_EXHIBITIONS_ID);

        while (cursor.moveToNext()) {
            intro = cursor.getString(cursor.getColumnIndex(COL_EXHIBITIONS_INTRODUCTION));
            content = cursor.getString(cursor.getColumnIndex(COL_EXHIBITIONS_CONTENT));
        }


        ModTextView tvIntro = (ModTextView)findViewById(R.id.tv_exhibitions_intro);
        ModTextView tvContent = (ModTextView)findViewById(R.id.tv_exhibitions_content);

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
