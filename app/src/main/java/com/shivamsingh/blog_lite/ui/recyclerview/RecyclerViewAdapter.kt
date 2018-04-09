package com.aasaanjobs.partnerinternal.recyclerview

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView.Adapter
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.ViewGroup
import io.reactivex.Single

class RecyclerViewAdapter<T>(val itemComparator: ItemComparator<T>,
                             val factoryMap: Map<Int, ViewHolderFactory>,
                             val binderMap: Map<Int, ViewHolderBinder<T>>) : Adapter<ViewHolder>() {

    var items: MutableList<DisplayableItem<T>> = ArrayList()
    var onViewHolderCreated: ((viewHolder: ViewHolder) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val viewHolder = factoryMap.get(viewType)?.createViewHolder(parent!!)!!
        onViewHolderCreated?.let { it(viewHolder) }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return items.get(position).type
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val item = items.get(position)
        holder?.let { binderMap.get(item.type)?.bind(it, item) }
    }

    fun update(items: MutableList<DisplayableItem<T>>) {
        if (this.items.isEmpty()) {
            updateAllItems(items)
        } else {
            updateDiffItemsOnly(items)
        }
    }

    fun onViewHolderCreated(onViewHolderCreated: (viewHolder: ViewHolder) -> Unit) {
        this.onViewHolderCreated = onViewHolderCreated
    }

    private fun updateAllItems(items: MutableList<DisplayableItem<T>>) {
        Single.just(items)
                .doOnSuccess({ updateItemsInModel(it) })
                .subscribe { _ -> notifyDataSetChanged() }
    }

    private fun updateDiffItemsOnly(items: MutableList<DisplayableItem<T>>) {
        Single.fromCallable { calculateDiff(items) }
                .doOnSuccess { _ -> updateItemsInModel(items) }
                .subscribe(this::updateAdapterWithDiffResult)
    }

    private fun updateItemsInModel(items: MutableList<DisplayableItem<T>>) {
        this.items.clear()
        this.items.addAll(items)
    }

    private fun updateAdapterWithDiffResult(result: DiffUtil.DiffResult) {
        result.dispatchUpdatesTo(this)
    }

    private fun calculateDiff(newItems: List<DisplayableItem<T>>): DiffUtil.DiffResult {
        return DiffUtil.calculateDiff(DiffUtilCallback(items, newItems, itemComparator))
    }
}