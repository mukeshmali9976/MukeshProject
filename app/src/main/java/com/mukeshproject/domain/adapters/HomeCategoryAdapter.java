package com.mukeshproject.domain.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mukeshproject.R;
import com.mukeshproject.models.HomeCategoryModel;

import java.util.List;

/**
 * Created by lenovo pc on 27/08/2017.
 */

public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.MyViewHolder> {


    private Context context;
    private List<HomeCategoryModel> homeCategoryList;

    public HomeCategoryAdapter(Context context, List<HomeCategoryModel> homeCategoryList) {
        this.context = context;
        this.homeCategoryList = homeCategoryList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_home_main_category, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return homeCategoryList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCategoryName;
        private ImageView ivCategory;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvCategoryName = (TextView) itemView.findViewById(R.id.tvCategoryName);
            ivCategory = (ImageView) itemView.findViewById(R.id.ivCategory);
        }
    }
}
