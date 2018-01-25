package com.iitbhu.technex18.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Abhinav on 02/01/2018.
 */
public class DbMethods implements DbConstants{

    Context context;
    DbHelper dbHelper;
    SQLiteDatabase db;
    String TAG = "DATABASE";

    public DbMethods(Context context){
        this.context=context;
        dbHelper=new DbHelper(context);
        db=dbHelper.getWritableDatabase();
    }

    //table workshop

//    String COL_WORKSHOP_ID = "COL_WORKSHOP_ID";
//    String COL_WORKSHOP_GLOBAL_ID = "COL_WORKSHOP_GLOBAL_ID";
//    String COL_WORKSHOP_NAME = "COL_WORKSHOP_NAME";
//    String COL_WORKSHOP_DESCRIPTION = "COL_WORKSHOP_DESCRIPTION";
//    String COL_WORKSHOP_COURSE_CONTENT = "COL_WORKSHOP_COURSE_CONTENT";
//    String COL_WORKSHOP_BENIFITS = "COL_WORKSHOP_BENIFITS";
//    String COL_WORKSHOP_REGISTERED = "COL_WORKSHOP_REGISTERED";
//    String COL_WORKSHOP_IMAGE = "COL_WORKSHOP_IMAGE";

    /***
     * Workshops
     * @param title
     * @param order
     * @param fees
     * @param description
     * @param timestamp
     * @param image
     * @return
     */

    public long insertWorkshop(String title, int order, double fees, String description, String timestamp, String image){
        ContentValues values= new ContentValues();
        values.put(COL_WORKSHOP_TITLE,title);
        values.put(COL_WORKSHOP_ORDER,order);
        values.put(COL_WORKSHOP_FEES,fees);
        values.put(COL_WORKSHOP_DESCRIPTION,description);
        values.put(COL_WORKSHOP_TIMESTAMP, timestamp);
        values.put(COL_WORKSHOP_IMAGE, image);
        long id=db.insert(TBL_WORKSHOP,null,values);
//        Log.d(TAG + " WORKSHOPS",values.toString());
        return id;
    }

    public Cursor queryWorkshop(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy){
        return  db.query(TBL_WORKSHOP,columns,selection,selectionArgs,groupBy,having,orderBy);
    }

    public void deleteWorkshop(long id){
        db.delete(TBL_WORKSHOP,COL_WORKSHOP_ID+" = ? ",new String[]{id+""});
    }


    public void deleteAllWorkshop(){
        db.delete(TBL_WORKSHOP,null,null);
    }

//    String COL_UPDATES_ID = "COL_UPDATES_ID";
//    String COL_UPDATES_GLOBAL_ID = "COL_UPDATES_GLOBAL_ID";
//    String COL_UPDATES_TITLE = "COL_UPDATES_TITLE";
//    String COL_UPDATE_TEXT = "COL_UPDATE_TEXT";
//    String COL_UPDATES_LINK = "COL_UPDATES_LINK";
//    String COL_UPDATES_TIME = "COL_UPDATES_TIME";
//    String COL_UPDATES_AUTHOR_NAME = "COL_UPDATES_AUTHOR_NAME";
//    String COL_UPDATES_AUTHOR_TYPE = "COL_UPDATES_AUTHOR_TYPE";
//    String COL_UPDATES_AUTHOR_ID = "COL_UPDATES_AUTHOR_ID";
//    String COL_UPDATES_TYPE = "COL_UPDATES_TYPE";
//    String COL_UPDATES_IMAGE = "COL_UPDATES_IMAGE";
//    String COL_UPDATES_IMAGE_1 = "COL_UPDATES_IMAGE_1";
//    String COL_UPDATES_IMAGE_2 = "COL_UPDATES_IMAGE_2";
//    String COL_UPDATES_IMAGE_3 = "COL_UPDATES_IMAGE_3";
//    String COL_UPDATES_IMAGE_4 = "COL_UPDATES_IMAGE_4";
//    String COL_UPDATES_VIDEO_URL = "COL_UPDATES_VIDEO_URL";



    public long insertUpdates(String text, long time, String title) {
        ContentValues values= new ContentValues();
        values.put(COL_UPDATES_TEXT, text);
        values.put(COL_UPDATES_TIME, time);
        values.put(COL_UPDATES_TITLE, title);
        long id=db.insert(TBL_UPDATES,null,values);
        Log.d(TAG,values.toString());
        return id;
    }

