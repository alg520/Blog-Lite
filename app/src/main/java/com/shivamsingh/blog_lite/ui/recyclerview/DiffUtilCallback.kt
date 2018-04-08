package com.aasaanjobs.partnerinternal.recyclerview

import android.support.v7.util.DiffUtil

class DiffUtilCallback<T>(val oldItems: List<DisplayableItem<T>>,
                          val newItems: List<DisplayableItem<T>>,
                          val itemComparator: ItemComparator<T>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return itemComparator.areItemsTheSame(oldItems.get(oldItemPosition), newItems.get(newItemPosition))
    }

    override fun getOldListSize(): Int {
        return oldItems.size
    }

    override fun getNewListSize(): Int {
        return newItems.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return itemComparator.areContentsTheSame(oldItems.get(oldItemPosition), newItems.get(newItemPosition))
    }
}