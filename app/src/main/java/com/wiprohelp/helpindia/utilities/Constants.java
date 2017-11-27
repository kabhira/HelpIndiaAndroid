package com.wiprohelp.helpindia.utilities;

/**
 * Created by AB335009 on 12/30/2015.
 */
public class Constants {
    public static String REQUEST_CONTACTID = "CategoryID";
    public static String REQUEST_PROBLEM = "Problem";
//    public static String REQUEST_VICTIM_NAME = "VictimName";
//    public static String REQUEST_VICTIM_AGE = "VictimAge";
//    public static String REQUEST_PHONE = "Phone1";
//    public static String REQUEST_OPT_PHONE = "OptionalPhone";
    public static String REGISTRATION_LATITUDE = "Latitude";
    public static String REGISTRATION_LONGITUDE = "Longitude";
    public static String REQUEST_ADDRESS = "Address";
    public static String REQUEST_DURATION = "Duration";

    public static String VOLUNTEER_REGISTRATION_EMAIL = "VolunteerEmail";
    public static String VOLUNTEER_REGISTRATION_ADDRESS = "VolunteerAddress";
    public static String VOLUNTEER_REGISTRATION_DAYS_AVAILABLE = "VolunteerDaysAvailable";
    public static String VOLUNTEER_REGISTRATION_TIME_AVAILABLE = "VolunteerTimeAvailable";
    public static String VOLUNTEER_REGISTRATION_SPECIALITY = "VolunteerSpeciality";

    public static String VICTIM_LATITUDE = "VictimLatitude";
    public static String VICTIM_LONGITUDE = "VictimLongitude";
    public static String VICTIM_MOBILE = "VictimMobile";
    public static String VICTIM_NAME = "VictimName";
    public static String VICTIM_ADDRESS = "VictimAddress";

    public static String CONTACT_VIEW_MODE = "Mode";
    public static String CONTACT_VIEW_VICTIM = "ContactVictim";
    public static String CONTACT_VIEW_VOLUNTEER = "ContactVolunteer";
    public static String SHARED_PREF_NAME = "HelpIndia";
    public static String GCM_REGISTRATION_FLAG = "GcmRegistrationFlag";
    public static String TRACK_REQUEST_MOBILE = "TrackRequestMobile";
    public static int PLAY_SERVICES_RESOLUTION_REQUEST = 20;

    // URLs
    public static String BASE_URL = "http://192.168.43.6:8080/";
    public static String ALL_GATEGORY_URL = "getAllCategoriesList";
    public static String NEW_HELP_REQUEST_URL = "creatingNewRequest";
    public static String TRACK_REQUEST_URL = "getAllRequestToUser";
    public static String VOLUNTEER_REGISTRATION_URL = "userRegistration";
    public static String ALL_REQUEST_TO_VOLUNTEER_URL = "getAllRequestToHelpProviders";
    public static String HELP_REQUEST_UPDATE_URL = "getupdateRequest";
    public static String LOAD_VOLUNTEER_PROFILE_URL = "getUserData";


        public static String SERVER_STATUS_SUCCESS = "success";
        public static String SERVER_STATUS_FAILED = "failed";

}