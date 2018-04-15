package com.shivamsingh.blog_lite.data.mapper

import com.shivamsingh.blog_lite.data.mapper.base.Mapper
import com.shivamsingh.blog_lite.data.source.local.entity.PostEntity
import com.shivamsingh.blog_lite.domain.model.Post
import javax.inject.Inject

class PostDatabaseEntityMapper @Inject constructor() : Mapper<Post, PostEntity>() {

    override fun map(postEntity: PostEntity): Post {
        return Post(postEntity.id, postEntity.title, postEntity.body, postEntity.email
                ?: "", postEntity.commentsCount)
    }
}