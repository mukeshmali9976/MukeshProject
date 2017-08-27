package com.mukeshproject.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.mukeshproject.domain.listeners.EndlessScrollListener;


public class EndlessScrollView extends ScrollView {
    private EndlessScrollListener endlessScrollListener = null;

    /**
     * Instantiates a new Endless scroll view.
     *
     * @param context the context
     */
    public EndlessScrollView(Context context) {
        super(context);
    }

    /**
     * Instantiates a new Endless scroll view.
     *
     * @param context  the context
     * @param attrs    the attrs
     * @param defStyle the def style
     */
    public EndlessScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * Instantiates a new Endless scroll view.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public EndlessScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Sets scroll view listener.
     *
     * @param endlessScrollListener the endless scroll listener
     */
    public void setScrollViewListener(EndlessScrollListener endlessScrollListener) {
        this.endlessScrollListener = endlessScrollListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (endlessScrollListener != null) {
            endlessScrollListener.onScrollChanged(this, l, t, oldl, oldt);
        }
    }
}
