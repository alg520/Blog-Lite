package com.shivamsingh.blog_lite.data.source.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.shivamsingh.blog_lite.data.source.dto.UserDto
import io.reactivex.Flowable

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users: List<UserDto>)

    @Query("SELECT * FROM user")
    fun users(): Flowable<List<UserDto>>
}