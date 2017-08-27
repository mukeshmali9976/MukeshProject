package com.mukeshproject.domain.listeners;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Vijay Desai on 27-04-2016
 */
public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
    /**
     * The interface On item click listener.
     */
    public interface OnItemClickListener {
        /**
         * On item click.
         *
         * @param view     the view
         * @param position the position
         */
        void onItemClick(View view, int position);

        /**
         * On item long click.
         *
         * @param view     the view
         * @param position the position
         */
        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener mListener;
    private GestureDetectorCompat mGestureDetector;
    private boolean isDragging = false;
    //private RecyclerView recyclerView;
    private static final int SECTION_TYPE = 0;

    /**
     * Instantiates a new Recycler item click listener.
     *
     * @param context      the context
     * @param recyclerView the recycler view
     * @param listener     the listener
     */
    public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener) {
        mListener = listener;

        // recyclerView = mRecyclerView;

        mGestureDetector = new GestureDetectorCompat(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onDown(MotionEvent e) {
                return super.onDown(e);
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());

                //int viewPosition = recyclerView.getChildAdapterPosition(childView);

                isDragging = true;
                //dragStart(e.getX(), e.getY());

                if (childView != null && mListener != null) {
                    mListener.onItemLongClick(childView, recyclerView.getChildAdapterPosition(childView));
                }

            }
        });
    }


    @Override
    public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
        View childView = view.findChildViewUnder(e.getX(), e.getY());

        if (childView == null)
            return false;

        //We need to find the relative position of the handle to the parent view
        //Then we can work out if the touch is within the handle
        /*int[] parentItemPos = new int[2];
        childView.getLocationInWindow(parentItemPos);

        int[] handlePos = new int[2];
        childView.getLocationInWindow(handlePos);

        int xRel = handlePos[0] - parentItemPos[0];
        int yRel = handlePos[1] - parentItemPos[1];

        Rect touchBounds = new Rect(childView.getLeft() + xRel, childView.getTop() + yRel,
                childView.getLeft() + xRel,
                childView.getTop() + yRel
        );

        if (touchBounds.contains((int)e.getX(), (int)e.getY()))
        {

            isDragging = true;
            setIsDragging(isDragging,childView,view);

            return false;
        }*/


        if (view != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
            mListener.onItemClick(view, view.getChildAdapterPosition(childView));
        }


        return false;
    }


    @Override
    public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    private void setIsDragging(final boolean dragging, View childView, RecyclerView parentView) {
        if (dragging != isDragging) {
            isDragging = dragging;
            if (mListener != null) {
                if (isDragging) {
                    mListener.onItemLongClick(childView, parentView.getChildAdapterPosition(childView));
                } else {
                    mListener.onItemClick(childView, parentView.getChildAdapterPosition(childView));
                }
            }
        }
    }
}
