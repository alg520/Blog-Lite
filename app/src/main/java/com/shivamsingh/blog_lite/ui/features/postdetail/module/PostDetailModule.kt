package com.shivamsingh.blog_lite.ui.features.posts.module

import com.aasaanjobs.partnerinternal.di.scopes.PerActivity
import com.aasaanjobs.partnerinternal.di.scopes.PerFragment
import com.shivamsingh.blog_lite.ui.features.postdetail.PostDetailContract
import com.shivamsingh.blog_lite.ui.features.postdetail.PostDetailFragment
import com.shivamsingh.blog_lite.ui.features.postdetail.PostDetailPresenter
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [CommentListModule::class])
abstract class PostDetailModule {

    @PerFragment
    @ContributesAndroidInjector
    internal abstract fun postDetailFragment(): PostDetailFragment

    @Binds
    @PerFragment
    abstract fun postDetailView(postDetailFragment: PostDetailFragment): PostDetailContract.View

    @Binds
    @PerActivity
    abstract fun postDetailPresenter(presenter: PostDetailPresenter): PostDetailContract.Presenter
}