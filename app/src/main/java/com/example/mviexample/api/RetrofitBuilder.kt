package com.example.mviexample.api

import com.example.mviexample.util.LiveDataCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * object keyword represents a singleton - a class that only has got one instance
 */

object RetrofitBuilder {

    const val BASE_URL = "https://open-api.xyz/"

    val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
    }


    val apiService: ApiService by lazy {
        retrofitBuilder
            .build()
            .create(ApiService::class.java)
    }

}