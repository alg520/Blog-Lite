package com.shivamsingh.blog_lite.data.source.local

import com.shivamsingh.blog_lite.data.source.dto.CommentDto
import com.shivamsingh.blog_lite.data.source.dto.PostDto
import com.shivamsingh.blog_lite.data.source.dto.UserDto
import com.shivamsingh.blog_lite.domain.model.Post
import io.reactivex.Flowable

interface BlogLocalSource {

    fun posts(): Flowable<List<PostDto>>

    fun mappedPosts(): Flowable<List<Post>>

    fun users(): Flowable<List<UserDto>>

    fun comments(): Flowable<List<CommentDto>>

    fun comments(postId: Int): Flowable<List<CommentDto>>

    fun savePosts(posts: List<PostDto>)

    fun saveUsers(users: List<UserDto>)

    fun saveComments(comments: List<CommentDto>)
}