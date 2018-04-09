package com.shivamsingh.blog_lite.domain.model

data class Post(val id: Int,
                val title: String,
                val body: String,
                val email: String,
                val commentsCount: Int)