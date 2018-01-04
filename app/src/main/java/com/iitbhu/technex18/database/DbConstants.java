package com.iitbhu.technex18.database;

/**
 * Created by mayank on 18/09/16.
 */
public interface DbConstants {
    String DB_NAME= "technex17.db";
    int DB_VERSION = 2 ;

    String INTEGER_PRIMARY_KEY_AUTOINCREMENT = " INTEGER PRIMARY KEY AUTOINCREMENT ";
    String INTEGER = " INTEGER ";
    String TEXT = " TEXT ";
    String REAL =  " REAL ";
    String DEFAULT = " DEFAULT ";


    String TBL_UPDATES="tbl_updates";
    String COL_UPDATES_ID = "_id";
    String COL_UPDATES_TEXT = "col_updates_text";
    String COL_UPDATES_TIME = "col_updates_time";
    String COL_UPDATES_TITLE = "col_updates_title";

    String COL_UPDATES[] = new String[] {
            COL_UPDATES_ID + INTEGER_PRIMARY_KEY_AUTOINCREMENT,
            COL_UPDATES_TEXT + TEXT,
            COL_UPDATES_TIME + INTEGER,
            COL_UPDATES_TITLE + TEXT
    };




    String TBL_WORKSHOP = "tbl_workshop";
    String COL_WORKSHOP_ID = "_id";
    String COL_WORKSHOP_TITLE = "col_workshop_title";
    String COL_WORKSHOP_ORDER = "col_workshop_order";
    String COL_WORKSHOP_FEES = "col_workshop_fees";
    String COL_WORKSHOP_DESCRIPTION = "col_workshop_description";
    String COL_WORKSHOP_TIMESTAMP = "col_workshop_timestamp";
    String COL_WORKSHOP_IMAGE = "col_workshop_image_url";

    String COL_WORKSHOP[] = new String[]{
            COL_WORKSHOP_ID+INTEGER_PRIMARY_KEY_AUTOINCREMENT,
            COL_WORKSHOP_TITLE+TEXT,
            COL_WORKSHOP_ORDER+INTEGER,
            COL_WORKSHOP_FEES+REAL,
            COL_WORKSHOP_DESCRIPTION +TEXT,
            COL_WORKSHOP_TIMESTAMP+TEXT,
            COL_WORKSHOP_IMAGE+TEXT
    };


    String TBL_GUEST_LECTURE = "tbl_guest_lecture";
    String COL_GUEST_LECTURE_ID = "_id";
    String COL_GUEST_LECTURE_LECTURER_BIO = "col_guest_lecture_lecturer_bio";
    String COL_GUEST_LECTURE_LECTURER_TYPE = "col_guest_lecture_lecturer_type";
    String COL_GUEST_LECTURE_NAME = "col_guest_lecture_name";
    String COL_GUEST_LECTURE_TITLE = "col_guest_lecture_title";
    String COL_GUEST_LECTURE_DESCRIPTION = "col_guest_lecture_description";
    String COL_GUEST_LECTURE_DESIGNATION = "col_guest_lecture_designation";
    String COL_GUEST_LECTURE_IMAGE = "col_guest_lecture_image_url";

    String COL_GUEST_LECTURE[] = new String[]{
            COL_GUEST_LECTURE_ID + INTEGER_PRIMARY_KEY_AUTOINCREMENT,
            COL_GUEST_LECTURE_LECTURER_BIO+ TEXT,
            COL_GUEST_LECTURE_LECTURER_TYPE+ TEXT,
            COL_GUEST_LECTURE_NAME + TEXT,
            COL_GUEST_LECTURE_TITLE + TEXT,
            COL_GUEST_LECTURE_DESCRIPTION + TEXT,
            COL_GUEST_LECTURE_DESIGNATION + TEXT,
            COL_GUEST_LECTURE_IMAGE + TEXT
    };


    String TBL_EVENTS = "tbl_events";
    String COL_EVENTS_ID = "_id";
    String COL_EVENTS_PARENT_CATEGORY = "col_events_parent_category";
    String COL_EVENTS_PARENT_DESCRIPTION = "col_events_parent_description";
    String COL_EVENTS_NAME = "col_events_name";
    String COL_EVENTS_EVENT_ORDER = "col_events_event_order";
    String COL_EVENTS_TEAM_SIZE = "col_events_team_size";
    String COL_EVENTS_DESCRIPTION = "col_events_description";
    String COL_EVENTS_DEADLINE = "col_events_theme";
    String COL_EVENTS_PRIZE_MONEY = "col_events_prize_money";


