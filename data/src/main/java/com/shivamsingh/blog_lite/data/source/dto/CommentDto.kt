package com.shivamsingh.blog_lite.data.source.dto

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "comment")
data class CommentDto(
        @PrimaryKey var id: Int = 0,
        var postId: Int = 0,
        var name: String = "",
        var email: String = "",
        var body: String = ""
)