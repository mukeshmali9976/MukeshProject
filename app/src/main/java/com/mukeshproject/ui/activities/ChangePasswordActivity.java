package com.mukeshproject.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.mukeshproject.R;

public class ChangePasswordActivity extends AppCompatActivity {

    EditText etCurrentPassword,etNewPassword,etReTypePassword;
    Button btnSavePassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        etCurrentPassword = (EditText)findViewById(R.id.etcurrentpassword);
        etNewPassword = (EditText)findViewById(R.id.etnewpassword);
        etReTypePassword = (EditText)findViewById(R.id.etretypepassword);
        btnSavePassword = (Button)findViewById(R.id.btnsavepassword);

        btnSavePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etCurrentPassword.getText().toString().isEmpty()){
                    etCurrentPassword.setError("please enter current password");
                }
                if (etNewPassword.getText().toString().isEmpty()){
                    etNewPassword.setError("Please Enter New Password");
                }
                if (etReTypePassword.getText().toString().isEmpty()){

                    etReTypePassword.setError("please enter retray paswword");
                }
            }
        });
    }
}
