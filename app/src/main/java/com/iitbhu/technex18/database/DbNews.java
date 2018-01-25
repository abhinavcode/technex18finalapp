package com.iitbhu.technex18.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Abhinav on 02/01/2018.
 */
public class DbNews {
    public static String KEY_ROWID="_id";
    public static String KEY_TITLE="title";
    public static String KEY_BODY="body";
    public static String KEY_PIC="pic";
    public static String KEY_DATE="date";
    public static String KEY_CLUB="club";

    private static String DATABASE_NAME="db_news";
    private static String DATABASE_TABLE="db_table_news";
    private static int DATABASE_VERSION=1;

    private DBHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;

    //******************creating
    //
    // NEW Entries***************************************//

    public long createEntry(String title, String body, byte[] pic, String date, String club) {
        ContentValues cv=new ContentValues();
        cv.put(KEY_TITLE, title);
        cv.put(KEY_BODY,body);
        cv.put(KEY_PIC,pic);
        cv.put(KEY_DATE,date);
        cv.put(KEY_CLUB,club);
        return ourDatabase.insert(DATABASE_TABLE,null,cv);
    }

    //******************creating NEW Entries***************************************//

    //********************Showing Output ******************************************//
    //              *************Called by DBViewer***********                     //

    public String[] getTitle() {
        String[] columns=new String[]{KEY_TITLE};
        Cursor curse=ourDatabase.query(DATABASE_TABLE,
                columns, null, null, null, null, null);
        String result[] = new String[30];

        int iTitle= curse.getColumnIndex(KEY_TITLE);
        int i=29;

        for(curse.moveToLast();!curse.isBeforeFirst();curse.moveToPrevious()){
            result[i] = ""+curse.getString(iTitle);
            if(i==0)
                break;
            i--;
        }
        return result;
    }
    public String[] getBody() {
        String[] columns = new String[]{KEY_BODY};
        Cursor curse = ourDatabase.query(DATABASE_TABLE,
                columns, null, null, null, null, null);
        String result[] = new String[30];

        int iBody = curse.getColumnIndex(KEY_BODY);
        int i=29;

        for (curse.moveToLast(); !curse.isBeforeFirst(); curse.moveToPrevious()) {
            result[i] = "" + curse.getString(iBody);
            if (i == 0)
                break;
            i--;
        }
        return result;
    }
    public byte[][] getPic() {
        String[] columns = new String[]{KEY_PIC};
        Cursor curse = ourDatabase.query(DATABASE_TABLE,
                columns, null, null, null, null, null);
        byte[][] result = new byte[30][];

        int iPic = curse.getColumnIndex(KEY_PIC);
        int i=29;

        for (curse.moveToLast(); !curse.isBeforeFirst(); curse.moveToPrevious()) {
            result[i] = curse.getBlob(iPic);
            if (i == 0)
                break;
            i--;
        }
        return result;
    }
    public String[] getDate() {
        String[] columns = new String[]{KEY_DATE};
        Cursor curse = ourDatabase.query(DATABASE_TABLE,
                columns, null, null, null, null, null);
        String result[] = new String[30];

        int iDate = curse.getColumnIndex(KEY_DATE);
        int i=29;

        for (curse.moveToLast(); !curse.isBeforeFirst(); curse.moveToPrevious()) {
            result[i] = "" + curse.getString(iDate);
            if (i == 0)
                break;
            i--;
        }
        return result;
    }
    public String[] getClub() {
        String[] columns = new String[]{KEY_CLUB};
        Cursor curse = ourDatabase.query(DATABASE_TABLE,
                columns, null, null, null, null, null);
        String result[] = new String[30];

        int iClub = curse.getColumnIndex(KEY_CLUB);
        int i=29;

        for (curse.moveToLast(); !curse.isBeforeFirst(); curse.moveToPrevious()) {
            result[i] = "" + curse.getString(iClub);
            if (i == 0)
                break;
            i--;
        }
        return result;
    }




    //********************Showing Output ******************************************//


    //********************** Creating Table ***************************************//

    private class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + DATABASE_TABLE + " ( " + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            KEY_TITLE + " TEXT NOT NULL, " +
                            KEY_BODY + " TEXT NOT NULL, " +
                            KEY_PIC + " BLOB NOT NULL, " +
                            KEY_DATE + " TEXT NOT NULL, " +
                            KEY_CLUB + " TEXT NOT NULL);"
            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS"+DATABASE_TABLE);
            onCreate(db);
        }

    }
    //********************** Creating Table ***************************************//

    //********************** DBHelper Stuff ***************************************//
    public DbNews(Context c) {
        ourContext = c;
    }
    public DbNews open() throws SQLException {
        ourHelper=new DBHelper(ourContext);
        ourDatabase=ourHelper.getWritableDatabase();
        return DbNews.this;
    }
    public DbNews close(){
        ourHelper.close();

        return DbNews.this;
    }

}




