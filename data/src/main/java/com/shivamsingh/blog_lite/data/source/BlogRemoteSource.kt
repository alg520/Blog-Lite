package com.shivamsingh.blog_lite.data.source

import com.shivamsingh.blog_lite.data.source.remote.dto.CommentDto
import com.shivamsingh.blog_lite.data.source.remote.dto.PostDto
import com.shivamsingh.blog_lite.data.source.remote.dto.UserDto
import io.reactivex.Single

interface BlogRemoteSource {

    fun posts(): Single<List<PostDto>>

    fun users(): Single<List<UserDto>>

    fun comments(): Single<List<CommentDto>>
}