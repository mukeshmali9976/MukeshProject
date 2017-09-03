package com.mukeshproject.ui.fragments;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mukeshproject.R;
import com.mukeshproject.base.BaseFragment;
import com.mukeshproject.domain.adapters.HomeCategoryAdapter;
import com.mukeshproject.domain.adapters.SlidingImageAdapter;
import com.mukeshproject.models.HomeCategoryModel;
import com.mukeshproject.models.SlidingImageModel;
import com.mukeshproject.network.NetworkManager;
import com.mukeshproject.network.RequestListener;
import com.mukeshproject.ui.activities.MainActivity;
import com.mukeshproject.ui.activities.OnlineBillPayActivity;
import com.mukeshproject.utils.CryptoManager;
import com.mukeshproject.utils.Utils;
import com.viewpagerindicator.CirclePageIndicator;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends BaseFragment implements View.OnClickListener, RequestListener,
        HomeCategoryAdapter.OnInnerViewsClickListener {

    public static final String TAG = HomeFragment.class.getSimpleName();
    private View mRootView;

    private SharedPreferences prefManager = null;
    private NetworkManager networkManager = null;
    private Activity mActivity;

    private RecyclerView rvHomeCategoryList, rvRecharge;

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private ArrayList<SlidingImageModel> imageList;
    private int[] myImageList = new int[]{R.drawable.img2, R.drawable.img3,
            R.drawable.img2, R.drawable.img3};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_home, null);

        mActivity = getActivity();
        networkManager = NetworkManager.getInstance();
        prefManager = CryptoManager.getInstance(getActivity()).getPrefs();

        initView();
        return mRootView;


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    private void initView() {

        List<HomeCategoryModel> homeCategoryList = new ArrayList<>();
        HomeCategoryModel homeCategoryModel;

        for (int i = 0; i < 10; i++) {
            homeCategoryModel = new HomeCategoryModel();
            homeCategoryList.add(homeCategoryModel);
        }

        rvHomeCategoryList = (RecyclerView) mRootView.findViewById(R.id.rvHomeCategoryList);
        rvRecharge = (RecyclerView) mRootView.findViewById(R.id.rvRecharge);

        rvHomeCategoryList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rvHomeCategoryList.setItemAnimator(new DefaultItemAnimator());
        rvHomeCategoryList.setAdapter(new HomeCategoryAdapter(getActivity(), homeCategoryList, this));

        rvRecharge.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rvRecharge.setItemAnimator(new DefaultItemAnimator());
        rvRecharge.setAdapter(new HomeCategoryAdapter(getActivity(), homeCategoryList, this));

        imageList = new ArrayList<>();
        imageList = populateList();

        viewPager();
    }


    private ArrayList<SlidingImageModel> populateList() {

        ArrayList<SlidingImageModel> list = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            SlidingImageModel imageModel = new SlidingImageModel();
            imageModel.setImage_drawable(myImageList[i]);
            list.add(imageModel);
        }

        return list;
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
        initActionBar(getActivity().getString(R.string.app_name), mRootView);
        setTitle(getResources().getString(R.string.app_name));
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

    @Override
    public void onItemClick(View view, int position) {
        switch (view.getId()) {
            case R.id.llCategotyMain:
                startActivity(new Intent(getActivity(), OnlineBillPayActivity.class).putExtra(OnlineBillPayActivity.EXTRA_RECHARGE_TYPE,position));
                break;
        }
    }

    private void viewPager() {
        mPager = (ViewPager) mRootView.findViewById(R.id.viewPager);
        mPager.setAdapter(new SlidingImageAdapter(getActivity(), imageList));
        CirclePageIndicator indicator = (CirclePageIndicator) mRootView.findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
        indicator.setRadius(5 * getResources().getDisplayMetrics().density);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler().post(new Runnable() {
                    public void run() {
                        if (currentPage == imageList.size()) {
                            currentPage = 0;
                        }
                        mPager.setCurrentItem(currentPage++, true);
                    }
                });
            }
        }, 2000, 2000);

        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }
            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {
            }
            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }


}
