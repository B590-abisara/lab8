package com.example.lab8

import android.util.Log
import com.example.lab8.network.Flicker
import com.example.lab8.network.GalleryItem
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create

class PhotoRepository {

    private val flicker: Flicker

    init {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(PhotoInterceptor())
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.flickr.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()

        flicker = retrofit.create<Flicker>()
    }

    suspend fun fetchContents() = flicker.fetchContents()
    suspend fun fetchPhotos(): List<GalleryItem> =
        flicker.fetchPhotos().photos.galleryItems

    suspend fun searchPhotos(query: String): List<GalleryItem> {
        val response = flicker.searchPhotos(query)
        Log.d("API_RESPONSE", response.toString())  // ✅ Log the full response
        return response.photos?.galleryItems ?: emptyList()  // ✅ Prevents crashing if photos is null
    }
}
