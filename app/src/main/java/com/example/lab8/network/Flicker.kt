package com.example.lab8.network

import com.example.lab8.PhotoRepository
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "f5da8d270e3bb27792676e099c337ad0"

interface Flicker {
    @GET("/")
    suspend fun fetchContents(): String

    @GET("services/rest?method=flickr.photos.search")
    suspend fun searchPhotos(@Query("text") query: String): FlickerResponse

    suspend fun fetchPhotos(): FlickerResponse

}