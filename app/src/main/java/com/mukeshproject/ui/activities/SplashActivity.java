package com.mukeshproject.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.gson.Gson;
import com.mukeshproject.R;
import com.mukeshproject.base.BaseAppCompatActivity;
import com.mukeshproject.models.SettingResponse;
import com.mukeshproject.network.NetworkManager;
import com.mukeshproject.network.RequestListener;
import com.mukeshproject.network.RequestMethod;
import com.mukeshproject.request.RequestBuilder;
import com.mukeshproject.utils.Constants;
import com.mukeshproject.utils.CryptoManager;
import com.mukeshproject.utils.Utils;

public class SplashActivity extends BaseAppCompatActivity implements RequestListener {

    private static final String TAG = SplashActivity.class.getSimpleName();
    private SharedPreferences prefManager = null;
    private NetworkManager networkManager = null;
    private int reqIDSetting = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        networkManager = NetworkManager.getInstance();
        prefManager = CryptoManager.getInstance(SplashActivity.this).getPrefs();
        getSettingData();


    }

    @Override
    public void onStart() {
        super.onStart();
        networkManager.setListener(this);
    }

    @Override
    public void onStop() {
        networkManager.removeListener(this);
        super.onStop();
    }

    private void getSettingData() {
        networkManager.isProgressBarVisible(false);
        reqIDSetting = networkManager.addRequest(RequestBuilder.blankRequest(), this, RequestMethod.POST, RequestBuilder.METHOD_SETTING);
    }

    @Override
    public void onSuccess(int id, String response) {
        try {
            if (!Utils.isEmptyString(response)) {
                if (reqIDSetting == id) {
                    SettingResponse settingResponse = new Gson().fromJson(response, SettingResponse.class);
                    if (settingResponse.getStatus().equalsIgnoreCase(Constants.RESPONSE_200)) {
                        prefManager.edit().putString(Constants.PREF_SETTING_DATA, response).apply();
                        startActivity(new Intent(SplashActivity.this, MainActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(int id, String message) {
        displayError(message);
    }
}
