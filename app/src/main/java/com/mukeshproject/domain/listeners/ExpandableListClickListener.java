package com.mukeshproject.domain.listeners;

import android.view.View;

/**
 * Created by Rohit on 31/3/17.
 */
public interface ExpandableListClickListener {

    /**
     * On drawer click.
     *
     * @param v             the v
     * @param mainPosition  the row position
     * @param childPosition the item position
     */
    public void onSubRowClick(View v, int mainPosition, int childPosition);


}
