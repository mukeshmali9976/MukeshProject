package com.mukeshproject.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mukeshproject.R;
import com.mukeshproject.base.BaseFragment;
import com.mukeshproject.domain.adapters.HomeCategoryAdapter;
import com.mukeshproject.models.HomeCategoryModel;
import com.mukeshproject.network.NetworkManager;
import com.mukeshproject.network.RequestListener;
import com.mukeshproject.utils.CryptoManager;
import com.mukeshproject.utils.Utils;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeFragment extends BaseFragment implements View.OnClickListener, RequestListener {

    public static final String TAG = HomeFragment.class.getSimpleName();
    private View mRootView;

    private SharedPreferences prefManager = null;
    private NetworkManager networkManager = null;
    private Activity mActivity;

    private RecyclerView rvHomeCategoryList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_home, null);

        mActivity = getActivity();
        networkManager = NetworkManager.getInstance();
        prefManager = CryptoManager.getInstance(getActivity()).getPrefs();

        initView();
        return mRootView;
    }


    private void initView() {

        List<HomeCategoryModel> homeCategoryList = new ArrayList<>();
        HomeCategoryModel homeCategoryModel;

        for (int i = 0; i < 10 ; i++) {
            homeCategoryModel = new HomeCategoryModel();
            homeCategoryList.add(homeCategoryModel);
        }

        rvHomeCategoryList = (RecyclerView) mRootView.findViewById(R.id.rvHomeCategoryList);
        rvHomeCategoryList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        rvHomeCategoryList.setItemAnimator(new DefaultItemAnimator());
        rvHomeCategoryList.setAdapter(new HomeCategoryAdapter(getActivity(),homeCategoryList));


    }

    @Override
    public void onStart() {
        networkManager.setListener(this);
        super.onStart();
    }

    @Override
    public void onStop() {
        networkManager.removeListener(this);
        super.onStop();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initActionBar("Eazylo", mRootView);
        setTitle("Eazylo");
        if (Utils.isInternetAvailable(mActivity)) {

        }
    }


    @Override
    public void onSuccess(int id, String response) {
        try {
            if (!TextUtils.isEmpty(response)) {
            } else {
                displayError(getString(R.string.no_network_connection));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(int id, String message) {
        displayError(message);
    }

    @Override
    public void onClick(View view) {

    }
}
