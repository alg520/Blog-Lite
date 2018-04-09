package com.shivamsingh.blog_lite.ui.features.postdetail

import com.aasaanjobs.partnerinternal.recyclerview.DisplayableItem
import com.aasaanjobs.partnerinternal.recyclerview.ItemComparator
import com.shivamsingh.blog_lite.ui.model.CommentEntity

class CommentItemComparator : ItemComparator<CommentEntity> {

    override fun areItemsTheSame(item1: DisplayableItem<CommentEntity>, item2: DisplayableItem<CommentEntity>): Boolean {
        return item1.model.equals(item2.model)
    }

    override fun areContentsTheSame(item1: DisplayableItem<CommentEntity>, item2: DisplayableItem<CommentEntity>): Boolean {
        return item1.model.equals(item2.model)
    }
}