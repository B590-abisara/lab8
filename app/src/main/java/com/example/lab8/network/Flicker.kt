package com.example.lab8.network

import retrofit2.http.GET


interface Flicker {
    @GET("/")
    suspend fun fetchContents(): String
}
