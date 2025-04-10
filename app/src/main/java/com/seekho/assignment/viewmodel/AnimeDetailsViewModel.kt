package com.seekho.assignment.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seekho.assignment.model.AnimeDetails
import com.seekho.assignment.network.RetrofitService
import com.seekho.assignment.repository.AnimeDetailsRepository
import com.seekho.assignment.repository.AnimeDetailsRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AnimeDetailsViewModel:ViewModel() {
    private val _animeDetails = MutableStateFlow(AnimeDetails())
    private val repository:AnimeDetailsRepository = AnimeDetailsRepositoryImpl(RetrofitService.apiService)
    val animeDetails:StateFlow<AnimeDetails>
        get() = _animeDetails

    fun fetchAnimeDetails(animeID: Int?) {
        viewModelScope.launch{
            repository.fetchAnimeDetails(animeID).collect{
                _animeDetails.emit(it)
            }
        }
    }
}