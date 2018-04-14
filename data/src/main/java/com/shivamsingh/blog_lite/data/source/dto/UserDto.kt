package com.shivamsingh.blog_lite.data.source.dto

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "user")
data class UserDto(
        @PrimaryKey var id: Int= 0,
        var name: String = "",
        var username: String = "",
        var email: String = "",
        @Embedded var address: AddressDto = AddressDto(),
        var phone: String = "",
        var website: String = "",
        @Embedded var company:  CompanyDto = CompanyDto()
)