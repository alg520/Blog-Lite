package com.shivamsingh.blog_lite.ui.features.posts.module

import com.aasaanjobs.partnerinternal.di.scopes.PerActivity
import com.shivamsingh.blog_lite.data.source.remote.BlogService
import dagger.Provides
import retrofit2.Retrofit

class PostsDataModule {

    @Provides
    @PerActivity
    fun service(retrofit: Retrofit): BlogService {
        return retrofit.create(BlogService::class.java)
    }
}