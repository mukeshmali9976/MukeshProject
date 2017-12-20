package com.mukeshproject.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.gson.Gson;
import com.mukeshproject.R;
import com.mukeshproject.base.BaseFragment;
import com.mukeshproject.domain.adapters.HomeCategoryAdapter;
import com.mukeshproject.domain.adapters.SlidingImageAdapter;
import com.mukeshproject.models.HomeCategoryModel;
import com.mukeshproject.models.SettingResponse;
import com.mukeshproject.models.SlidingImageModel;
import com.mukeshproject.network.NetworkManager;
import com.mukeshproject.network.RequestListener;
import com.mukeshproject.ui.activities.OnlineRechargeActivity;
import com.mukeshproject.utils.Constants;
import com.mukeshproject.utils.CryptoManager;
import com.mukeshproject.utils.Utils;
import com.viewpagerindicator.CirclePageIndicator;
import java.util.ArrayList;
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
    private SettingResponse settingResponse = null;
    private RecyclerView rvHomeCategoryList, rvRecharge,rvPayment;
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 5;
    private ArrayList<SlidingImageModel> imageList;

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

        settingResponse = new Gson().fromJson(prefManager.getString(Constants.PREF_SETTING_DATA, ""), SettingResponse.class);
        List<HomeCategoryModel> homeCategoryList = new ArrayList<>();
        HomeCategoryModel homeCategoryModel;

        for (int i = 0; i < 8; i++) {
            homeCategoryModel = new HomeCategoryModel();
            homeCategoryList.add(homeCategoryModel);

        }
        rvHomeCategoryList = (RecyclerView) mRootView.findViewById(R.id.rvHomeCategoryList);
        rvRecharge = (RecyclerView) mRootView.findViewById(R.id.rvRecharge);
        rvPayment = (RecyclerView)mRootView.findViewById(R.id.rvPayment);

        rvPayment.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        rvPayment.setItemAnimator(new DefaultItemAnimator());
        rvPayment.setAdapter(new HomeCategoryAdapter(getActivity(),homeCategoryList,this));

        rvRecharge.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rvRecharge.setItemAnimator(new DefaultItemAnimator());
        rvRecharge.setAdapter(new HomeCategoryAdapter(getActivity(), homeCategoryList, this));


//        rvHomeCategoryList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
//        rvHomeCategoryList.setItemAnimator(new DefaultItemAnimator());
//        rvHomeCategoryList.setAdapter(new HomeCategoryAdapter(getActivity(), homeCategoryList, this));

        viewPager();
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
                startActivity(new Intent(getActivity(), OnlineRechargeActivity.class).putExtra(OnlineRechargeActivity.EXTRA_RECHARGE_TYPE,position));
                break;
        }
    }

    private void viewPager() {
        mPager = (ViewPager) mRootView.findViewById(R.id.viewPager);
        mPager.setAdapter(new SlidingImageAdapter(getActivity(),settingResponse.getResult().getSlider() ));
        CirclePageIndicator indicator = (CirclePageIndicator) mRootView.findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
        indicator.setRadius(5 * getResources().getDisplayMetrics().density);
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
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
