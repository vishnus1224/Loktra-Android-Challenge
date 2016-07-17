package com.vishnus1224.commitsearch.listener;

import android.widget.AbsListView;

/**
 * Listener for knowing when list view has reached its bottom to load more results.
 * Created by Vishnu on 7/17/2016.
 */
public class ListViewScrollToBottomListener implements AbsListView.OnScrollListener {

    private BottomHitListener bottomHitListener;

    /**
     * The position of the visible item. Used to prevent multiple calls to onBottomHit.
     */
    private int lastItemPosition;

    /**
     * The offset from the last item at which the onBottomHit method will fire.
     * The onBottomHit method will be called when the list view has scrolled past the third last item.
     */
    private int lastItemOffset = 2;

    public ListViewScrollToBottomListener(BottomHitListener bottomHitListener){

        this.bottomHitListener = bottomHitListener;

    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        int currentPosition = firstVisibleItem + visibleItemCount;

        //calculate the offset position from the specified offset.
        int offsetPosition = totalItemCount - lastItemOffset;

        if(currentPosition == offsetPosition && totalItemCount > 0){

            if(lastItemPosition != currentPosition) {

                lastItemPosition = currentPosition;

                if (bottomHitListener != null) {

                    bottomHitListener.onBottomHit();

                }

            }

        }
    }

    /**
     * Notifies the calling the class that the list view has reached its bottom.
     */
    public interface BottomHitListener {

        /**
         * ListView has hit its bottom.
         */
        void onBottomHit();

    }
}
