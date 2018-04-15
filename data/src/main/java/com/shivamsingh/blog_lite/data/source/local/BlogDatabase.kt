package com.shivamsingh.blog_lite.data.source.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.shivamsingh.blog_lite.BuildConfig
import com.shivamsingh.blog_lite.data.source.dto.CommentDto
import com.shivamsingh.blog_lite.data.source.dto.PostDto
import com.shivamsingh.blog_lite.data.source.dto.UserDto
import com.shivamsingh.blog_lite.data.source.local.dao.CommentDao
import com.shivamsingh.blog_lite.data.source.local.dao.PostDao
import com.shivamsingh.blog_lite.data.source.local.dao.UserDao

@Database(version = BuildConfig.DATABASE_VERSION, entities = [PostDto::class, UserDto::class, CommentDto::class])
abstract class BlogDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao

    abstract fun userDao(): UserDao

    abstract fun commentDao(): CommentDao
}