    public Cursor queryUpdates(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy){
        return  db.query(TBL_UPDATES,columns,selection,selectionArgs,groupBy,having,orderBy);
    }

    public void deleteUpdates(long id){
        db.delete(TBL_UPDATES,COL_UPDATES_ID+" = ? ",new String[]{id+""});
    }

    public void deleteAllUpdates() {
        db.delete(TBL_UPDATES, null,null);
    }


//    String COL_GUEST_LECTURE_ID = "COL_GUEST_LECTURE_ID";
//    String COL_GUEST_LECTURE_GLOBAL_ID = "COL_GUEST_LECTURE_GLOBAL_ID";
//    String COL_GUEST_LECTURE_NAME = "COL_GUEST_LECTURE_NAME";
//    String COL_GUEST_LECTURE_DESCRIPTION = "COL_GUEST_LECTURE_DESCRIPTION";
//    String COL_GUEST_LECTURE_IMAGE = "COL_GUEST_LECTURE_IMAGE";

    /***
     * Guest Lectures
     * @param lecturerBio
     * @param lecturerType
     * @param name
     * @param title
     * @param description
     * @param designation
     * @param image
     * @return
     */

    public long insertGuestLecture(String lecturerBio, String lecturerType, String name, String title, String description, String designation, String image) {
        ContentValues values= new ContentValues();
        values.put(COL_GUEST_LECTURE_LECTURER_BIO, lecturerBio);
        values.put(COL_GUEST_LECTURE_LECTURER_TYPE, lecturerType);
        values.put(COL_GUEST_LECTURE_NAME, name);
        values.put(COL_GUEST_LECTURE_TITLE, title);
        values.put(COL_GUEST_LECTURE_DESCRIPTION, description);
        values.put(COL_GUEST_LECTURE_DESIGNATION, designation);
        values.put(COL_GUEST_LECTURE_IMAGE, image);
        long id=db.insert(TBL_GUEST_LECTURE, null, values);
//        Log.d(TAG+" GUEST",values.toString());
        return id;
    }

    public Cursor queryGuestLectures(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy){
        return  db.query(TBL_GUEST_LECTURE,columns,selection,selectionArgs,groupBy,having,orderBy);
    }

    public void deleteGuestLecture(long id) {
        db.delete(TBL_GUEST_LECTURE, COL_GUEST_LECTURE_ID+" = ? ",new String[]{id+""});
    }

    public void deleteAllGuestLecture() {
        db.delete(TBL_GUEST_LECTURE, null,null);
    }


//    String COL_EVENTS_ID = "COL_EVENTS_ID";
//    String COL_EVENTS_GLOBAL_ID = "COL_EVENTS_GLOBAL_ID";
//    String COL_EVENTS_CLUB_ID = "COL_EVENTS_CLUB_ID";
//    String COL_EVENTS_NAME = "COL_EVENTS_NAME";
//    String COL_EVENTS_INTRODUCTION = "COL_EVENTS_INTRODUCTION";
//    String COL_EVENTS_TASK = "COL_EVENTS_TASK";
//    String COL_EVENTS_RULES = "COL_EVENTS_RULES";
//    String COL_EVENTS_PROBLEM_STATEMENT = "COL_EVENTS_PROBLEM_STATEMENT";
//    String COL_EVENTS_ABSTRACT = "COL_EVENTS_ABSTRACT";
//    String COL_EVENTS_MODEL_SPECIFICATION = "COL_EVENTS_MODEL_SPECIFICATION";
//    String COL_EVENTS_SCORING = "COL_EVENTS_SCORING";
//    String COL_EVENTS_THEME = "COL_EVENTS_THEME";
//    String COL_EVENTS_EVENT_STRUCTURE = "COL_EVENTS_EVENT_STRUCTURE";
//    String COL_EVENTS_HOW_TO_ENTER = "COL_EVENTS_HOW_TO_ENTER";
//    String COL_EVENTS_EVALUATION = "COL_EVENTS_EVALUATION";
//    String COL_EVENTS_ROUNDS = "COL_EVENTS_ROUNDS";
//    String COL_EVENTS_GAMEPLAY = "COL_EVENTS_GAMEPLAY";
//    String COL_EVENTS_TIMELINE = "COL_EVENTS_TIMELINE";
//    String COL_EVENTS_PRIZE_MONEY = "COL_EVENTS_PRIZE_MONEY";
//    String COL_EVENTS_IMAGE = "COL_EVENTS_IMAGE";
//    String COL_EVENTS_PEOPLE_ID = "COL_EVENTS_PEOPLE_ID";

