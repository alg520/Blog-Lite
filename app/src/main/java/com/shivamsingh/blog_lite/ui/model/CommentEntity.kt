package com.shivamsingh.blog_lite.ui.model

data class CommentEntity(val id: Int,
                         val postId: Int,
                         val name: String,
                         val email: String,
                         val avatar: String,
                         val body: String)