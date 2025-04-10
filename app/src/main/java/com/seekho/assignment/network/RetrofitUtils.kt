package com.seekho.assignment.network

import com.seekho.assignment.network.NetworkConstants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitUtils {
    private val httpLoggingInterceptor by lazy {HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)  } ;
    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }


    val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
            GsonConverterFactory.create()
        ).client(okHttpClient).build()
    }
}