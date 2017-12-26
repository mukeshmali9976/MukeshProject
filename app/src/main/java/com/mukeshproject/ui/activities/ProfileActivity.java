package com.mukeshproject.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.mukeshproject.R;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnProfileLogin, btnProfileRegister;
    TextView tvInvite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnProfileLogin = (Button) findViewById(R.id.btnProfileLogin);
        btnProfileRegister = (Button) findViewById(R.id.btnProfileRegister);
        tvInvite = (TextView) findViewById(R.id.tvinvite);

        btnProfileLogin.setOnClickListener(this);
        btnProfileRegister.setOnClickListener(this);

        tvInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                intent.setType("text/plain");
                String shareBodyText = "https://play.google.com/store/apps/details?id=com.app.eazylo&hl=en";
                intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject/Title");
                intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
                startActivity(Intent.createChooser(intent, "Choose sharing method"));
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnProfileLogin:
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intent);
                break;

            case R.id.btnProfileRegister:
                Intent intent1 = new Intent(ProfileActivity.this, RegisterActivity.class);
                startActivity(intent1);
                break;
        }

    }
}
