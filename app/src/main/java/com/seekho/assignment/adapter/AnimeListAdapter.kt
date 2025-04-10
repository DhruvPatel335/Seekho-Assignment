package com.seekho.assignment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.seekho.assignment.R
import com.seekho.assignment.model.AnimeData
import com.seekho.assignment.model.AnimeLists

class AnimeListAdapter : RecyclerView.Adapter<AnimeListAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val animeTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val numberOfEpisodes: TextView = itemView.findViewById(R.id.tvTotalEpisodes)
        val ratings: TextView = itemView.findViewById(R.id.tvRating)
        val poster: ImageView = itemView.findViewById(R.id.ivAnimePoster)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.anime_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return asyncListDiffer.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = asyncListDiffer.currentList[position]
        holder.animeTitle.text = item.title
        holder.ratings.text = item.rating
        holder.numberOfEpisodes.text = item.episodes.toString()
        Glide.with(holder.itemView.context).load(item.url).into(holder.poster)
    }

    private val diffUtil = object : DiffUtil.ItemCallback<AnimeData>() {
        override fun areItemsTheSame(oldItem: AnimeData, newItem: AnimeData):
                Boolean {
            return oldItem.malId == newItem.malId
        }

        override fun areContentsTheSame(oldItem: AnimeData, newItem: AnimeData):
                Boolean {
            return oldItem == newItem
        }
    }

    private val asyncListDiffer = AsyncListDiffer(this, diffUtil)
    fun saveData(animeList: AnimeLists) {
        asyncListDiffer.submitList(animeList.data)
    }

}