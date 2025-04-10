package com.seekho.assignment.network

object RetrofitService {
    val apiService: ApiService by lazy {
        RetrofitUtils.retrofit.create(ApiService::class.java)
    }
}