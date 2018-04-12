package com.shivamsingh.blog_lite.data.mapper

import com.shivamsingh.blog_lite.data.BlogDatabase
import com.shivamsingh.blog_lite.data.mapper.base.Mapper
import com.shivamsingh.blog_lite.domain.model.Post
import javax.inject.Inject

class PostMapper @Inject constructor() : Mapper<List<Post>, BlogDatabase>() {

    override fun map(blog: BlogDatabase): List<Post> {
        return blog.posts.map { post ->
            val emailOrEmpty = blog.users.firstOrNull { it.id == post.userId }?.email ?: emptyString
            val comments = blog.comments.filter { it.postId == post.id }
            Post(post.id, post.title, post.body, emailOrEmpty, comments.size)
        }.toList()
    }
}