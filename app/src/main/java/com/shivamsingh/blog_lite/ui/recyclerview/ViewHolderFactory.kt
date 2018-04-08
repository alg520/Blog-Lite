package com.aasaanjobs.partnerinternal.recyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.ViewGroup

abstract class ViewHolderFactory(val context: Context) {

    abstract fun createViewHolder(parent: ViewGroup): ViewHolder
}