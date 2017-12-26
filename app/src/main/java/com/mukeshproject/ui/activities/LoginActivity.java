package com.mukeshproject.ui.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mukeshproject.R;
import com.mukeshproject.base.BaseAppCompatActivity;
import com.mukeshproject.network.NetworkManager;
import com.mukeshproject.network.RequestListener;
import com.mukeshproject.network.RequestMethod;
import com.mukeshproject.request.PARAMS;
import com.mukeshproject.request.RequestBuilder;
import com.mukeshproject.utils.CryptoManager;

/**
 * Created by lenovo pc on 07/09/2017.
 */

public class LoginActivity extends BaseAppCompatActivity implements View.OnClickListener, RequestListener {

    public static final String TAG = LoginActivity.class.getSimpleName();
    private int reqIdLogin = -1;

    private EditText etEmail,etPassword;
    private TextView tvErorrEmail, tvErorrPassword;

    private SharedPreferences prefManager = null;
    private NetworkManager networkManager = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        networkManager = NetworkManager.getInstance();
        prefManager = CryptoManager.getInstance(LoginActivity.this).getPrefs();

        initActionBar(getResources().getString(R.string.title_login_user));

        initView();
    }

    private void initView() {

        etEmail = (EditText)findViewById(R.id.etEmail);
        etPassword = (EditText)findViewById(R.id.etPassword);

        tvErorrEmail = (TextView)findViewById(R.id.tvErorrEmail);
        tvErorrPassword = (TextView)findViewById(R.id.tvErorrPassword);

        findViewById(R.id.btnLogin).setOnClickListener(this);
        findViewById(R.id.btnRegister).setOnClickListener(this);
        findViewById(R.id.tvForgotPassword).setOnClickListener(this);
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
        switch (view.getId()) {
            case R.id.btnLogin:
                validationLogin();


                break;

            case R.id.btnRegister:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;

            case R.id.tvForgotPassword:
                    showforgotpassworddialog();

//                startActivity(new Intent(LoginActivity.this,ForgotPasswordActivity.class));
//                break;
        }

    }

    private void showforgotpassworddialog() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_forgot_password, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setTitle("Forgot Password");
        final AlertDialog b = dialogBuilder.create();
        b.show();
    }

    private void validationLogin() {

        if (etEmail.getText().toString().isEmpty()){
            tvErorrEmail.setVisibility(View.VISIBLE);
        }
        else {
            tvErorrEmail.setVisibility(View.GONE);
        }
        if (etPassword.getText().toString().isEmpty()){
            tvErorrPassword.setVisibility(View.VISIBLE);
        }
        else {
            tvErorrPassword.setVisibility(View.GONE);
            performLogin();
        }
    }
    private void performLogin() {

        networkManager.isProgressBarVisible(true);
        reqIdLogin = networkManager.addRequest(RequestBuilder.performLogin(etEmail.getText().toString().trim(),etPassword.getText().toString().trim(),PARAMS.TAG_DEVICE_TOKEN),LoginActivity.this, RequestMethod.POST,RequestBuilder.METHOD_SETTING);
    }
    @Override
    public void onSuccess(int id, String response) {

    }
    @Override
    public void onError(int id, String message) {

        displayError(message);

    }
}
