package com.seekho.assignment.network

import com.seekho.assignment.model.AnimeLists
import retrofit2.http.GET

interface ApiService {
     @GET("top/anime")
     suspend fun getAnimes(): AnimeLists
}