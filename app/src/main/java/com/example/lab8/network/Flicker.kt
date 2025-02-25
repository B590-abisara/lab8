package com.example.lab8.network

import com.example.lab8.PhotoRepository
import retrofit2.http.GET

private const val API_KEY = "f5da8d270e3bb27792676e099c337ad0"

interface Flicker {
    @GET("/")
    suspend fun fetchContents(): String

    @GET(
        "services/rest/?method=flickr.interestingness.getList" +
                "&api_key=$API_KEY" +
                "&format=json" +
                "&nojsoncallback=1" +
                "&extras=url_s"
    )
    suspend fun fetchPhotos(): FlickerResponse
}