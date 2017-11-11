package com.mukeshproject.request;


import com.mukeshproject.utils.Constants;
import com.mukeshproject.utils.Log;


import org.json.JSONException;
import org.json.JSONObject;


import java.util.HashMap;

public class RequestBuilder extends PARAMS {
    private static final String TAG = RequestBuilder.class.getSimpleName();


    public static final String METHOD_SETTING = "/api";
    public static final String METHOD_PRODUCT_LIST = "/api/productlist";
    public static final String METHOD_RECHARGE = "";

    public static HashMap<String, String> blankRequest() {
        JSONObject jObjReq = new JSONObject();
        HashMap<String, String> parameters = new HashMap<>();
        Log.i(TAG, jObjReq.toString());
        return parameters;
    }
    public static HashMap<String, String> performLogin(String email, String password, String device_token) {
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
