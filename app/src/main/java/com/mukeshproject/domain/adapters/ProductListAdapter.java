package com.mukeshproject.domain.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mukeshproject.R;
import com.mukeshproject.models.ProductitemListModel;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

/**
 * Created by lenovo pc on 30/08/2017.
 */

public class ProductListAdapter extends BaseAdapter {

    private ArrayList<ProductitemListModel> productArryList;
    private LayoutInflater inflater;
    private Context context;
    private ImageLoader mImageLoader;
    private DisplayImageOptions options;

    public ProductListAdapter(Context context, ArrayList<ProductitemListModel> productArryList) {
        this.productArryList = productArryList;
        this.context = context;


        inflater = LayoutInflater.from(context);
        mImageLoader = ImageLoader.getInstance();
        mImageLoader.init(ImageLoaderConfiguration.createDefault(context));
        options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.img2)
                .showImageForEmptyUri(R.drawable.img2)
                .showImageOnFail(R.drawable.img2)
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .build();

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
            viewHolder.tvProductName.setText(productArryList.get(position).getProduct_name());
        }
        return convertView;
    }

    private class ViewHolder {
        ImageView ivproductImage;
        TextView tvProductName, tvProductPrice;
        RatingBar rbProductRating;

        public ViewHolder(View view) {

            ivproductImage = (ImageView) view.findViewById(R.id.ivProductImage);
            tvProductName = (TextView) view.findViewById(R.id.tvProductName);
            tvProductPrice = (TextView) view.findViewById(R.id.tvProductPrice);
            rbProductRating = (RatingBar) view.findViewById(R.id.rbProductRating);
        }
    }
}
