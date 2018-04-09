package com.shivamsingh.blog_lite.platform.extensions

import com.aasaanjobs.partnerinternal.recyclerview.DisplayableItem
import io.reactivex.Observable

fun <T> mapToDisplayableItems(type: Int, items: List<T>): List<DisplayableItem<T>> {
    return Observable
            .fromIterable(items)
            .map { DisplayableItem(type, it) }
            .toList()
            .blockingGet()
}