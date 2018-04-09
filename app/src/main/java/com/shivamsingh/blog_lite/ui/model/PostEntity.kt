package com.shivamsingh.blog_lite.ui.model

import java.io.Serializable

data class PostEntity(val id: Int,
                      val title: String,
                      val body: String,
                      val email: String,
                      val avatar: String,
                      val commentsCount: Int) : Serializable