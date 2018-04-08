package com.shivamsingh.blog_lite.domain.repository

import com.shivamsingh.blog_lite.domain.model.Comment
import com.shivamsingh.blog_lite.domain.model.Post
import io.reactivex.Flowable

interface BlogRepository {

    fun posts(): Flowable<List<Post>>

    fun comments(postId: Int): Flowable<List<Comment>>
}