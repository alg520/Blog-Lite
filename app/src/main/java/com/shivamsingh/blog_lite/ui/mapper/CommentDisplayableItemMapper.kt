package com.shivamsingh.blog_lite.ui.mapper

import com.aasaanjobs.partnerinternal.recyclerview.DisplayableItem
import com.aasaanjobs.partnerinternal.recyclerview.DisplayableItem.Companion.toDisplayableItem
import com.shivamsingh.blog_lite.data.mapper.base.Mapper
import com.shivamsingh.blog_lite.domain.model.Comment
import com.shivamsingh.blog_lite.ui.features.COMMENT_ITEM
import com.shivamsingh.blog_lite.ui.model.CommentEntity
import javax.inject.Inject

class CommentDisplayableItemMapper @Inject constructor(private val commentToViewEntityMapper: CommentMapper)
    : Mapper<DisplayableItem<CommentEntity>, Comment>() {

    override fun map(comment: Comment): DisplayableItem<CommentEntity> {
        return toDisplayableItem(commentToViewEntityMapper.map(comment), COMMENT_ITEM)
    }
}