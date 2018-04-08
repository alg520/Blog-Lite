package com.shivamsingh.blog_lite.data.mapper

import com.shivamsingh.blog_lite.data.BlogDatabase
import com.shivamsingh.blog_lite.data.mapper.base.Mapper
import com.shivamsingh.blog_lite.data.source.remote.dto.PostDto
import com.shivamsingh.blog_lite.domain.model.Post
import java.util.*
import javax.inject.Inject
import javax.inject.Named

class PostMapper @Inject constructor(@Named(AVATAR_URL_WITH_EMAIL_PLACEHOLDER) val avatarUrlWithEmailPlaceHolder: String) : Mapper<List<Post>, BlogDatabase>() {
    companion object {
        const val AVATAR_URL_WITH_EMAIL_PLACEHOLDER = "AVATAR_URL_WITH_EMAIL_PLACEHOLDER"
    }

    override fun map(blog: BlogDatabase): List<Post> {
        return blog.posts?.map { post ->
            val avatarUrl = avatarUrl(blog, post)
            val comments = blog.comments?.filter { it.postId == post.id }
            Post(post.id, post.title, post.body, avatarUrl, comments?.size
                    ?: invalidInt)
        }?.toList() ?: Collections.emptyList()
    }

    private fun avatarUrl(blog: BlogDatabase, post: PostDto): String {
        val emailOrEmpty = blog.users?.firstOrNull { it.id == post.userId }?.email ?: emptyString
        return avatarUrlWithEmailPlaceHolder.format(emailOrEmpty)
    }
}