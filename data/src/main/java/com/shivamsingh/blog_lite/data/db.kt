package com.shivamsingh.blog_lite.data

import com.shivamsingh.blog_lite.data.source.remote.dto.CommentDto
import com.shivamsingh.blog_lite.data.source.remote.dto.PostDto
import com.shivamsingh.blog_lite.data.source.remote.dto.UserDto

class BlogDatabase() {
    var posts: List<PostDto>? = null
    var users: List<UserDto>? = null
    var comments: List<CommentDto>? = null
}