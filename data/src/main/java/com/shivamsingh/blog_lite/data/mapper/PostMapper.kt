package com.shivamsingh.blog_lite.data.mapper

import com.shivamsingh.blog_lite.data.InMemoryBlogDatabase
import com.shivamsingh.blog_lite.data.mapper.base.Mapper
import com.shivamsingh.blog_lite.domain.model.Post
import javax.inject.Inject

class PostMapper @Inject constructor() : Mapper<List<Post>, InMemoryBlogDatabase>() {

    override fun map(inMemoryBlog: InMemoryBlogDatabase): List<Post> {
        return inMemoryBlog.posts.map { post ->
            val emailOrEmpty = inMemoryBlog.users.firstOrNull { it.id == post.userId }?.email ?: emptyString
            val comments = inMemoryBlog.comments.filter { it.postId == post.id }
            Post(post.id, post.title, post.body, emailOrEmpty, comments.size)
        }.toList()
    }
}