    /***
     * Events
     * @param parentCategory
     * @param eventOrder
     * @param teamSize
     * @param eventName
     * @param prizeMoney
     * @param description
     * @param deadline
     * @return
     */
    public long insertEvents (String parentCategory, String parentDescription, int eventOrder, int teamSize, String eventName, int prizeMoney, String description, String deadline) {
        ContentValues values= new ContentValues();
        values.put(COL_EVENTS_PARENT_CATEGORY, parentCategory);
        values.put(COL_EVENTS_PARENT_DESCRIPTION, parentDescription);
        values.put(COL_EVENTS_EVENT_ORDER, eventOrder);
//        values.put(COL_EVENTS_REMOTE_ID,remoteId);
//        values.put(COL_EVENTS_GLOBAL_ID, globalId);
//        values.put(COL_EVENTS_CLUB_ID, clubId);
        values.put(COL_EVENTS_NAME, eventName);
        values.put(COL_EVENTS_DESCRIPTION, description);
//        values.put(COL_EVENTS_INTRODUCTION, introduction);
//        values.put(COL_EVENTS_TASK, task);
//        values.put(COL_EVENTS_RULES, rules);
//        values.put(COL_EVENTS_PROBLEM_STATEMENT, problemStatement);
        values.put(COL_EVENTS_TEAM_SIZE,teamSize);
        values.put(COL_EVENTS_DEADLINE, deadline);
//        values.put(COL_EVENTS_MODEL_SPECIFICATION, modelSpecification);
//        values.put(COL_EVENTS_SCORING, scoring);
//        values.put(COL_EVENTS_THEME, theme);
//        values.put(COL_EVENTS_EVENT_STRUCTURE, eventStructure);
//        values.put(COL_EVENTS_HOW_TO_ENTER, howToEnter);
//        values.put(COL_EVENTS_EVALUATION, evaluation);
//        values.put(COL_EVENTS_ROUNDS, rounds);
//        values.put(COL_EVENTS_GAMEPLAY, gameplay);
//        values.put(COL_EVENTS_TIMELINE, timeline);
        values.put(COL_EVENTS_PRIZE_MONEY, prizeMoney);
//        values.put(COL_EVENTS_IMAGE, image);
//        values.put(COL_EVENTS_PEOPLE_ID1, peopleId1);
//        values.put(COL_EVENTS_PEOPLE_ID2, peopleId2);
//        values.put(COL_EVENTS_PEOPLE_ID3, peopleId3);
        long id=db.insert(TBL_EVENTS, null, values);
        Log.d(TAG + " EVENTS",values.toString());
        return id;
    }

    public Cursor queryEvents(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy){
        Log.d(TAG +"query events",db.query(TBL_EVENTS,columns,selection,selectionArgs,groupBy,having,orderBy).toString());
        return db.query(TBL_EVENTS,columns,selection,selectionArgs,groupBy,having,orderBy);
    }
    public Cursor queryEventsRaw(String event){
        return db.rawQuery("SELECT * FROM "+ TBL_EVENTS + " WHERE "+ COL_EVENTS_PARENT_CATEGORY +" = '"+event+"' ORDER BY " + COL_EVENTS_EVENT_ORDER,null);
    }
    public void deleteEvents(long id) {
        db.delete(TBL_EVENTS, COL_EVENTS_ID+" = ? ",new String[]{id+""});
    }

    public void deleteAllEvents() {
        db.delete(TBL_EVENTS, null,null);
    }


//    String COL_EVENTS_OPTIONS_ID = "_id";
//    String COL_EVENTS_OPTIONS_NAME = "col_events_options_name";
//    String COL_EVENTS_OPTIONS_DESCRIPTION = "col_events_options_description";

    /***
     *
     * @param name
     * @param description
     * @param eventName
     * @param order
     * @return
     */
    public long insertEventOptions(String name, String description, String eventName, int order) {
        ContentValues values = new ContentValues();
        values.put(COL_EVENTS_OPTIONS_NAME, name);
        values.put(COL_EVENTS_OPTIONS_DESCRIPTION, description);
        values.put(COL_EVENTS_OPTIONS_EVENT, eventName);
        values.put(COL_EVENTS_OPTIONS_ORDER, order);
        long id = db.insert(TBL_EVENTS_OPTIONS, null, values);
        Log.d(TAG + " EVENT OPTIONS", values.toString());
        return id;
    }

