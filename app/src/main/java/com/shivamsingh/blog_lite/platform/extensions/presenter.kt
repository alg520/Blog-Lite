package com.shivamsingh.blog_lite.platform.extensions

import com.aasaanjobs.partnerinternal.recyclerview.DisplayableItem
import com.shivamsingh.blog_lite.ui.features.posts.module.PostsListModule
import io.reactivex.Observable

fun <T> mapToDisplayableItems(candidates: List<T>): List<DisplayableItem<T>> {
    return Observable
            .fromIterable(candidates)
            .map { DisplayableItem(PostsListModule.POST_ITEM, it) }
            .toList()
            .blockingGet()
}