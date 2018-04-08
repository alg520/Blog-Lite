package com.shivamsingh.blog_lite.data.source.remote

import com.shivamsingh.blog_lite.data.source.remote.dto.CommentDto
import com.shivamsingh.blog_lite.data.source.remote.dto.PostDto
import com.shivamsingh.blog_lite.data.source.remote.dto.UserDto
import io.reactivex.Single
import retrofit2.http.GET

interface BlogService {

    @GET("posts")
    fun posts(): Single<List<PostDto>>

    @GET("users")
    fun users(): Single<List<UserDto>>

    @GET("comments")
    fun comments(): Single<List<CommentDto>>
}