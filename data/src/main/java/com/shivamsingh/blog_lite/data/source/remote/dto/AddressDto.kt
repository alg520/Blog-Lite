package com.shivamsingh.blog_lite.data.source.remote.dto

data class AddressDto(
        val street: String,
        val suite: String,
        val city: String,
        val zipcode: String,
        val geo: GeoDto
)