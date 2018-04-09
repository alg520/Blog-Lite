package com.shivamsingh.blog_lite.di.modules

import com.aasaanjobs.partnerinternal.di.scopes.PerActivity
import com.shivamsingh.blog_lite.ui.features.postdetail.PostDetailActivity
import com.shivamsingh.blog_lite.ui.features.posts.PostsActivity
import com.shivamsingh.blog_lite.ui.features.posts.module.PostDetailModule
import com.shivamsingh.blog_lite.ui.features.posts.module.PostsDataModule
import com.shivamsingh.blog_lite.ui.features.posts.module.PostsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [(PostsModule::class), (PostsDataModule::class)])
    abstract fun postsActivity(): PostsActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [(PostDetailModule::class), (PostsDataModule::class)])
    abstract fun postDetailActivity(): PostDetailActivity
}