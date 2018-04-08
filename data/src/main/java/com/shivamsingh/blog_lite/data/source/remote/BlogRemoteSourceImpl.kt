package com.shivamsingh.blog_lite.data.source.remote

import com.shivamsingh.blog_lite.data.source.BlogRemoteSource
import javax.inject.Inject

class BlogRemoteSourceImpl @Inject constructor(val service: BlogService) : BlogRemoteSource {

    override fun posts() = service.posts()

    override fun users() = service.users()

    override fun comments() = service.comments()
}