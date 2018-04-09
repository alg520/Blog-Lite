package com.shivamsingh.blog_lite.ui.mapper

import com.shivamsingh.blog_lite.data.mapper.base.Mapper
import com.shivamsingh.blog_lite.di.modules.NetworkModule.Companion.AVATAR_URL_WITH_EMAIL_PLACEHOLDER
import com.shivamsingh.blog_lite.domain.model.Post
import com.shivamsingh.blog_lite.ui.model.PostEntity
import javax.inject.Inject
import javax.inject.Named

class PostMapper @Inject constructor(@Named(AVATAR_URL_WITH_EMAIL_PLACEHOLDER) val avatarUrlWithEmailPlaceHolder: String)
    : Mapper<PostEntity, Post>() {

    override fun map(post: Post): PostEntity {
        return PostEntity(post.id, post.title, post.body, post.email, avatarUrlWithEmailPlaceHolder.format(post.email), post.commentsCount)
    }
}