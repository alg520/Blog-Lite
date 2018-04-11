package com.shivamsingh.blog_lite.ui.mapper

import com.aasaanjobs.partnerinternal.recyclerview.DisplayableItem
import com.aasaanjobs.partnerinternal.recyclerview.DisplayableItem.Companion.toDisplayableItem
import com.shivamsingh.blog_lite.data.mapper.base.Mapper
import com.shivamsingh.blog_lite.domain.model.Post
import com.shivamsingh.blog_lite.ui.features.POST_ITEM
import com.shivamsingh.blog_lite.ui.model.PostEntity
import javax.inject.Inject

class PostDisplayableItemMapper @Inject constructor(private val postToViewEntityMapper: PostMapper)
    : Mapper<DisplayableItem<PostEntity>, Post>() {

    override fun map(post: Post): DisplayableItem<PostEntity> {
        return toDisplayableItem(postToViewEntityMapper.map(post), POST_ITEM)
    }
}