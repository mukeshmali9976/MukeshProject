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

public class RegisterActivity extends BaseAppCompatActivity implements View.OnClickListener,RequestListener {

    public static final String TAG = RegisterActivity.class.getSimpleName();

    private SharedPreferences prefManager = null;
    private NetworkManager networkManager = null;

    EditText etFirstName,etEmail,etContactNumber,etPassword,etConfirmPassword;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        networkManager = NetworkManager.getInstance();
        prefManager = CryptoManager.getInstance(RegisterActivity.this).getPrefs();

        initActionBar(getResources().getString(R.string.title_register_user));

        initView();
    }

    private void initView() {

        etFirstName = (EditText)findViewById(R.id.etFirstName);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etContactNumber = (EditText)findViewById(R.id.etContactNumber);
        etPassword = (EditText)findViewById(R.id.etPassword);
        etConfirmPassword = (EditText)findViewById(R.id.etConfirmPassword);

        findViewById(R.id.btnRegister).setOnClickListener(this);

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
    public void onClick(View view) {


    }

    @Override
    public void onSuccess(int id, String response) {

    }

    @Override
    public void onError(int id, String message) {

        displayError(message);

    }
}
