package com.shivamsingh.blog_lite.ui.mapper

import com.shivamsingh.blog_lite.data.mapper.base.Mapper
import com.shivamsingh.blog_lite.di.modules.NetworkModule.Companion.AVATAR_URL_WITH_EMAIL_PLACEHOLDER
import com.shivamsingh.blog_lite.domain.model.Comment
import com.shivamsingh.blog_lite.ui.model.CommentEntity
import javax.inject.Inject
import javax.inject.Named

class CommentMapper @Inject constructor(@Named(AVATAR_URL_WITH_EMAIL_PLACEHOLDER) val avatarUrlWithEmailPlaceHolder: String)
    : Mapper<CommentEntity, Comment>() {

    override fun map(comment: Comment): CommentEntity {
        return CommentEntity(comment.id, comment.postId, comment.name, comment.email, avatarUrlWithEmailPlaceHolder.format(comment.email), comment.body)
    }
}