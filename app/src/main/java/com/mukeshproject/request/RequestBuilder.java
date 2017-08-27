package com.mukeshproject.request;


import com.mukeshproject.utils.Constants;
import com.mukeshproject.utils.Log;


import org.json.JSONException;
import org.json.JSONObject;


import java.util.HashMap;

public class RequestBuilder extends PARAMS {
    private static final String TAG = RequestBuilder.class.getSimpleName();


    public static final String METHOD_FETCH_ASSIGNMENTS = "user/availableassignments";

    /**
     * The constant METHOD_LOGOUT.
     */
    public static final String METHOD_LOGOUT = "user/logout";
    /**
     * The constant METHOD_USER_LOGIN.
     */
// to get Login Response
    public static final String METHOD_USER_LOGIN = "user/login";
    /**
     * The constant METHOD_USER_REGISTER.
     */
// to Register User
    public static final String METHOD_USER_REGISTER = "user/createaccount";


    public static HashMap<String, String> blankRequest() {

        JSONObject jObjReq = new JSONObject();

        HashMap<String, String> parameters = new HashMap<>();
//        parameters.put(TAG_PARAMS, jObjReq.toString());
        Log.i(TAG, jObjReq.toString());
        return parameters;
    }


    public static HashMap<String, String> performLogin(String email, String password, String device_token, String lat, String lng) {
        JSONObject jObjReq = new JSONObject();
        HashMap<String, String> parameters = new HashMap<>();
        try {
            jObjReq.put(PARAMS.TAG_EMAIL, email);
            jObjReq.put(PARAMS.TAG_PASSWORD, password);
            jObjReq.put(PARAMS.TAG_DEVICE_TOKEN, device_token);
            jObjReq.put(PARAMS.TAG_DEVICE_TYPE, Constants.DEVICE_TYPE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        parameters.put(TAG_PARAMS, jObjReq.toString());
        Log.i(TAG, jObjReq.toString());
        return parameters;
    }


}
