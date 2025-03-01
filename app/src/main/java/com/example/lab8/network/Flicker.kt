package com.example.lab8.network

import com.example.lab8.PhotoRepository
import retrofit2.http.GET
import retrofit2.http.Query

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
    suspend fun fetchPhotos(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int,  // ✅ Add pagination support
        @Query("per_page") perPage: Int = 100  // ✅ Define how many images per page
    ): FlickerResponse
}