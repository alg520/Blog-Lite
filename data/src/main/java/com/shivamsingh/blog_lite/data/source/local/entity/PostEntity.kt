package com.shivamsingh.blog_lite.data.source.local.entity

data class PostEntity(var id: Int = 0,
                      var title: String = "",
                      var body: String = "",
                      var email: String? = "",
                      var commentsCount: Int = 0)