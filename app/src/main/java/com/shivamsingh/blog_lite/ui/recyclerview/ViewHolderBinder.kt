package com.aasaanjobs.partnerinternal.recyclerview

import android.support.v7.widget.RecyclerView.ViewHolder

interface ViewHolderBinder<T> {

    fun bind(viewHolder: ViewHolder, item: DisplayableItem<T>)
}