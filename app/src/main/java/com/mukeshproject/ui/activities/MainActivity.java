package com.mukeshproject.ui.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.mukeshproject.R;
import com.mukeshproject.ui.fragments.HomeFragment;
import com.mukeshproject.ui.fragments.MenuFragment;
import com.mukeshproject.ui.slidingmenu.SlidingActivity;
import com.mukeshproject.ui.slidingmenu.SlidingMenu;
import com.mukeshproject.ui.views.MyProgressDialog;
import com.mukeshproject.utils.CryptoManager;
import com.mukeshproject.utils.Log;
import com.mukeshproject.utils.TypefaceUtils;
import com.mukeshproject.utils.Utils;

public class MainActivity extends SlidingActivity{

    public static final String TAG = MainActivity.class.getSimpleName();

    private static int REQUEST_CHECK_SETTINGS = 101;
    private TextView mActionBarTitle;

    protected MenuFragment menuFragment;
    public Activity mActivity;


    private SharedPreferences prefManager = null;
    private MyProgressDialog dialog;

    private MenuItem menuItem;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);

        mActivity = this;
        prefManager = CryptoManager.getInstance(mActivity).getPrefs();
        setBehindContentView(R.layout.layout_menu_drawer_fragment_container);
        if (savedInstanceState == null) {
            FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
            menuFragment = new MenuFragment();
            t.replace(R.id.menuFragmentContainer, menuFragment);
            t.commit();
            replaceFragment(new HomeFragment(), "Home");
        } else {
            menuFragment = (MenuFragment) this.getSupportFragmentManager().findFragmentById(R.id.menuFragmentContainer);
        }
//        Collections.synchronizedCollection(mPostList);

        SlidingMenu sm = getSlidingMenu();
        sm.setMode(SlidingMenu.LEFT);
        sm.setShadowWidthRes(R.dimen.shadow_width);
        sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        sm.setFadeDegree(0.35f);
        sm.setFadeEnabled(true);
        sm.setOnOpenListener(new SlidingMenu.OnOpenListener() {
            @Override
            public void onOpen() {
                FragmentTransaction t = getSupportFragmentManager().beginTransaction();
                menuFragment = new MenuFragment();
                t.replace(R.id.menuFragmentContainer, menuFragment);
                t.commit();
            }
        });
    }

    public void restartActivity() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        menuItem = menu.findItem(R.id.action_menu_sync);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                toggle();
                break;
            case R.id.action_menu_sync:
                Toast.makeText(this,"Hjjdcdc",Toast.LENGTH_LONG).show();
                break;
            case R.id.action_menu_checkin_checkout:
                Utils.hideKeyboard(this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG, "Call MainActivity onActivityResult");
        Log.e(TAG, "requestCode" + requestCode);
        Log.e(TAG, "resultCode" + resultCode);

        if (requestCode == REQUEST_CHECK_SETTINGS) {
            if (resultCode == RESULT_OK) {
            }
        } else {
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
            if (fragment != null) {
                fragment.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    public void replaceFragment(Fragment frm, String tag) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, frm, tag).addToBackStack(null).commit();
    }

    public void addFragment(Fragment f) {
        this.addFragment(f, true);
    }

    public void addFragment(Fragment f, boolean clearStack) {
        if (clearStack) {
            getSupportFragmentManager().popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, f).addToBackStack(null).commit();
    }

    public Fragment getActiveFragment() {
        return getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
    }

    public void clearBackStack() {
        getSupportFragmentManager().popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    @Override
    public void onBackPressed() {
        if (getSlidingMenu().isMenuShowing()) {
            getSlidingMenu().toggle();
        }

        if (getActiveFragment() instanceof HomeFragment) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {

                try {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setTitle(R.string.alert);
                    builder.setCancelable(false);
                    builder.setMessage("finish");
                    builder.setPositiveButton(R.string.lbl_yes,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                    finish();
                                }
                            });
                    builder.setNegativeButton(R.string.lbl_no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    // create alert dialog
                    AlertDialog alertDialog = builder.create();
//                alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    // show it
                    alertDialog.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                getSupportFragmentManager().popBackStack();
            }
        } else {
            getSupportFragmentManager().popBackStack();
            replaceFragment(new HomeFragment(), "Home");
        }
    }





}
