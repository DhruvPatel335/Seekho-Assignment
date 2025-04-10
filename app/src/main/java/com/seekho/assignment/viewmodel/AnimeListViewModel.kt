package com.seekho.assignment.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seekho.assignment.model.AnimeLists
import com.seekho.assignment.network.RetrofitService
import com.seekho.assignment.repository.AnimeListRepository
import com.seekho.assignment.repository.AnimeListRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AnimeListViewModel:ViewModel() {
    private val _animeData = MutableStateFlow(AnimeLists())
    private val repository:AnimeListRepository = AnimeListRepositoryImpl(RetrofitService.apiService)
    val animeData:StateFlow<AnimeLists>
        get() = _animeData

    fun fetchAnimeData(){
        viewModelScope.launch{
            repository.fetchTopAnime().collect{
                _animeData.emit(it)
            }
        }
    }
}