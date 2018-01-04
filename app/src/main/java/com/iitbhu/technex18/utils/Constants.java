package com.iitbhu.technex18.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shriyansh on 13/1/16.
 */
public interface Constants {

    public static final String NAME= "name";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String CONTACT = "contact";
    public static final String COLLEGE = "college";
    public static final String YEAR = "year";
    public static final String PIN = "pin";
    public static final String TECHNEX_ID = "technex_id";
    public static final String REGISTERED = "registered";
    public static final String VERIFIED = "verified";
    public static final String HOSTEL = "hostel";

    public static String PROJECT_ID = "441155145442";
    public static String API_KEY = "AIzaSyApZl6IpMnivPD9UG7MSGEpxx5fTf_gcqk";
    public static String RELEASE_API_KEY = "AIzaSyAFnBw2eFdWK-cGIU8-fGuXEZSBsr-G3C4";
    public static String BROWSER_KEY = "AIzaSyAxeIG-AAzYBrOMzrsJrd6BWNxi-qs7cFI";
    public static String SERVER_KEY = "AIzaSyAUhD-j8zBorWwdYynxA8ALtKknp5lRQks";


    public static Map<String, String> hostelMap = new HashMap<String, String>() {
        {
            put("A","C.V.Raman Hostel");
            put("B","Morvi Hostel");
            put("C","Dhanrajgiri Hostel");
            put("D","Ramanujan Hostel");
            put("E","Aryabhatta Hostel");
            put("F","Other option 1");
            put("G","Other option 2");
            put("H","S. RadhaKrishnan Hostel BHU");
            put("I","A.S.N. Bose Hostel");
            put("J","Vishveshwarayya Hostel");
            put("K","Rajputana Hostel");
            put("L","Limbdi Hostel");
            put("M","S.C. De Hostel");
            put("N","Vivekananda Hostel");
            put("O","Vishwakarma Hostel");
            put("P","G.S.M.C. Old");
            put("Q","G.S.M.C. Extension");
            put("R","Other option 3");
            put("S","Saluja Hostel");
            put("T","GRTA");
            put("U","Other option 4");
            put("V","Other option 5");
            put("W","Gurtu Hostel BHU");
            put("X","Other Option 6");
            put("Y","Other Option 7");

            put("Z", "No Accommodation");
        }
    };


    String LAST_UPDATE_TIME = "last_update_time";

    String IS_EVENT_FETCHED = "is_event_fetched";
    String IS_TALKS_FETCHED = "is_talks_fetched";
    String IS_WORKSHOPS_FETCHED = "is_workshops_fetched";
    String IS_STARTUP_FAIR_FETCHED = "is_startup_fair_fetched";
    String IS_KALEIDOSCOPE_FETCHED = "is_kaleidoscope_fetched";
    String IS_INSTITUTE_DAY_FETCHED = "is_institute_day_fetched";
    String IS_CORPORATE_CONCLAVE_FETCHED = "is_corporate_conclave_fetched";
    String IS_EXHIBITIONS_FETCHED = "is_exhibitions_fetched";
    String IS_HOSPITALITY_FETCHED = "is_hospitality_fetched";

    String FCM_TOKEN = "fcm_token";
    String IS_FCM_TOKEN_SENT = "is_fcm_token_sent";

}
