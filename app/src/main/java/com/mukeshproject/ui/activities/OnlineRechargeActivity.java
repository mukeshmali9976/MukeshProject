package com.mukeshproject.ui.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.mukeshproject.R;
import com.mukeshproject.base.BaseAppCompatActivity;
import com.mukeshproject.models.ProductitemListModel;
import com.mukeshproject.network.NetworkManager;
import com.mukeshproject.network.RequestListener;
import com.mukeshproject.utils.Constants;
import com.mukeshproject.utils.CryptoManager;
import com.mukeshproject.utils.Utils;

public class OnlineRechargeActivity extends BaseAppCompatActivity implements RequestListener {

    private static final String TAG = OnlineRechargeActivity.class.getSimpleName();
    public static final String EXTRA_RECHARGE_TYPE = "extra_recharge_type";
    private SharedPreferences prefManager = null;
    private NetworkManager networkManager = null;
    private Spinner spOperatorName, spCircle;
    private EditText etMobileNumber, etAmount;
    private RadioGroup rgRechargeType;
    private Button btnRechargeNow;

    LinearLayout llRecharge;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_recharge);

        networkManager = NetworkManager.getInstance();
        prefManager = CryptoManager.getInstance(OnlineRechargeActivity.this).getPrefs();

        initView();

    }

    private void initView() {
        getIntentData();

    }

    private void getIntentData() {
        llRecharge = (LinearLayout) findViewById(R.id.llRecharge);
        View view = null;
        if (getIntent().hasExtra(EXTRA_RECHARGE_TYPE)) {
            if (getIntent().getIntExtra(EXTRA_RECHARGE_TYPE, -1) == 0) {
                view = LayoutInflater.from(this).inflate(R.layout.row_mobile_recharge, null);
                llRecharge.addView(view);
                etMobileNumber = (EditText) view.findViewById(R.id.etMobileNumber);
                spOperatorName = (Spinner) view.findViewById(R.id.spOperatorName);
                spCircle = (Spinner) view.findViewById(R.id.spCircle);
                etAmount = (EditText) view.findViewById(R.id.etAmount);

            } else if (getIntent().getIntExtra(EXTRA_RECHARGE_TYPE, -1) == 1) {
                view = LayoutInflater.from(this).inflate(R.layout.row_broadband_recharge, null);
                llRecharge.addView(view);
            } else if (getIntent().getIntExtra(EXTRA_RECHARGE_TYPE, -1) == 2) {
                view = LayoutInflater.from(this).inflate(R.layout.row_electicity_recharge, null);
                llRecharge.addView(view);
            }
            if (view != null)
                btnRechargeNow = (Button) view.findViewById(R.id.btnRechargeNow);
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
                ProductitemListModel productitemListModel = new Gson().fromJson(response, ProductitemListModel.class);
                if (productitemListModel.getStatus() == Constants.RESPONSE_200) {
                    //
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
