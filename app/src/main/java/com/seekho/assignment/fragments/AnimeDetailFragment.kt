package com.seekho.assignment.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.seekho.assignment.R
import com.seekho.assignment.constants.Constants.ANIME_ID
import com.seekho.assignment.viewmodel.AnimeDetailsViewModel
import kotlinx.coroutines.launch


class AnimeDetailFragment : Fragment() {
    private lateinit var viewModel: AnimeDetailsViewModel
    private var animeID: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            animeID = it!!.getInt(ANIME_ID)
        }
        viewModel = ViewModelProvider.create(this)[AnimeDetailsViewModel::class.java]
        initAnimeDetailsObserver()
    }

    private fun initAnimeDetailsObserver() {
        lifecycleScope.launch {
            viewModel.animeDetails.collect {
                Log.d("APICallDetails", it.data.toString())
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_anime_detail, container, false)
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchAnimeDetails(animeID)
    }
}