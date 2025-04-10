package com.seekho.assignment.repository

import android.util.Log
import com.seekho.assignment.model.AnimeDetails
import com.seekho.assignment.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

interface AnimeDetailsRepository{
    suspend fun fetchAnimeDetails(): Flow<AnimeDetails>
}

class AnimeDetailsRepositoryImpl(private val apiService: ApiService):AnimeDetailsRepository {
    override suspend fun fetchAnimeDetails(): Flow<AnimeDetails> {
        return flow {
            emit(apiService.getAnimeDetails())
        }.catch {
            Log.e("Exception", it.message.toString())
        }
    }
}