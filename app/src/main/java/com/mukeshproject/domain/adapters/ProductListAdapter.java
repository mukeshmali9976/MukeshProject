package com.mukeshproject.domain.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.mukeshproject.models.ProductitemListModel;
import com.mukeshproject.models.SlidingImageModel;

import java.util.ArrayList;

/**
 * Created by lenovo pc on 30/08/2017.
 */

public class ProductListAdapter extends BaseAdapter {

    private ArrayList<ProductitemListModel> productArryList;
    private LayoutInflater inflater;
    @Override
    public int getCount() {

        return productArryList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        return null;
    }
}
