package com.mukeshproject.ui.fragments;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mukeshproject.R;
import com.mukeshproject.network.NetworkManager;
import com.mukeshproject.network.RequestListener;
import com.mukeshproject.ui.activities.MainActivity;
import com.mukeshproject.utils.CryptoManager;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;



public class MenuFragment extends Fragment implements View.OnClickListener, RequestListener {

    public static final String TAG = MenuFragment.class.getSimpleName();

    private NetworkManager networkManager;
    private SharedPreferences prefManager = null;
    private View mRootView;


    private int reqIdLogout = -1;
    private TextView tvVersion, tvHome;
    private DisplayImageOptions options;

    @SuppressLint("InflateParams")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_drawer_menu, null);
        networkManager = NetworkManager.getInstance();
        prefManager = CryptoManager.getInstance(getActivity()).getPrefs();
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        initView();
        return mRootView;
    }

    private void initView() {


        tvVersion = (TextView) mRootView.findViewById(R.id.tvVersion);

//        tvHome = (TextView) mRootView.findViewById(R.id.tvHome);



//        tvHome.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));


//            setTextViewDrawableColor(tvHome, R.color.colorTextHint);
//            setTextViewDrawableColor(tvMyperformance, R.color.white_color);
//            setTextViewDrawableColor(tvNotification, R.color.white_color);
//            setTextViewDrawableColor(tvHelp, R.color.white_color);
//            setTextViewDrawableColor(tvLogout, R.color.white_color);
//            tvHome.setTextColor(ContextCompat.getColor(getActivity(), R.color.white_color));
//            tvMyperformance.setTextColor(ContextCompat.getColor(getActivity(), R.color.white_color));
//            tvNotification.setTextColor(ContextCompat.getColor(getActivity(), R.color.white_color));
//            tvHelp.setTextColor(ContextCompat.getColor(getActivity(), R.color.white_color));
//            tvLogout.setTextColor(ContextCompat.getColor(getActivity(), R.color.white_color));
//            tvName.setTextColor(ContextCompat.getColor(getActivity(), R.color.white_color));
//            tvVersion.setTextColor(ContextCompat.getColor(getActivity(), R.color.white_color));





//        tvHome.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        ((MainActivity) getActivity()).getSlidingMenu().toggle();
        v.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
        ((MainActivity) getActivity()).clearBackStack();
        switch (v.getId()) {
//            case R.id.tvHome:
//                ((MainActivity) getActivity()).replaceFragment(new HomeFragment(), "Home");
//                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        networkManager.setListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        networkManager.removeListener(this);
    }

    @Override
    public void onSuccess(int id, String response) {

    }

    @Override
    public void onError(int id, String message) {
        if (id == reqIdLogout) {

        }
    }

}
