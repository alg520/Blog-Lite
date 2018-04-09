package com.shivamsingh.blog_lite.ui.features.postdetail

import com.aasaanjobs.partnerinternal.recyclerview.DisplayableItem
import com.aasaanjobs.partnerinternal.recyclerview.ItemComparator
import com.shivamsingh.blog_lite.domain.model.Comment

class CommentItemComparator : ItemComparator<Comment> {

    override fun areItemsTheSame(item1: DisplayableItem<Comment>, item2: DisplayableItem<Comment>): Boolean {
        return item1.model.equals(item2.model)
    }

    override fun areContentsTheSame(item1: DisplayableItem<Comment>, item2: DisplayableItem<Comment>): Boolean {
        return item1.model.equals(item2.model)
    }
}