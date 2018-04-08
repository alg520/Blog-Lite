package com.shivamsingh.blog_lite.data.mapper

import com.shivamsingh.blog_lite.data.BlogDatabase
import com.shivamsingh.blog_lite.data.mapper.base.Mapper
import com.shivamsingh.blog_lite.data.source.remote.dto.PostDto
import com.shivamsingh.blog_lite.domain.model.Post
import java.util.*
import javax.inject.Inject

class PostMapper @Inject constructor() : Mapper<List<Post>, BlogDatabase>() {

    override fun map(blog: BlogDatabase): List<Post> {
        return blog.posts?.map { post ->
            val email = email(blog, post)
            val comments = blog.comments?.filter { it.postId == post.id }
            Post(post.id, post.title, post.body, email ?: emptyString, comments?.size ?: invalidInt)
        }?.toList() ?: Collections.emptyList()
    }

    private fun email(blog: BlogDatabase, post: PostDto) =
            blog.users?.firstOrNull { it.id == post.userId }?.email
}