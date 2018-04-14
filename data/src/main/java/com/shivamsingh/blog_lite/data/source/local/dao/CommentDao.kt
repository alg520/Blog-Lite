package com.shivamsingh.blog_lite.data.source.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.shivamsingh.blog_lite.data.source.dto.CommentDto
import io.reactivex.Flowable

@Dao
interface CommentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComments(comments: List<CommentDto>)

    @Query("SELECT * FROM comment")
    fun comments(): Flowable<List<CommentDto>>

    @Query("SELECT * FROM comment WHERE :postId")
    fun comments(postId: Int): Flowable<List<CommentDto>>
}