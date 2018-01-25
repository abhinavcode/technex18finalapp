package com.iitbhu.technex18.network;

/**
 * Created by Abhinav on 02/01/2018.
 */

public interface URLs {

    String HOST = "http://52.24.177.3/";
//    String WORKSHOP_URL = HOST+"workshop_resp.php";
//    String GUEST_LECTURES = HOST+"guest_lecture_resp.php";
    String PLACES = HOST+"places_resp.php";
    String CLUBS = HOST+"club_resp.php";
//    String EVENTS = HOST+"events_resp.php";
    String UPDATES = HOST+"updates.php";
    String PEOPLE = HOST+"people_resp.php";
    String VIDEOS = HOST+"videos_resp.php";
    String IMAGES = HOST+"image_resp.php";
    String IMAGE_CONTENT = HOST+"image_content/";

    //Remote URLs
    String LOGIN_URL = "http://technex2017.herokuapp.com/api/login/";
    String REGISTER_URL = "http://technex2017.herokuapp.com/api/register/";
    String GUEST_LECTURES_URL = "https://technex2017.herokuapp.com/api/guestLecture/";
    String EVENTS_URL = "https://technex2017.herokuapp.com/api/eventApi/";
    String EVENT_REG = "https://technex2017.herokuapp.com/api/eventRegistration/";
    String WORKSHOPS_URL = "https://technex2017.herokuapp.com/api/workshops/";
    String STARTUP_FAIR_URL = "https://technex2017.herokuapp.com/api/startUpFairApi/";
    String PRONITES_URL = "https://technex2017.herokuapp.com/api/pronitesApi/";
    String INSTITUTE_DAY_URL = "https://technex2017.herokuapp.com/api/instituteDayApi/";
    String CORPORATE_CONCLAVE_URL = "https://technex2017.herokuapp.com/api/corporateConclaveApi/";
    String EXHIBITIONS_URL = "https://technex2017.herokuapp.com/api/exhibitionsApi/";
    String HOSPITALITY_URL = "https://technex2017.herokuapp.com/api/hospitalityApi/";
    String FORGOT_PASSWORD_URL = "https://technex2017.herokuapp.com/api/forgotPass/ ";
    String LOGOUT_URL = "https://technex2017.herokuapp.com/api/logout/";
    String TOKEN_URL = "https://technex2017.herokuapp.com/api/notificationToken/";
//    String EVENT_REGISTER_URL_REMOTE = "http://www.technex.in/app/register_event/";
//    String USER_TEAMS = "http://www.technex.in/app/userteams/";
//    String REGISTER_URL_LOCAL = "http://52.24.177.3/register.php";

//    String PASS_KEY = "Vc2KK79Q9Y0914a797hp8kQlKJjcFMec";

}