    String COL_EVENTS[] = new String[]{
            COL_EVENTS_ID + INTEGER_PRIMARY_KEY_AUTOINCREMENT,
            COL_EVENTS_PARENT_CATEGORY + TEXT,
            COL_EVENTS_PARENT_DESCRIPTION + TEXT,
            COL_EVENTS_NAME + TEXT,
            COL_EVENTS_EVENT_ORDER + TEXT,
            COL_EVENTS_TEAM_SIZE + INTEGER,
            COL_EVENTS_DESCRIPTION + TEXT,
            COL_EVENTS_DEADLINE + TEXT,
            COL_EVENTS_PRIZE_MONEY + INTEGER
    };


    String TBL_EVENTS_OPTIONS = "tbl_events_options";
    String COL_EVENTS_OPTIONS_ID = "_id";
    String COL_EVENTS_OPTIONS_NAME = "col_events_options_name";
    String COL_EVENTS_OPTIONS_DESCRIPTION = "col_events_options_description";
    String COL_EVENTS_OPTIONS_EVENT = "col_events_options_event";
    String COL_EVENTS_OPTIONS_ORDER = "col_events_options_order";

    String COL_EVENTS_OPTIONS[] = new String[] {
            COL_EVENTS_OPTIONS_ID + INTEGER_PRIMARY_KEY_AUTOINCREMENT,
            COL_EVENTS_OPTIONS_NAME + TEXT,
            COL_EVENTS_OPTIONS_DESCRIPTION + TEXT,
            COL_EVENTS_OPTIONS_EVENT + TEXT,
            COL_EVENTS_OPTIONS_ORDER + INTEGER
    };


    String TBL_STARTUP_FAIR = "tbl_startup_fair";
    String COL_STARTUP_FAIR_ID = "_id";
    String COL_STARTUP_FAIR_INTRODUCTION = "col_startup_fair_introduction";
    String COL_STARTUP_FAIR_CONTENT = "col_startup_fair_content";

    String COL_STARTUP_FAIR[] = new String[] {
            COL_STARTUP_FAIR_ID + INTEGER_PRIMARY_KEY_AUTOINCREMENT,
            COL_STARTUP_FAIR_INTRODUCTION + TEXT,
            COL_STARTUP_FAIR_CONTENT + TEXT
    };

    String TBL_PRONITES = "tbl_pronites";
    String COL_PRONITES_ID = "_id";
    String COL_PRONITES_INTRODUCTION = "col_pronites_introduction";
    String COL_PRONITES_CONTENT = "col_pronites_content";

    String COL_PRONITES[] = new String[] {
            COL_PRONITES_ID + INTEGER_PRIMARY_KEY_AUTOINCREMENT,
            COL_PRONITES_INTRODUCTION + TEXT,
            COL_PRONITES_CONTENT + TEXT
    };

    String TBL_INSTITUTE_DAY = "tbl_institute_day";
    String COL_INSTITUTE_DAY_ID = "_id";
    String COL_INSTITUTE_DAY_INTRODUCTION = "col_institute_day_introduction";
    String COL_INSTITUTE_DAY_CONTENT = "col_institute_day_content";

    String COL_INSTITUTE_DAY[] = new String[] {
            COL_INSTITUTE_DAY_ID + INTEGER_PRIMARY_KEY_AUTOINCREMENT,
            COL_INSTITUTE_DAY_INTRODUCTION + TEXT,
            COL_INSTITUTE_DAY_CONTENT + TEXT
    };

    String TBL_CORPORATE_CONCLAVE = "tbl_corporate_conclave";
    String COL_CORPORATE_CONCLAVE_ID = "_id";
    String COL_CORPORATE_CONCLAVE_INTRODUCTION = "col_corporate_conclave_introduction";
    String COL_CORPORATE_CONCLAVE_CONTENT = "col_corporate_conclave_content";

    String COL_CORPORATE_CONCLAVE[] = new String[] {
            COL_CORPORATE_CONCLAVE_ID + INTEGER_PRIMARY_KEY_AUTOINCREMENT,
            COL_CORPORATE_CONCLAVE_INTRODUCTION + TEXT,
            COL_CORPORATE_CONCLAVE_CONTENT + TEXT
    };

