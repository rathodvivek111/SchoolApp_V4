package com.example.rathods.schoolapp_v4.utility;

/**
 * Created by RATHOD'S on 6/10/2017.
 */

public class WebConfig {

    public static String BASE_URL = "http://schoolmanagementapp.16mb.com/schoolapp/";
  //  public static String BASE_URL = "http://192.168.0.108/schoolapp/";

    public static String LOGIN = BASE_URL + "getlogin.php";
    //public static String REQUEST_SMS = BASE_URL + "getlogin.php";
    public static String GETALBUMS = BASE_URL + "getalbums.php";
    public static String GETNOTICE = BASE_URL + "getnotice.php";
    public static String GETNEWS = BASE_URL + "getnews.php";
    public static String GETEVENTS = BASE_URL + "getevents.php";
    public static String GETPICTURES = BASE_URL + "getpictures.php";
    public static String GETPARENTINFO = BASE_URL + "getparentinfo.php";


    ////// PARAMS
    public static String PARAM_TYPE = "type";
    public static String PARAM_MOBILE_NUMBER = "mobile_number";
    public static String PARAM_GALLERY_ID = "gallery_id";
    public static String PARAM_USER_TOKEN = "user_token";
}
