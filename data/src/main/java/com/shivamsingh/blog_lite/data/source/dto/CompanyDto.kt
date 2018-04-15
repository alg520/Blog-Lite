package com.shivamsingh.blog_lite.data.source.dto

import android.arch.persistence.room.ColumnInfo

data class CompanyDto(
        @ColumnInfo(name = "company_name")var name: String = "",
        var catchPhrase: String = "",
        var bs: String = ""
)