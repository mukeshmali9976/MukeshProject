package com.mukeshproject.domain.listeners;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * The type Endless recycler on scroll listener.
 */
public abstract class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener {
    /**
     * The constant TAG.
     */
    public static String TAG = EndlessRecyclerOnScrollListener.class.getSimpleName();

	private int previousTotal = 0; // The total number of items in the dataset after the last load
	private boolean loading = false; // True if we are still waiting for the last set of data to load.
	private int visibleThreshold = 3; // The minimum amount of items to have below your current scroll position before loading more.
    /**
     * The First visible item.
     */
    int firstVisibleItem,
    /**
     * The Visible item count.
     */
    visibleItemCount,
    /**
     * The Total item count.
     */
    totalItemCount;

	private int current_page = 1;

	private LinearLayoutManager mLinearLayoutManager;

    /**
     * Instantiates a new Endless recycler on scroll listener.
     *
     * @param linearLayoutManager the linear layout manager
     */
    public EndlessRecyclerOnScrollListener(LinearLayoutManager linearLayoutManager) {
		this.mLinearLayoutManager = linearLayoutManager;
	}

	@Override
	public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
		super.onScrolled(recyclerView, dx, dy);

		if (dy < 0) return;
		// check for scroll down
		visibleItemCount = recyclerView.getChildCount();
		totalItemCount = mLinearLayoutManager.getItemCount();
		firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();

		synchronized (this) {
			if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
				// End has been reached, Do something
				current_page++;
				onLoadMore(current_page);
				loading = true;
			}
		}
	}

    /**
     * Reset current page.
     */
    public void resetCurrent_page(){
		current_page = 1;
	}

    /**
     * Sets loading.
     *
     * @param loading the loading
     */
    public void setLoading(boolean loading) {
		this.loading = loading;
	}

    /**
     * On load more.
     *
     * @param current_page the current page
     */
    public abstract void onLoadMore(int current_page);
}
