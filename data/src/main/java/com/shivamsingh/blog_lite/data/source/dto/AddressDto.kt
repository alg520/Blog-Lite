package com.shivamsingh.blog_lite.data.source.dto

data class AddressDto(
        var street: String = "",
        var suite: String = "",
        var city: String = "",
        var zipcode: String = ""
)