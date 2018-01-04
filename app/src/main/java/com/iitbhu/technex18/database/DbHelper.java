package com.iitbhu.technex18.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.iitbhu.technex18.utils.Constants;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by shriyansh on 18/12/15.
 */
public class DbHelper extends SQLiteOpenHelper implements DbConstants, Constants{

    Context mContext;

    public DbHelper(Context context) {
        super(context,DB_NAME, null, DB_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables(db,TBL_WORKSHOP,COL_WORKSHOP);
        createTables(db,TBL_UPDATES,COL_UPDATES);
        createTables(db,TBL_GUEST_LECTURE,COL_GUEST_LECTURE);
        createTables(db,TBL_EVENTS,COL_EVENTS);
        createTables(db,TBL_EVENTS_OPTIONS,COL_EVENTS_OPTIONS);
        createTables(db,TBL_STARTUP_FAIR,COL_STARTUP_FAIR);
        createTables(db,TBL_PRONITES,COL_PRONITES);
        createTables(db,TBL_INSTITUTE_DAY,COL_INSTITUTE_DAY);
        createTables(db,TBL_CORPORATE_CONCLAVE,COL_CORPORATE_CONCLAVE);
        createTables(db,TBL_EXHIBITIONS,COL_EXHIBITIONS);
        createTables(db,TBL_HOSPITALITY,COL_HOSPITALITY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        deleteTable(db,TBL_WORKSHOP);
//        deleteTable(db,TBL_UPDATES);
//        deleteTable(db,TBL_GUEST_LECTURE);

        /***
         * Migrating from DB version 1 to 2
         */
        if (oldVersion >= 1) {
            deleteTable(db, TBL_EVENTS);
            deleteTable(db, TBL_EVENTS_OPTIONS);
            createTables(db, TBL_EVENTS, COL_EVENTS);
            createTables(db, TBL_EVENTS_OPTIONS, COL_EVENTS_OPTIONS);
            SharedPreferences myprefs = PreferenceManager.getDefaultSharedPreferences(mContext);
            SharedPreferences.Editor editor = myprefs.edit();
            editor.putBoolean(IS_EVENT_FETCHED,false);
            editor.commit();
        }
//        deleteTable(db,TBL_STARTUP_FAIR);
//        deleteTable(db,TBL_PRONITES);
//        deleteTable(db,TBL_INSTITUTE_DAY);
//        deleteTable(db,TBL_CORPORATE_CONCLAVE);
//        deleteTable(db,TBL_EXHIBITIONS);
//        deleteTable(db,TBL_HOSPITALITY);
//        /***
//         * Migrating fom 19 to 20
//         */
//        if(oldVersion==newVersion-1){
//            createTables(db,TBL_TEAMS,COL_TEAMS);
//            createTables(db,TBL_USER_EVENTS,COL_USER_EVENTS);
//            //EVENTs table column added
//            deleteTable(db,TBL_EVENTS);
//            createTables(db,TBL_EVENTS,COL_EVENTS);
//        }
//        deleteTable(db,TBL_TEAMS);
//        deleteTable(db,TBL_USER_EVENTS);
//        onCreate(db);
    }




    public void createTables(SQLiteDatabase db, String tableName, String[] columns) {
        String columnString="";

        for(int i=0;i<columns.length;i++){
            columnString+= columns[i]+" , ";
        }
        columnString=columnString.substring(0,columnString.length()-2);

        String SQL_CREATE_TABlE = " CREATE TABLE "+tableName+
                " ( "
                    +columnString+
                " ); ";
        db.execSQL(SQL_CREATE_TABlE);
        Log.d("TBL CREATED",SQL_CREATE_TABlE);
    }


    public void deleteTable(SQLiteDatabase db, String tableName) {
        db.execSQL("DROP TABLE IF EXISTS "+tableName);
    }

}
