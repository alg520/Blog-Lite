package com.aasaanjobs.partnerinternal.recyclerview

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

abstract class RecyclerViewScrollManager : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if (isLastItemDisplaying(recyclerView)) {
            endOfList()
        }
    }

    private fun isLastItemDisplaying(recyclerView: RecyclerView, threshold: Int = 1): Boolean {
        if (recyclerView.adapter.itemCount != 0) {
            var lastVisibleItemPosition = RecyclerView.NO_POSITION
            if (recyclerView.layoutManager is LinearLayoutManager) {
                lastVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
            } else if (recyclerView.layoutManager is GridLayoutManager) {
                lastVisibleItemPosition = (recyclerView.layoutManager as GridLayoutManager).findLastCompletelyVisibleItemPosition()
            }

            if (lastVisibleItemPosition != RecyclerView.NO_POSITION && lastVisibleItemPosition == recyclerView.adapter.itemCount - threshold)
                return true
        }
        return false
    }

    open fun endOfList() {}
}
