package com.example.retrofit.model.api_interfaces

import com.example.retrofit.model.Image
import retrofit2.Call
import retrofit2.http.GET

interface PlaceholderImageServices {

    @GET("/photos")
    fun getImages():Call<List<Image>>
}