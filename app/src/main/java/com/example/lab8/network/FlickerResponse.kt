package com.example.lab8.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FlickerResponse(
    val photos: PhotoResponse
)