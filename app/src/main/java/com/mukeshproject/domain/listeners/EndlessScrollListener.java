package com.mukeshproject.domain.listeners;


import com.mukeshproject.ui.views.EndlessScrollView;

/**
 * The interface Endless scroll listener.
 *
 * @author Vijay Desai on 22/2/17.
 */
public interface EndlessScrollListener {
    /**
     * On scroll changed.
     *
     * @param scrollView the scroll view
     * @param x          the x
     * @param y          the y
     * @param oldx       the oldx
     * @param oldy       the oldy
     */
    void onScrollChanged(EndlessScrollView scrollView, int x, int y, int oldx, int oldy);
}
