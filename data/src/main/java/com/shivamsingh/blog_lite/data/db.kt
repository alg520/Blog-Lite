package com.shivamsingh.blog_lite.data

import com.shivamsingh.blog_lite.data.source.remote.dto.CommentDto
import com.shivamsingh.blog_lite.data.source.remote.dto.PostDto
import com.shivamsingh.blog_lite.data.source.remote.dto.UserDto

data class BlogDatabase(val posts: List<PostDto>,
                        val users: List<UserDto>,
                        val comments: List<CommentDto>)