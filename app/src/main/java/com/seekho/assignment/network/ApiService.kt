package com.seekho.assignment.network

import com.seekho.assignment.model.AnimeDetails
import com.seekho.assignment.model.AnimeLists
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
     @GET("top/anime")
     suspend fun getAnimes(): AnimeLists

     @GET("anime/{id}")
     suspend fun getAnimeDetails(@Path("id") animeID: Int?): AnimeDetails
}