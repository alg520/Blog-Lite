package com.shivamsingh.blog_lite.ui.features.posts

import com.aasaanjobs.partnerinternal.recyclerview.DisplayableItem
import com.aasaanjobs.partnerinternal.recyclerview.ItemComparator
import com.shivamsingh.blog_lite.ui.model.PostEntity

class PostItemComparator : ItemComparator<PostEntity> {

    override fun areItemsTheSame(item1: DisplayableItem<PostEntity>, item2: DisplayableItem<PostEntity>): Boolean {
        return item1.model.equals(item2.model)
    }

    override fun areContentsTheSame(item1: DisplayableItem<PostEntity>, item2: DisplayableItem<PostEntity>): Boolean {
        return item1.model.equals(item2.model)
    }
}