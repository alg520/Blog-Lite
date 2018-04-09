package com.shivamsingh.blog_lite.domain.model

import java.io.Serializable

data class Post(val id: Int,
                val title: String,
                val body: String,
                val authorPicUrl: String,
                val commentsCount: Int) : Serializable