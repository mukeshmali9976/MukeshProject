package com.mukeshproject.ui.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.mukeshproject.R;
import com.mukeshproject.base.BaseAppCompatActivity;
import com.mukeshproject.network.NetworkManager;
import com.mukeshproject.network.RequestListener;
import com.mukeshproject.utils.CryptoManager;
import com.mukeshproject.utils.Utils;

public class OnlineBillPayActivity extends BaseAppCompatActivity implements RequestListener {

    private static final String TAG = OnlineBillPayActivity.class.getSimpleName();
    public static final String EXTRA_RECHARGE_TYPE = "extra_recharge_type";
    private SharedPreferences prefManager = null;
    private NetworkManager networkManager = null;

    LinearLayout llRecharge;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_bill_pay);

        networkManager = NetworkManager.getInstance();
        prefManager = CryptoManager.getInstance(OnlineBillPayActivity.this).getPrefs();
        initView();
        getIntentData();
    }

    private void initView() {
        llRecharge = (LinearLayout) findViewById(R.id.llRecharge);
    }

    private void getIntentData() {
        if (getIntent().hasExtra(EXTRA_RECHARGE_TYPE)) {
            if (getIntent().getIntExtra(EXTRA_RECHARGE_TYPE, -1) == 0) {
                View view = LayoutInflater.from(this).inflate(R.layout.row_mobile_recharge, null);
                llRecharge.addView(view);
            } else {
                View view = LayoutInflater.from(this).inflate(R.layout.row_broadband_recharge, null);
                llRecharge.addView(view);
            }
        }
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
