package com.mukeshproject.ui.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.GridView;
import com.mukeshproject.R;
import com.mukeshproject.base.BaseAppCompatActivity;
import com.mukeshproject.domain.adapters.ProductListAdapter;
import com.mukeshproject.models.ProductitemListModel;
import com.mukeshproject.models.SettingResponse;
import com.mukeshproject.network.NetworkManager;
import com.mukeshproject.network.RequestListener;
import com.mukeshproject.network.RequestMethod;
import com.mukeshproject.request.RequestBuilder;
import com.mukeshproject.utils.CryptoManager;
import com.mukeshproject.utils.Log;

import java.util.ArrayList;

public class ProductItemList extends BaseAppCompatActivity implements RequestListener {

    public static final String TAG = ProductItemList.class.getSimpleName();

    private SharedPreferences prefmanager = null;
    private NetworkManager networkManager = null;
    private SettingResponse settingResponse = null;
    private int reqIDSetting = -1;

    GridView gvProductItemList;
    ArrayList<ProductitemListModel> productModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_item_list);

        networkManager = NetworkManager.getInstance();
        prefmanager = CryptoManager.getInstance(ProductItemList.this).getPrefs();
        init();

        getProductList();

    }

    private void getProductList() {

        networkManager.isProgressBarVisible(false);
        reqIDSetting = networkManager.addRequest(RequestBuilder.blankRequest(),this, RequestMethod.POST,RequestBuilder.METHOD_PRODUCT_LIST);
    }

    private void init() {

        gvProductItemList = (GridView)findViewById(R.id.gvProductItemList);
        gvProductItemList.setAdapter(new ProductListAdapter(this,settingResponse.getResult().getProductlist()));
        Log.e("product","pro"+settingResponse.getResult().getProductlist());
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
    public void onSuccess(int id, String response) {

    }

    @Override
    public void onError(int id, String message) {

       displayError(message);
    }
}
