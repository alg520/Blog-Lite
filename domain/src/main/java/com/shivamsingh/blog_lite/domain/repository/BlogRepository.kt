package com.shivamsingh.blog_lite.domain.repository

import com.shivamsingh.blog_lite.domain.model.Comment
import com.shivamsingh.blog_lite.domain.model.Post
import io.reactivex.Flowable
import io.reactivex.Single

interface BlogRepository {

    fun posts(): Single<List<Post>>

    fun comments(postId: Int): Single<List<Comment>>
}