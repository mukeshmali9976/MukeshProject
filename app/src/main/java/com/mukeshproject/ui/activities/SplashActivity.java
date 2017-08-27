package com.mukeshproject.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.mukeshproject.R;
import com.mukeshproject.base.BaseAppCompatActivity;
import com.mukeshproject.network.NetworkManager;
import com.mukeshproject.network.RequestListener;
import com.mukeshproject.utils.CryptoManager;
import com.mukeshproject.utils.Utils;

public class SplashActivity extends BaseAppCompatActivity implements RequestListener {

    private static final String TAG = SplashActivity.class.getSimpleName();
    private SharedPreferences prefManager = null;
    private NetworkManager networkManager = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        networkManager = NetworkManager.getInstance();
        prefManager = CryptoManager.getInstance(SplashActivity.this).getPrefs();

        startActivity(new Intent(SplashActivity.this, MainActivity.class));
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

    @Override
    public void onSuccess(int id, String response) {
        try {
            if (!Utils.isEmptyString(response)) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(int id, String message) {
    }
}
