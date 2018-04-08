package com.shivamsingh.blog_lite.data.source.remote.dto

data class PostDto(
        val userId: Int,
        val id: Int,
        val title: String,
        val body: String)