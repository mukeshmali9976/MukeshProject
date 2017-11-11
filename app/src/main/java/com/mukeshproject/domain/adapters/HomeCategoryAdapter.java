package com.mukeshproject.domain.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mukeshproject.R;
import com.mukeshproject.models.HomeCategoryModel;
import com.mukeshproject.ui.fragments.HomeFragment;
import com.mukeshproject.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

/**
 * Created by lenovo pc on 27/08/2017.
 */

public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.MyViewHolder> {


    private Context context;
    private List<HomeCategoryModel> homeCategoryList;
    private OnInnerViewsClickListener mListner;

    public HomeCategoryAdapter(Context context, List<HomeCategoryModel> homeCategoryList, OnInnerViewsClickListener mListner) {
        this.context = context;
        this.homeCategoryList = homeCategoryList;
        this.mListner = mListner;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_home_main_category, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        HomeCategoryModel rowObject = homeCategoryList.get(position);

        holder.tvCategoryName.setText(rowObject.getName());
        Picasso.with(context).load(rowObject.getImageName()).into(holder.ivCategory);
    }
    @Override
    public int getItemCount() {
        return homeCategoryList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCategoryName;
        private ImageView ivCategory;
        private LinearLayout llCategotyMain;


        public MyViewHolder(View itemView) {
            super(itemView);
            tvCategoryName = (TextView) itemView.findViewById(R.id.tvCategoryName);
            ivCategory = (ImageView) itemView.findViewById(R.id.ivCategory);
            llCategotyMain = (LinearLayout) itemView.findViewById(R.id.llCategotyMain);
            llCategotyMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListner.onItemClick(view, getAdapterPosition());

                }
            });
        }
    }
    public interface OnInnerViewsClickListener {

        void onItemClick(View view, int position);
    }
}
