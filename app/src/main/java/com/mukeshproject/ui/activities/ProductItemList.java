package com.mukeshproject.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.mukeshproject.R;
import com.mukeshproject.models.ProductItemListModel;

import java.util.ArrayList;

public class ProductItemList extends AppCompatActivity {
    public static final String TAG = ProductItemList.class.getSimpleName();
    GridView gvProductItemList;
    ArrayList<ProductItemListModel> productModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_item_list);
        gvProductItemList = (GridView) findViewById(R.id.gvProductItemList);
    }
}
