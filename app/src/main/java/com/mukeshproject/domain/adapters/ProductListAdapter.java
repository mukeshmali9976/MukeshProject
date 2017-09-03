package com.mukeshproject.domain.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mukeshproject.R;
import com.mukeshproject.models.ProductitemListModel;

import java.util.ArrayList;

/**
 * Created by lenovo pc on 30/08/2017.
 */

public class ProductListAdapter extends BaseAdapter {

    private ArrayList<ProductitemListModel> productArryList;
    private LayoutInflater inflater;
    private Context context;

    public ProductListAdapter(Context context, ArrayList<ProductitemListModel> productArryList) {
        this.productArryList = productArryList;
        this.context = context;
    }


    @Override
    public int getCount() {
        return productArryList.size();
    }

    @Override
    public Object getItem(int position) {
        return productArryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_product_item_list, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    private class ViewHolder {
        ImageView ivproductlist;
        TextView tvProductName, tvProductPrice;
        RatingBar rbProductRating;

        public ViewHolder(View view) {

            ivproductlist = (ImageView) view.findViewById(R.id.ivProductList);
            tvProductName = (TextView) view.findViewById(R.id.tvProductName);
            tvProductPrice = (TextView) view.findViewById(R.id.tvProductPrice);
            rbProductRating = (RatingBar) view.findViewById(R.id.rbProductRating);
        }
    }

}
