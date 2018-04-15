package com.shivamsingh.blog_lite.data.source.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.shivamsingh.blog_lite.data.source.dto.PostDto
import com.shivamsingh.blog_lite.data.source.local.entity.PostEntity
import com.shivamsingh.blog_lite.domain.model.Post
import io.reactivex.Flowable

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPosts(posts: List<PostDto>)

    @Query("SELECT * FROM post")
    fun posts(): Flowable<List<PostDto>>

    @Query("""
        SELECT post.id, post.userId, post.title, post.body, user.email, count(comment.id) as commentsCount
        FROM post
        LEFT JOIN user ON post.userId = user.id
        LEFT JOIN comment ON comment.postId = post.id
        GROUP BY post.id, post.userId, post.title, post.body, user.email
    """)
    fun mappedPosts(): Flowable<List<PostEntity>>
}