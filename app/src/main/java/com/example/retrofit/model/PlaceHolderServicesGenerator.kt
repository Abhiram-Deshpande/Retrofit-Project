package com.example.retrofit.model

import okhttp3.OkHttpClient

import retrofit2.Retrofit

import retrofit2.converter.gson.GsonConverterFactory




class PlaceHolderServicesGenerator {

    companion object{
        val BASE_URL ="https://jsonplaceholder.typicode.com/"
        private val httpClient = OkHttpClient.Builder()
        val builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
        //Creating a retrofit client
        private val retrofit = builder.build()

        fun <S>createService(serviceClass: Class<S>): S {
            return retrofit.create(serviceClass)
        }
    }


}