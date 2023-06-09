package com.example.retrofit.model.api_interfaces

import com.example.retrofit.model.Contributor
import retrofit2.Call
import retrofit2.http.GET

interface GitHubClient {

    @GET("/posts")
    fun contributors():Call<List<Contributor>>

}