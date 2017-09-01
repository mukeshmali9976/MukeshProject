package com.mukeshproject.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import com.mukeshproject.R;
import com.mukeshproject.models.ProductitemListModel;
import java.util.ArrayList;

public class ProductItemList extends AppCompatActivity {

    public static final String TAG = ProductItemList.class.getSimpleName();
    GridView gvProductItemList;
    ArrayList<ProductitemListModel> productModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_item_list);

        gvProductItemList = (GridView)findViewById(R.id.gvproductlist);

    }
}
