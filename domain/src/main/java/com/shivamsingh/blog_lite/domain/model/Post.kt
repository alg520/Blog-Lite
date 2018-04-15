package com.shivamsingh.blog_lite.domain.model

data class Post(var id: Int = 0,
                var title: String = "",
                var body: String = "",
                var email: String? = "",
                var commentsCount: Int = 0)