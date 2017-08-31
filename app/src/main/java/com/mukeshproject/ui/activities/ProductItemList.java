package com.mukeshproject.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.mukeshproject.R;

public class ProductItemList extends AppCompatActivity {

    public static final String TAG = ProductItemList.class.getSimpleName();
    GridView gvProductItemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_item_list);

        gvProductItemList = (GridView)findViewById(R.id.gvproductlist);

    }
}
