package com.shivamsingh.blog_lite.ui.features.posts.module

import com.aasaanjobs.partnerinternal.di.scopes.PerActivity
import com.shivamsingh.blog_lite.data.mapper.CommentMapper
import com.shivamsingh.blog_lite.data.mapper.PostMapper
import com.shivamsingh.blog_lite.data.repository.BlogRepositoryImpl
import com.shivamsingh.blog_lite.data.source.local.BlogLocalSourceImpl
import com.shivamsingh.blog_lite.data.source.remote.BlogRemoteSourceImpl
import com.shivamsingh.blog_lite.data.source.remote.BlogService
import com.shivamsingh.blog_lite.domain.repository.BlogRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class PostsDataModule {

    @Provides
    @PerActivity
    fun service(retrofit: Retrofit): BlogService {
        return retrofit.create(BlogService::class.java)
    }

    @Provides
    @PerActivity
    fun blogRepository(localSource: BlogLocalSourceImpl,
                       remoteSource: BlogRemoteSourceImpl,
                       postMapper: PostMapper,
                       commentMapper: CommentMapper): BlogRepository {
        return BlogRepositoryImpl(localSource, remoteSource, postMapper, commentMapper)
    }
}