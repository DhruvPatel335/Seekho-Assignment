package com.seekho.assignment.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.seekho.assignment.R
import com.seekho.assignment.adapter.AnimeListAdapter
import com.seekho.assignment.model.AnimeData
import com.seekho.assignment.viewmodel.AnimeListViewModel
import kotlinx.coroutines.launch

class AnimeListFragment : Fragment(), AnimeListAdapter.OnAnimeClickListener {
    private lateinit var viewModel: AnimeListViewModel
    private val adapter = AnimeListAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider.create(this)[AnimeListViewModel::class.java]
        observeAnimeData()

    }

    private fun observeAnimeData() {
        lifecycleScope.launch {
            viewModel.animeData.collect {
                adapter.saveData(it)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_anime_listfragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        val recyclerView = requireView().findViewById<RecyclerView>(R.id.rvAnimeList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager
        recyclerView.hasFixedSize()
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchAnimeData()
    }

    override fun onAnimeClick(animeData: AnimeData) {
        val bundle = bundleOf()
        bundle.putInt("AnimeId", animeData.malId!!)
        findNavController().navigate(R.id.action_animeListFragment_to_animeDetailFragment, bundle)
    }
}