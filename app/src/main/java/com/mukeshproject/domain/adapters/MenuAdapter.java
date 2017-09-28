package com.mukeshproject.domain.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mukeshproject.R;
import com.mukeshproject.models.MenuResponse;
import com.mukeshproject.utils.Constants;

import java.util.ArrayList;

/**
 * Created by lenovo pc on 09/09/2017.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {

    private final static int FADE_DURATION = 300;
    private Context context;
    private ArrayList<MenuResponse> menuResponses = new ArrayList<>();
    private OnInnerViewsClickListener mListner;
    private String menu;

    public MenuAdapter(Context context, ArrayList<MenuResponse> menuResponses, String menu, OnInnerViewsClickListener mListner) {
        this.context = context;
        this.menuResponses = menuResponses;
        this.mListner = mListner;
        this.menu = menu;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_menu_items, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        MenuResponse menuResponse = menuResponses.get(position);
        if (menu.equalsIgnoreCase(Constants.MENU_MAIN)) {
            if (menuResponse.getSubmenu_details().size() > 0) {
                holder.ivMore.setVisibility(View.VISIBLE);
            } else {
                holder.ivMore.setVisibility(View.GONE);
            }
            holder.tvMenu.setText(menuResponse.getMenu_name());


        } else if (menu.equalsIgnoreCase(Constants.MENU_SUB_MENU)) {
            if (menuResponse.getSubsubmenudetails().size() > 0) {
                holder.ivMore.setVisibility(View.VISIBLE);
            } else {
                holder.ivMore.setVisibility(View.GONE);
            }
            holder.tvMenu.setText(menuResponse.getSubmenu());

        } else if (menu.equalsIgnoreCase(Constants.MENU_SUB_OF_SUB_MENU)) {
            holder.ivMore.setVisibility(View.GONE);
            holder.tvMenu.setText(menuResponse.getSubsubmenu());

        }
        setScaleAnimation(holder.itemView);
    }

    private void setScaleAnimation(View view) {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_right);
        animation.setDuration(FADE_DURATION);
        view.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return menuResponses.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvMenu;
        private ImageView ivMore;
        private LinearLayout llRow;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvMenu = (TextView) itemView.findViewById(R.id.tvMenu);
            llRow = (LinearLayout) itemView.findViewById(R.id.llRow);
            ivMore = (ImageView) itemView.findViewById(R.id.ivMore);
            llRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListner.onItemClick(view, getAdapterPosition(), menu);
                }
            });

        }
    }

    public interface OnInnerViewsClickListener {
        void onItemClick(View view, int position, String menu);

    }
}