    public Cursor queryEventOptions(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy){

        Log.d("query events",db.query(TBL_EVENTS_OPTIONS,columns,selection,selectionArgs,groupBy,having,orderBy).toString());
        return db.query(TBL_EVENTS_OPTIONS,columns,selection,selectionArgs,groupBy,having,orderBy);
    }
    public Cursor queryEventOptionsRaw(String eventName){

        return db.rawQuery("SELECT * FROM "+TBL_EVENTS_OPTIONS+" WHERE "+COL_EVENTS_OPTIONS_EVENT+" = '"+eventName+"' ORDER BY "+COL_EVENTS_OPTIONS_ORDER,null);
    }
    public void deleteEventOptions(long id) {
        db.delete(TBL_EVENTS_OPTIONS, COL_EVENTS_OPTIONS_ID+" = ? ",new String[]{id+""});
    }

    public void deleteSpecificEventOptions(String event) {
        db.delete(TBL_EVENTS_OPTIONS, COL_EVENTS_OPTIONS_EVENT+" = ? ",new String[]{event});
    }

    public void deleteAllEventOptions() {
        db.delete(TBL_EVENTS_OPTIONS, null,null);
    }





    public long insertStartupFair(String intro, String content) {
        ContentValues values = new ContentValues();
        values.put(COL_STARTUP_FAIR_INTRODUCTION, intro);
        values.put(COL_STARTUP_FAIR_CONTENT, content);
        long id = db.insert(TBL_STARTUP_FAIR, null, values);
        Log.d(TAG + " STARTUP FAIR", values.toString());
        return id;
    }

    public Cursor queryStartupFair(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy){
        return  db.query(TBL_STARTUP_FAIR,columns,selection,selectionArgs,groupBy,having,orderBy);
    }

    public void deleteStartupFair(long id) {
        db.delete(TBL_STARTUP_FAIR, COL_STARTUP_FAIR_ID+" = ? ",new String[]{id+""});
    }

    public void deleteAllStartupFair() {
        db.delete(TBL_STARTUP_FAIR, null,null);
    }






    public long insertPronites(String intro, String content) {
        ContentValues values = new ContentValues();
        values.put(COL_PRONITES_INTRODUCTION, intro);
        values.put(COL_PRONITES_CONTENT, content);
        long id = db.insert(TBL_PRONITES, null, values);
        Log.d(TAG + " PRONITES", values.toString());
        return id;
    }

    public Cursor queryPronites(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy){
        return  db.query(TBL_PRONITES,columns,selection,selectionArgs,groupBy,having,orderBy);
    }

    public void deletePronites(long id) {
        db.delete(TBL_PRONITES, COL_PRONITES_ID+" = ? ",new String[]{id+""});
    }

    public void deleteAllPronites() {
        db.delete(TBL_PRONITES, null,null);
    }






    public long insertInstituteDay(String intro, String content) {
        ContentValues values = new ContentValues();
        values.put(COL_INSTITUTE_DAY_INTRODUCTION, intro);
        values.put(COL_INSTITUTE_DAY_CONTENT, content);
        long id = db.insert(TBL_INSTITUTE_DAY, null, values);
        Log.d(TAG + " INSTITUTE DAY", values.toString());
        return id;
    }

    public Cursor queryInstituteDay(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy){
        return  db.query(TBL_INSTITUTE_DAY,columns,selection,selectionArgs,groupBy,having,orderBy);
    }

    public void deleteInstituteDay(long id) {
        db.delete(TBL_INSTITUTE_DAY, COL_INSTITUTE_DAY_ID+" = ? ",new String[]{id+""});
    }

    public void deleteAllInstituteDay() {
        db.delete(TBL_INSTITUTE_DAY, null,null);
    }






    public long insertCorporateConclave(String intro, String content) {
        ContentValues values = new ContentValues();
        values.put(COL_CORPORATE_CONCLAVE_INTRODUCTION, intro);
        values.put(COL_CORPORATE_CONCLAVE_CONTENT, content);
        long id = db.insert(TBL_CORPORATE_CONCLAVE, null, values);
        Log.d(TAG + " CORPORATE", values.toString());
        return id;
    }

    public Cursor queryCorporateConclave(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy){
        return  db.query(TBL_CORPORATE_CONCLAVE,columns,selection,selectionArgs,groupBy,having,orderBy);
    }

