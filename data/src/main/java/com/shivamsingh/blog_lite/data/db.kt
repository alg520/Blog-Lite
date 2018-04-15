package com.shivamsingh.blog_lite.data

import com.shivamsingh.blog_lite.data.source.dto.CommentDto
import com.shivamsingh.blog_lite.data.source.dto.PostDto
import com.shivamsingh.blog_lite.data.source.dto.UserDto

data class InMemoryBlogDatabase(val posts: List<PostDto>,
                                val users: List<UserDto>,
                                val comments: List<CommentDto>)