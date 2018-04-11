package com.shivamsingh.blog_lite.data.source.remote.dto

data class CommentDto(
        val id: Int,
        val postId: Int,
        val name: String,
        val email: String,
        val body: String
)