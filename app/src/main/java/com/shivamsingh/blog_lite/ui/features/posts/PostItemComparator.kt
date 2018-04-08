package com.shivamsingh.blog_lite.ui.features.posts

import com.aasaanjobs.partnerinternal.recyclerview.DisplayableItem
import com.aasaanjobs.partnerinternal.recyclerview.ItemComparator
import com.shivamsingh.blog_lite.domain.model.Post

class PostItemComparator : ItemComparator<Post> {

    override fun areItemsTheSame(item1: DisplayableItem<Post>, item2: DisplayableItem<Post>): Boolean {
        return item1.model.equals(item2.model)
    }

    override fun areContentsTheSame(item1: DisplayableItem<Post>, item2: DisplayableItem<Post>): Boolean {
        return item1.model.equals(item2.model)
    }
}