    public void deleteCorporateConclave(long id) {
        db.delete(TBL_CORPORATE_CONCLAVE, COL_CORPORATE_CONCLAVE_ID+" = ? ",new String[]{id+""});
    }

    public void deleteAllCorporateConclave() {
        db.delete(TBL_CORPORATE_CONCLAVE, null,null);
    }






    public long insertExhibitions(String intro, String content) {
        ContentValues values = new ContentValues();
        values.put(COL_EXHIBITIONS_INTRODUCTION, intro);
        values.put(COL_EXHIBITIONS_CONTENT, content);
        long id = db.insert(TBL_EXHIBITIONS, null, values);
        Log.d(TAG + " EXHIBITIONS", values.toString());
        return id;
    }

    public Cursor queryExhibitions(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy){
        return  db.query(TBL_EXHIBITIONS,columns,selection,selectionArgs,groupBy,having,orderBy);
    }

    public void deleteExhibitions(long id) {
        db.delete(TBL_EXHIBITIONS, COL_EXHIBITIONS_ID+" = ? ",new String[]{id+""});
    }

    public void deleteAllExhibitions() {
        db.delete(TBL_EXHIBITIONS, null,null);
    }






    public long insertHospitality(String intro, String content) {
        ContentValues values = new ContentValues();
        values.put(COL_HOSPITALITY_INTRODUCTION, intro);
        values.put(COL_HOSPITALITY_CONTENT, content);
        long id = db.insert(TBL_HOSPITALITY, null, values);
        Log.d(TAG + " HOSPITALITY", values.toString());
        return id;
    }

    public Cursor queryHospitality(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy){
        return  db.query(TBL_HOSPITALITY,columns,selection,selectionArgs,groupBy,having,orderBy);
    }

    public void deleteHospitality(long id) {
        db.delete(TBL_HOSPITALITY, COL_HOSPITALITY_ID+" = ? ",new String[]{id+""});
    }

