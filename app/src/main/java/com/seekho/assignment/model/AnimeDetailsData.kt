package com.seekho.assignment.model

import com.google.gson.annotations.SerializedName


data class AnimeDetailsData(
    @SerializedName("mal_id") var malId: Int? = null,
    @SerializedName("images") var images: Images? = Images(),
    @SerializedName("trailer") var trailer: Trailer? = Trailer(),
    @SerializedName("title") var title: String? = null,
    @SerializedName("episodes") var episodes: Int? = null,
    @SerializedName("status") var status: String? = null,
    @SerializedName("rating") var rating: String? = null,
    @SerializedName("synopsis") var synopsis: String? = null,
    @SerializedName("genres") var genres: ArrayList<Genres> = arrayListOf(),
    @SerializedName("broadcast") var broadcast: Broadcast? = Broadcast()
)