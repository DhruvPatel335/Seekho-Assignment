package com.seekho.assignment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.seekho.assignment.R
import com.seekho.assignment.constants.Constants.ANIME_ID
import com.seekho.assignment.databinding.FragmentAnimeDetailBinding
import com.seekho.assignment.model.AnimeDetailsData
import com.seekho.assignment.model.Trailer
import com.seekho.assignment.viewmodel.AnimeDetailsViewModel
import kotlinx.coroutines.launch


class AnimeDetailFragment : Fragment() {
    private lateinit var viewModel: AnimeDetailsViewModel
    private var animeID: Int? = null
    private lateinit var binding: FragmentAnimeDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            animeID = it!!.getInt(ANIME_ID)
        }
        viewModel = ViewModelProvider.create(this)[AnimeDetailsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_anime_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAnimeDetailsObserver()
    }

    private fun initAnimeDetailsObserver() {
        lifecycleScope.launch {
            viewModel.animeDetails.collect {
                updateViews(it.data)
            }
        }
    }

    private fun updateViews(data: AnimeDetailsData?) {
        if (data?.trailer != Trailer()) {
            binding.trailerWebView.settings.javaScriptEnabled = true
            val video =
                "<iframe width=\"100%\" height=\"100%\" src=${data!!.trailer!!.embedUrl} title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>"
            binding.trailerWebView.loadData(video, "text/html", "utf-8")
            binding.trailerWebView.visibility = View.VISIBLE
            binding.ivAnimePoster.visibility = View.GONE
        } else {
            Glide.with(requireContext()).load(data.images?.jpg?.imageUrl)
                .into(binding.ivAnimePoster)
            binding.trailerWebView.visibility = View.GONE
            binding.ivAnimePoster.visibility = View.VISIBLE
        }
        binding.tvTitle.text = data.title
        binding.tvSynopsis.text = data.synopsis
        binding.tvEpisodes.text = data.episodes.toString()
        val genreNames = data.genres.mapNotNull { it.name }.joinToString(", ")
        binding.tvGenres.text = genreNames
        binding.tvRatings.text = data.rating
        binding.tvCast.text = getString(
            R.string.at,
            data.broadcast?.day,
            data.broadcast?.time,
            data.broadcast?.timezone
        )

    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchAnimeDetails(animeID)
    }
}