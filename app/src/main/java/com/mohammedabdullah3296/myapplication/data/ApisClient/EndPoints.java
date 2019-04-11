package com.mohammedabdullah3296.myapplication.data.ApisClient;


// this class define to hold all server constants and end points
public class EndPoints {
   public static final String BASE_URL = "http://unicomtrade.com/survey/api/";
    public static final String GET_ALL_ITEMS = "1bnsyj";

    //public static final String BASE_URL = "http://192.168.3.70:8080/api/";

    public static final String GET_USER_PROJECTS = "user_projects/{user_id}";


    //login By Amr Rabie
    public static final String GET_LOGin = "login";

    //By Elamary
    public static final String GET_GOVERNOR = "get_governorates";
    public static final String GET_CITIES = "get_cities";
    public static final String GET_DISTRICTS = "get_districts";

    // by Belal
    public static final String GET_POSITIONS = "positions";
    public static final String GET_OFFICES = "offices";
    public static final String GET_COMMENTS = "has_feedback_offices/{user_id}";
    public static final String SYNC_SURVEYS = "add_office_data";

    public static final String ADD_SKETCHING = "add_sketching/{office_data_id}";



}
