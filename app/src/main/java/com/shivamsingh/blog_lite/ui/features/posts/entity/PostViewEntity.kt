package com.shivamsingh.blog_lite.ui.features.posts.entity

data class PostViewEntity(val id: Int,
                          val title: String,
                          val body: String,
                          val authorPicUrl: String,
                          val comments: Int)