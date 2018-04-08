package com.shivamsingh.blog_lite.domain.model

data class Post(val id: Int,
                val title: String,
                val body: String,
                val authorPicUrl: String,
                val commentsCount: Int)