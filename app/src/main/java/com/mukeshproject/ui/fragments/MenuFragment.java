package com.mukeshproject.ui.fragments;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.gson.Gson;
import com.mukeshproject.R;
import com.mukeshproject.domain.adapters.MenuAdapter;
import com.mukeshproject.models.MenuResponse;
import com.mukeshproject.models.SettingResponse;
import com.mukeshproject.network.NetworkManager;
import com.mukeshproject.network.RequestListener;
import com.mukeshproject.ui.activities.MainActivity;
import com.mukeshproject.utils.Constants;
import com.mukeshproject.utils.CryptoManager;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.ArrayList;


public class MenuFragment extends Fragment implements View.OnClickListener, RequestListener, MenuAdapter.OnInnerViewsClickListener {

    public static final String TAG = MenuFragment.class.getSimpleName();

    private NetworkManager networkManager;
    private SharedPreferences prefManager = null;
    private View mRootView;
    private SettingResponse settingResponse;
    private ArrayList<MenuResponse> listMenuResponse;
    private MenuAdapter menuAdapter;
    private String menuString;
    private TextView tvBackButton;

    private int reqIdLogout = -1;
    private DisplayImageOptions options;
    private RecyclerView rvMenu;
    private int mPostion = -1;

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

        tvBackButton = (TextView) mRootView.findViewById(R.id.tvBackButton);
        tvBackButton.setOnClickListener(this);

        settingResponse = new Gson().fromJson(prefManager.getString(Constants.PREF_SETTING_DATA, ""), SettingResponse.class);
        rvMenu = (RecyclerView) mRootView.findViewById(R.id.rvMenu);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        rvMenu.setLayoutManager(mLayoutManager);
        setAdapterData(Constants.MENU_MAIN, settingResponse.getResult().getMenudetails(), false);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvBackButton:
                if (menuString.equalsIgnoreCase(Constants.MENU_MAIN)) {
                    tvBackButton.setText(getString(R.string.lbl_category));
                    setAdapterData(Constants.MENU_MAIN, settingResponse.getResult().getMenudetails(), false);

                } else if (menuString.equalsIgnoreCase(Constants.MENU_SUB_MENU)) {
                    menuString = Constants.MENU_MAIN;
                    tvBackButton.setText(settingResponse.getResult().getMenudetails().get(mPostion).getMenu_name());
                    setAdapterData(Constants.MENU_SUB_MENU, settingResponse.getResult().getMenudetails().get(mPostion).getSubmenu_details(), true);
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

    @Override
    public void onItemClick(View view, int position, String menu) {
        menuString = menu;
        switch (view.getId()) {
            case R.id.llRow:
                if (menu.equalsIgnoreCase(Constants.MENU_MAIN)) {
                    mPostion = position;
                    if (settingResponse.getResult().getMenudetails().get(position).getSubmenu_details().size() > 0) {
                        tvBackButton.setText(settingResponse.getResult().getMenudetails().get(position).getMenu_name());
                        setAdapterData(Constants.MENU_SUB_MENU, settingResponse.getResult().getMenudetails().get(position).getSubmenu_details(), true);
                    }
                } else if (menu.equalsIgnoreCase(Constants.MENU_SUB_MENU)) {
                    if (mPostion != -1) {
                        if (settingResponse.getResult().getMenudetails().get(mPostion).getSubmenu_details().get(position).getSubsubmenudetails().size() > 0) {
                            tvBackButton.setText(settingResponse.getResult().getMenudetails().get(mPostion).getSubmenu_details().get(position).getSubmenu());
                            setAdapterData(Constants.MENU_SUB_OF_SUB_MENU, settingResponse.getResult().getMenudetails().get(mPostion).getSubmenu_details().get(position).getSubsubmenudetails(), true);
                        }
                    }
                } else {

                    ((MainActivity) getActivity()).getSlidingMenu().toggle(true);
                    ((MainActivity) getActivity()).clearBackStack();
                    ((MainActivity) getActivity()).addFragment(new HomeFragment());

                }
                break;
        }

    }
    private void setAdapterData(String menu, ArrayList<MenuResponse> menuResponsesList, boolean isBackVisible) {
        if (isBackVisible) {
            tvBackButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_blue, 0, 0, 0);
        } else {
            tvBackButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }
        menuAdapter = new MenuAdapter(getActivity(), menuResponsesList, menu, this);
        rvMenu.setAdapter(menuAdapter);
    }
}
