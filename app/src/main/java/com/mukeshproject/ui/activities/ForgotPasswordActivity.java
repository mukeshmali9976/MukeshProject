package com.mukeshproject.ui.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.mukeshproject.R;
import com.mukeshproject.base.BaseAppCompatActivity;
import com.mukeshproject.network.NetworkManager;
import com.mukeshproject.network.RequestListener;
import com.mukeshproject.utils.CryptoManager;

public class ForgotPasswordActivity extends BaseAppCompatActivity implements View.OnClickListener, RequestListener {

    public static final String TAG = ForgotPasswordActivity.class.getSimpleName();

    private SharedPreferences prefManager = null;
    private NetworkManager networkManager = null;

    EditText etEmail;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        networkManager = NetworkManager.getInstance();
        prefManager = CryptoManager.getInstance(ForgotPasswordActivity.this).getPrefs();

        initActionBar(getResources().getString(R.string.title_forgot_password));

        initView();

    }

    private void initView() {

        etEmail = (EditText)findViewById(R.id.etEmail);
        findViewById(R.id.btnSubmit).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        networkManager.setListener(this);
    }

    @Override
    protected void onStop() {
        networkManager.removeListener(this);
        super.onStop();
    }

    @Override
    public void onSuccess(int id, String response) {

    }

    @Override
    public void onError(int id, String message) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {

        }

    }

}
