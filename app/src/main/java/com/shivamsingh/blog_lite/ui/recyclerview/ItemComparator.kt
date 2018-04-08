package com.aasaanjobs.partnerinternal.recyclerview

interface ItemComparator<T> {

    fun areItemsTheSame(item1: DisplayableItem<T>, item2: DisplayableItem<T>): Boolean

    fun areContentsTheSame(item1: DisplayableItem<T>, item2: DisplayableItem<T>): Boolean
}