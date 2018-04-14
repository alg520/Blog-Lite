package com.shivamsingh.blog_lite.data.source

import com.shivamsingh.blog_lite.data.source.dto.CommentDto
import com.shivamsingh.blog_lite.data.source.dto.PostDto
import com.shivamsingh.blog_lite.data.source.dto.UserDto
import io.reactivex.Single

interface BlogRemoteSource {

    fun posts(): Single<List<PostDto>>

    fun users(): Single<List<UserDto>>

    fun comments(): Single<List<CommentDto>>
}