    String TBL_EXHIBITIONS = "tbl_exhibitions";
    String COL_EXHIBITIONS_ID = "_id";
    String COL_EXHIBITIONS_INTRODUCTION = "col_exhibitions_introduction";
    String COL_EXHIBITIONS_CONTENT = "col_exhibitions_content";

    String COL_EXHIBITIONS[] = new String[] {
            COL_EXHIBITIONS_ID + INTEGER_PRIMARY_KEY_AUTOINCREMENT,
            COL_EXHIBITIONS_INTRODUCTION + TEXT,
            COL_EXHIBITIONS_CONTENT + TEXT
    };

    String TBL_HOSPITALITY = "tbl_hospitality";
    String COL_HOSPITALITY_ID = "_id";
    String COL_HOSPITALITY_INTRODUCTION = "col_hospitality_introduction";
    String COL_HOSPITALITY_CONTENT = "col_hospitality_content";

    String COL_HOSPITALITY[] = new String[] {
            COL_HOSPITALITY_ID + INTEGER_PRIMARY_KEY_AUTOINCREMENT,
            COL_HOSPITALITY_INTRODUCTION + TEXT,
            COL_HOSPITALITY_CONTENT + TEXT
    };


//    String TBL_PEOPLE = "tbl_people";
//    String COL_PEOPLE_ID = "_id";
//    String COL_PEOPLE_GLOBAL_ID = "col_people_global_id";
//    String COL_PEOPLE_NAME = "col_people_name";
//    String COL_PEOPLE_CONTACT = "col_people_contact";
//    String COL_PEOPLE_EMAIL = "col_people_email";
//
//    String COL_PEOPLE[] = new String[] {
//            COL_PEOPLE_ID + INTEGER_PRIMARY_KEY_AUTOINCREMENT,
//            COL_PEOPLE_GLOBAL_ID + INTEGER,
//            COL_PEOPLE_NAME + TEXT,
//            COL_PEOPLE_CONTACT + TEXT,
//            COL_PEOPLE_EMAIL + TEXT
//    };
//
//
//    String TBL_CLUB = "tbl_club";
//    String COL_CLUB_ID = "_id";
//    String COL_CLUB_GLOBAL_ID = "col_club_global_id";
//    String COL_CLUB_NAME = "col_club_name";
//    String COL_CLUB_IMAGE = "col_club_image";
//    String COL_CLUB_DESCRIPTION = "col_club_description";
//
//    String COL_CLUB[] = new String[] {
//            COL_CLUB_ID + INTEGER_PRIMARY_KEY_AUTOINCREMENT,
//            COL_CLUB_GLOBAL_ID + INTEGER,
//            COL_CLUB_NAME + TEXT,
//            COL_CLUB_IMAGE + TEXT,
//            COL_CLUB_DESCRIPTION + TEXT
//    };
//
//
//    String TBL_PLACES = "tbl_places";
//    String COL_PLACES_ID = "_id";
//    String COL_PLACES_GLOBAL_ID = "col_places_global_id";
//    String COL_PLACES_DESCRIPTION = "col_places_description";
//    String COL_PLACES_LATITUDE = "col_places_latitude";
//    String COL_PLACES_LONGITUDE = "col_places_longitude";
//    String COL_PLACES_ZOOM = "col_places_zoom";
//    String COL_PLACES_ADDRESS_PRIMARY = "col_places_address_primary";
//    String COL_PLACES_ADDRESS_SECONDARY = "col_places_address_secondary";
//    String COL_PLACES_IMAGE = "col_places_image_url";
//
//    String COL_PLACES[] = new String[]{
//            COL_PLACES_ID + INTEGER_PRIMARY_KEY_AUTOINCREMENT,
//            COL_PLACES_GLOBAL_ID + INTEGER,
//            COL_PLACES_DESCRIPTION + TEXT,
//            COL_PLACES_LATITUDE  + REAL,
//            COL_PLACES_LONGITUDE + REAL,
//            COL_PLACES_ZOOM + REAL ,
//            COL_PLACES_ADDRESS_PRIMARY + TEXT,
//            COL_PLACES_ADDRESS_SECONDARY + TEXT,
//            COL_PLACES_IMAGE + TEXT
//    };
//
//
//    String TBL_VIDEO = "tbl_video";
//    String COL_VIDEO_ID = "_id";
//    String COL_VIDEO_GLOBAL_ID = "col_video_id";
//    String COL_VIDEO_TITLE = "col_video_title";
//    String COL_VIDEO_URL = "col_video_url";
//    String COL_VIDEO_SUBTITLE = "col_video_subtitle";
//    String COL_VIDEO_DESCIRIPTION = "col_video_description";
//
//    String COL_VIDEOS[] = new String[]{
//            COL_VIDEO_ID + INTEGER_PRIMARY_KEY_AUTOINCREMENT,
//            COL_VIDEO_GLOBAL_ID + INTEGER,
//            COL_VIDEO_TITLE + TEXT,
//            COL_VIDEO_URL + TEXT,
//            COL_VIDEO_SUBTITLE + TEXT,
//            COL_VIDEO_DESCIRIPTION + TEXT
//    };
//
//
//    String TBL_IMAGES = "tbl_images";
//    String COL_IMAGE_ID = "_id";
//    String COL_IMAGE_GLOBAL_ID = "col_image_id";
//    String COL_IMAGE_TITLE = "col_image_title";
//    String COL_IMAGE_URL = "col_image_url";
//    String COL_IMAGE_SUBTITLE = "col_image_subtitle";
//    String COL_IMAGE_DESCIRIPTION = "col_image_description";
//
//    String COL_IMAGES[] = new String[]{
//            COL_IMAGE_ID + INTEGER_PRIMARY_KEY_AUTOINCREMENT,
//            COL_IMAGE_GLOBAL_ID + INTEGER,
//            COL_IMAGE_TITLE + TEXT,
//            COL_IMAGE_URL + TEXT,
//            COL_IMAGE_SUBTITLE + TEXT,
//            COL_IMAGE_DESCIRIPTION + TEXT
//    };
//
//
//    /*
//    *Version 10
//     */
//    String TBL_TEAMS = "tbl_teams";
//    String COL_TEAMS_TEAM_NAME = "col_teams_team_name";
//    String COL_TEAMS_EVENT_ID = "col_teams_event_id";
//    String COL_TEAMS_EVENT_NAME = "col_teams_event_name";
//    String COL_TEAMS_TEAM_SIZE = "col_teams_team_size";
//    String COL_TEAMS_EMAIL_LEADER = "col_teams_email_leader";
//    String COL_TEAMS_EMAIL_MEMBER2 = "col_teams_email_member2";
//    String COL_TEAMS_EMAIL_MEMBER3 = "col_teams_email_member3";
//    String COL_TEAMS_EMAIL_MEMBER4 = "col_teams_email_member4";
//    String COL_TEAMS_EMAIL_MEMBER5 = "col_teams_email_member5";
//    String COL_TEAMS_EMAIL_MEMBER6 = "col_teams_email_member6";
//
//    String COL_TEAMS[] = new String[]{
//            COL_TEAMS_TEAM_NAME + TEXT,
//            COL_TEAMS_EVENT_ID + INTEGER,
//            COL_TEAMS_EVENT_NAME + TEXT,
//            COL_TEAMS_TEAM_SIZE + INTEGER,
//            COL_TEAMS_EMAIL_LEADER + TEXT,
//            COL_TEAMS_EMAIL_MEMBER2 + TEXT,
//            COL_TEAMS_EMAIL_MEMBER3 + TEXT,
//            COL_TEAMS_EMAIL_MEMBER4 + TEXT,
//            COL_TEAMS_EMAIL_MEMBER5 + TEXT,
//            COL_TEAMS_EMAIL_MEMBER6 + TEXT
//    };
//
//
//    String TBL_USER_EVENTS = "tbl_user_events";
//    String COL_USER_EVENTS_EMAIL = "col_user_events_email";
//    String COL_USER_EVENTS_EVENT_ID = "col_user_events_event_id";
//    String COL_USER_EVENTS_EVENT_NAME = "col_user_events_event_name";
//    String COL_USER_EVENTS_TEAM_NAME = "col_user_events_team_name";
//
//    String COL_USER_EVENTS[] = new String[]{
//            COL_USER_EVENTS_EMAIL + TEXT,
//            COL_USER_EVENTS_EVENT_ID + INTEGER,
//            COL_USER_EVENTS_EVENT_NAME + TEXT,
//            COL_USER_EVENTS_TEAM_NAME + TEXT
//    };

}
