package com.mukeshproject.ui.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import com.mukeshproject.R;
import com.mukeshproject.base.BaseAppCompatActivity;
import com.mukeshproject.network.NetworkManager;
import com.mukeshproject.network.RequestListener;
import com.mukeshproject.utils.CryptoManager;
import com.mukeshproject.utils.Utils;


public class OnlineRechargeActivity extends BaseAppCompatActivity implements RequestListener {

    private static final String TAG = OnlineRechargeActivity.class.getSimpleName();
    public static final String EXTRA_RECHARGE_TYPE = "extra_recharge_type";
    private SharedPreferences prefManager = null;
    private NetworkManager networkManager = null;
    private Spinner spOperatorName, spCircle;
    private EditText etMobileNumber, etAmount;
    private ImageButton imContactShow;
    private RadioGroup rgRechargeType;
    private Button btnRechargeNow;
    public static int MY_PERMISSIONS_REQUEST_READ_CONTACTS ;
    LinearLayout llRecharge;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_recharge);

        networkManager = NetworkManager.getInstance();
        prefManager = CryptoManager.getInstance(OnlineRechargeActivity.this).getPrefs();
        initActionBar(getResources().getString(R.string.title_login_user));
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
                imContactShow = (ImageButton) view.findViewById(R.id.imContactShow);
                imContactShow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (ContextCompat.checkSelfPermission(OnlineRechargeActivity.this,
                                Manifest.permission.READ_CONTACTS)
                                != PackageManager.PERMISSION_GRANTED) {

                            if (ActivityCompat.shouldShowRequestPermissionRationale(OnlineRechargeActivity.this,
                                    Manifest.permission.READ_CONTACTS)) {


                            } else {

                                ActivityCompat.requestPermissions(OnlineRechargeActivity.this,
                                        new String[]{Manifest.permission.READ_CONTACTS},
                                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                                // app-defined int constant. The callback method gets the
                                // result of the request.
                            }
                        }
                        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                        startActivityForResult(contactPickerIntent, 1);

                    }

                });

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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == Activity.RESULT_OK) {
                    Uri contactData = data.getData();

                    Cursor cur = getContentResolver().query(contactData, null, null, null, null);
                    if (cur.getCount() > 0) {
                        if (cur.moveToNext()) {
                            String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                            String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                            Log.e("Names", name);

                            if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {

                                Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id, null, null);
                                while (phones.moveToNext()) {
                                    String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                    Log.e("Number", phoneNumber);
                                    etMobileNumber.setText(phoneNumber);
                                }
                                phones.close();
                            }

                        }
                    }
                    cur.close();
                }
                break;
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
        displayError(message);

    }
}