    public void deleteAllHospitality() {
        db.delete(TBL_HOSPITALITY, null,null);
    }

//    String COL_PEOPLE_ID = "COL_PEOPLE_ID";
//    String COL_PEOPLE_GLOBAL_ID = "COL_PEOPLE_GLOBAL_ID";
//    String COL_PEOPLE_NAME = "COL_PEOPLE_NAME";
//    String COL_PEOPLE_CONTACT = "COL_PEOPLE_CONTACT";
//    String COL_PEOPLE_EMAIL = "COL_PEOPLE_EMAIL";


//    /***
//     * People
//     * @param globalId
//     * @param name
//     * @param contact
//     * @param email
//     * @return
//     */
//    public long insertPeople (long globalId, String name, String contact, String email) {
//        ContentValues values= new ContentValues();
//        values.put(COL_PEOPLE_GLOBAL_ID, globalId);
//        values.put(COL_PEOPLE_NAME, name);
//        values.put(COL_PEOPLE_CONTACT, contact);
//        values.put(COL_PEOPLE_EMAIL, email);
//        long id=db.insert(TBL_PEOPLE, null, values);
//        Log.d(TAG,values.toString());
//        return id;
//    }
//
//
//    public Cursor queryPeople(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy){
//        return  db.query(TBL_PEOPLE,columns,selection,selectionArgs,groupBy,having,orderBy);
//    }
//
//    public void deletePeople(long id) {
//        db.delete(TBL_PEOPLE, COL_PEOPLE_ID+" = ? ",new String[]{id+""});
//    }
//
//    public void deleteAllPeople() {
//        db.delete(TBL_PEOPLE, null,null);
//    }
//
//
////    String COL_CLUB_ID = "COL_CLUB_ID";
////    String COL_CLUB_GLOBAL_ID = "COL_CLUB_GLOBAL_ID";
////    String COL_CLUB_NAME = "COL_CLUB_NAME";
////    String COL_CLUB_DESCRIPTION = "COL_CLUB_DESCRIPTION";
//
//    /***
//     * Clubs
//     * @param globalId
//     * @param name
//     * @param description
//     * @param image
//     * @return
//     */
//    public long insertClub(long globalId, String name, String description, String image) {
//        ContentValues values= new ContentValues();
//        values.put(COL_CLUB_GLOBAL_ID, globalId);
//        values.put(COL_CLUB_NAME, name);
//        values.put(COL_CLUB_IMAGE,image);
//        values.put(COL_CLUB_DESCRIPTION, description);
//        long id=db.insert(TBL_CLUB, null, values);
//        Log.d(TAG,values.toString());
//        return id;
//    }
//
//    public Cursor queryClub(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy){
//        return  db.query(TBL_CLUB,columns,selection,selectionArgs,groupBy,having,orderBy);
//    }
//
//    public void deleteClub(long id) {
//        db.delete(TBL_CLUB, COL_CLUB_ID+" = ? ",new String[]{id+""});
//    }
//
//    public void deleteAllClubs(){
//        db.delete(TBL_CLUB, null,null);
//    }
//
//
////    String COL_PLACES_ID = "COL_PLACES_ID";
////    String COL_PLACES_GLOBAL_ID = "COL_PLACES_GLOBAL_ID";
////    String COL_PLACES_DESCRIPTION = "COL_PLACES_DESCRIPTION";
////    String COL_PLACES_LATITUDE = "COL_PLACES_LATITUDE";
////    String COL_PLACES_LONGITUDE = "COL_PLACES_LONGITUDE";
////    String COL_PLACES_ZOOM = " col_places_zoom ";
////    String COL_PLACES_ADDRESS_PRIMARY = "COL_PLACES_ADDRESS_PRIMARY";
////    String COL_PLACES_ADDRESS_SECONDARY = "COL_PLACES_ADDRESS_SECONDARY";
////    String COL_PLACES_IMAGE = "COL_PLACES_IMAGE";
//
//    /***
//     * Places
//     * @param globalId
//     * @param description
//     * @param latitude
//     * @param longitude
//     * @param zoom
//     * @param addressPrimary
//     * @param addressSecondary
//     * @param image
//     * @return
//     */
//    public long insertPlaces(long globalId, String description, double latitude, double longitude, double zoom, String addressPrimary, String addressSecondary, String image) {
//        ContentValues values= new ContentValues();
//        values.put(COL_PLACES_GLOBAL_ID, globalId);
//        values.put(COL_PLACES_DESCRIPTION, description);
//        values.put(COL_PLACES_LATITUDE, latitude);
//        values.put(COL_PLACES_LONGITUDE, longitude);
//        values.put(COL_PLACES_ZOOM,zoom);
//        values.put(COL_PLACES_ADDRESS_PRIMARY, addressPrimary);
//        values.put(COL_PLACES_ADDRESS_SECONDARY, addressSecondary);
//        values.put(COL_PLACES_IMAGE, image);
//        long id=db.insert(TBL_PLACES, null, values);
//        Log.d(TAG,values.toString());
//        return id;
//    }
//
//    public Cursor queryPlaces(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy){
//        return  db.query(TBL_PLACES,columns,selection,selectionArgs,groupBy,having,orderBy);
//    }
//
//    public void deletePlaces(long id) {
//        db.delete(TBL_PLACES, COL_PLACES_ID+" = ? ",new String[]{id+""});
//    }
//
//    public void deleteAllPlaces() {
//        db.delete(TBL_PLACES, null,null);
//    }
//
//
////    String TBL_VIDEO = "tbl_video";
////    String COL_VIDEO_ID = "_id";
////    String COL_VIDEO_GLOBAL_ID = "col_video_id";
////    String COL_VIDEO_TITLE = "col_video_title";
////    String COL_VIDEO_URL = "col_video_url";
////    String COL_VIDEO_SUBTITLE = "col_video_subtitle";
////    String COL_VIDEO_DESCIRIPTION = "col_video_description";
//
//    /***
//     * Videos
//     * @param globalId
//     * @param title
//     * @param url
//     * @param subtitle
//     * @param description
//     * @return
//     */
//    public long insertVideos(long globalId, String title, String url, String subtitle, String description){
//        ContentValues values = new ContentValues();
//        values.put(COL_VIDEO_GLOBAL_ID,globalId);
//        values.put(COL_VIDEO_TITLE,title);
//        values.put(COL_VIDEO_URL,url);
//        values.put(COL_VIDEO_SUBTITLE,subtitle);
//        values.put(COL_VIDEO_DESCIRIPTION,description);
//        return db.insert(TBL_VIDEO,null,values);
//    }
//
//    public Cursor queryVideos(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy){
//        return  db.query(TBL_VIDEO, columns, selection, selectionArgs, groupBy, having, orderBy);
//    }
//
//    public void deleteVideo(long id) {
//        db.delete(TBL_VIDEO, COL_VIDEO_ID+" = ? ",new String[]{id+""});
//    }
//
//    public void deleteAllVideos() {
//        db.delete(TBL_VIDEO, null, null);
//    }
//
//
//    /***
//     * Images
//     * @param globalId
//     * @param title
//     * @param url
//     * @param subtitle
//     * @param description
//     * @return
//     */
//    public long insertImages(long globalId, String title, String url, String subtitle, String description){
//        ContentValues values = new ContentValues();
//        values.put(COL_IMAGE_GLOBAL_ID,globalId);
//        values.put(COL_IMAGE_TITLE,title);
//        values.put(COL_IMAGE_URL,url);
//        values.put(COL_IMAGE_SUBTITLE,subtitle);
//        values.put(COL_IMAGE_DESCIRIPTION,description);
//        return db.insert(TBL_IMAGES,null,values);
//    }
//
//    public Cursor queryImages(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy){
//        return  db.query(TBL_IMAGES, columns, selection, selectionArgs, groupBy, having, orderBy);
//    }
//
//    public void deleteImage(long id) {
//        db.delete(TBL_IMAGES, COL_IMAGE_ID+" = ? ",new String[]{id+""});
//    }
//
//    public void deleteAllImages() {
//        db.delete(TBL_IMAGES, null, null);
//    }
//
//
//    /***
//     * User Teams
//     * @param teamName
//     * @param eventId
//     * @param teamSize
//     * @param teamLeaderEmail
//     * @param member2Email
//     * @param member3Email
//     * @param member4Email
//     * @param member5Email
//     * @param member6Email
//     * @return
//     */
//    public long insertTeams(String teamName, long eventId, String eventName, int teamSize, String teamLeaderEmail,
//                            String member2Email, String member3Email, String member4Email, String member5Email, String member6Email) {
//        ContentValues values = new ContentValues();
//        values.put(COL_TEAMS_TEAM_NAME, teamName);
//        values.put(COL_TEAMS_EVENT_NAME,eventName);
//        values.put(COL_TEAMS_EVENT_ID, eventId);
//        values.put(COL_TEAMS_TEAM_SIZE, teamSize);
//        values.put(COL_TEAMS_EMAIL_LEADER, teamLeaderEmail);
//        values.put(COL_TEAMS_EMAIL_MEMBER2, member2Email);
//        values.put(COL_TEAMS_EMAIL_MEMBER3, member3Email);
//        values.put(COL_TEAMS_EMAIL_MEMBER4, member4Email);
//        values.put(COL_TEAMS_EMAIL_MEMBER5, member5Email);
//        values.put(COL_TEAMS_EMAIL_MEMBER6, member6Email);
//        return db.insert(TBL_TEAMS, null, values);
//    }
//
//    public Cursor queryTeams(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy){
//        return  db.query(TBL_TEAMS, columns, selection, selectionArgs, groupBy, having, orderBy);
//    }
//
////    public void deleteTeams(long EventId) {
////        db.delete(TBL_TEAMS, COL_TEAMS_EVENT_ID+" = ? ",new String[]{id+""});
////    }
//
//    public void deleteAllTeams() {
//        db.delete(TBL_TEAMS, null, null);
//    }
//
//
//    /***
//     * User Events
//     * @param email
//     * @param eventId
//     * @param eventName
//     * @param teamName
//     * @return
//     */
//
//    public long insertUserEvents(String email, long eventId, String eventName, String teamName) {
//        ContentValues values = new ContentValues();
//        values.put(COL_USER_EVENTS_EMAIL, email);
//        values.put(COL_USER_EVENTS_EVENT_ID, eventId);
//        values.put(COL_USER_EVENTS_EVENT_NAME, eventName);
//        values.put(COL_USER_EVENTS_TEAM_NAME, teamName);
//        return db.insert(TBL_USER_EVENTS, null, values);
//    }
//
//    public Cursor queryUserEvents(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy){
//        return  db.query(TBL_USER_EVENTS, columns, selection, selectionArgs, groupBy, having, orderBy);
//    }
//
//    public void deleteUserEvents(long eventId) {
//        db.delete(TBL_USER_EVENTS, COL_USER_EVENTS_EVENT_ID + " = ? ", new String[]{eventId + ""});
//    }
//
//    public void deleteAllUserEvents() {
//        db.delete(TBL_USER_EVENTS, null, null);
//    }





}
