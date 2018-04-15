package com.shivamsingh.blog_lite.data.source.dto

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "post")
data class PostDto(
        @PrimaryKey var id: Int,
        var userId: Int,
        var title: String,
        var body: String)