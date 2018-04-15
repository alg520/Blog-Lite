package com.shivamsingh.blog_lite.di.modules

import android.arch.persistence.room.Room
import android.content.Context
import com.aasaanjobs.partnerinternal.di.qualifiers.ForApplication
import com.aasaanjobs.partnerinternal.di.scopes.PerApplication
import com.shivamsingh.blog_lite.BuildConfig.DATABASE_NAME
import com.shivamsingh.blog_lite.data.source.local.BlogDatabase
import dagger.Module
import dagger.Provides


@Module
class RoomModule {

    @PerApplication
    @Provides
    fun blogDatabase(@ForApplication context: Context): BlogDatabase {
        return Room.databaseBuilder(context, BlogDatabase::class.java, DATABASE_NAME).build()
    }

    @PerApplication
    @Provides
    fun postDao(blogDatabase: BlogDatabase) = blogDatabase.postDao()

    @PerApplication
    @Provides
    fun userDao(blogDatabase: BlogDatabase) = blogDatabase.userDao()

    @PerApplication
    @Provides
    fun commentDao(blogDatabase: BlogDatabase) = blogDatabase.commentDao()
}