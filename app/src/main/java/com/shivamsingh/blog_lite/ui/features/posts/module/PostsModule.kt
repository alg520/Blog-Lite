package com.shivamsingh.blog_lite.ui.features.posts.module

import com.aasaanjobs.partnerinternal.di.scopes.PerActivity
import com.aasaanjobs.partnerinternal.di.scopes.PerFragment
import com.shivamsingh.blog_lite.ui.features.posts.PostsContract
import com.shivamsingh.blog_lite.ui.features.posts.PostsFragment
import com.shivamsingh.blog_lite.ui.features.posts.PostsPresenter
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [(PostsListModule::class)])
abstract class PostsModule {

    @PerFragment
    @ContributesAndroidInjector
    internal abstract fun postsFragment(): PostsFragment

    @Binds
    @PerFragment
    abstract fun postsView(postsFragment: PostsFragment): PostsContract.View

    @Binds
    @PerActivity
    abstract fun postsPresenter(presenter: PostsPresenter): PostsContract.Presenter
}