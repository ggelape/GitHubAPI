package com.example.gelape.concretedesafio;

import android.widget.AbsListView;

public abstract class EndlessScrollListener implements AbsListView.OnScrollListener
{
    private int currPage = 0;
    private int visibleThreshold = 0;
    private int previousTotalItemCount = 30;
    private boolean loading = true;
    private int startingPageIndex = 0;
    public final int SCROLL_DIRECTION_UP = 0;
    public final int SCROLL_DIRECTION_DOWN = 1;
    private int scrollDirection = SCROLL_DIRECTION_DOWN;

    public EndlessScrollListener()
    {

    }
    public EndlessScrollListener(int visibleThreshold)
    {
        this.visibleThreshold = visibleThreshold;
    }

    public EndlessScrollListener(int visibleThreshold, int startPage)
    {
        this.visibleThreshold = visibleThreshold;
        this.startingPageIndex = startPage;
        this.currPage = startPage;
    }
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState)
    {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
    {
        if (totalItemCount < previousTotalItemCount)
        {
            this.currPage = this.startingPageIndex;
            this.previousTotalItemCount = totalItemCount;
            if (totalItemCount == 0)
            {
                this.loading = true;
            }
        }

        if (loading && (totalItemCount > previousTotalItemCount))
        {
            loading = false;
            previousTotalItemCount = totalItemCount;
            currPage++;
        }


        if (!loading && (firstVisibleItem + visibleItemCount + visibleThreshold) >= totalItemCount )
        {
            onLoadMore(currPage++, totalItemCount);
        }
    }
    public abstract void onLoadMore(int page, int totalItemsCount);
}
