package com.shivamsingh.blog_lite.domain.model

data class Comment(val id: Int,
                   val name: String,
                   val email: String,
                   val body: String) {
}