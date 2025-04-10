package com.seekho.assignment.repository

import android.util.Log
import com.seekho.assignment.model.AnimeLists
import com.seekho.assignment.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

interface AnimeListRepository{
    suspend fun fetchTopAnime(): Flow<AnimeLists>
}

class AnimeListRepositoryImpl(private val apiService: ApiService):AnimeListRepository {
    override suspend fun fetchTopAnime(): Flow<AnimeLists> {
        return flow {
            emit(apiService.getAnimes())
        }.catch {
            Log.e("Exception", it.message.toString())
        }
    }
}