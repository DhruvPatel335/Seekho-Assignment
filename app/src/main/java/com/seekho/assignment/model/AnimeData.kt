package com.seekho.assignment.model

import com.google.gson.annotations.SerializedName

data class AnimeData(
    @SerializedName("mal_id") var malId: Int? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("trailer") var trailer: Trailer? = Trailer(),
    @SerializedName("title") var title: String? = null,
    @SerializedName("episodes") var episodes: Int? = null,
    @SerializedName("rating") var rating: String? = null,
    @SerializedName("synopsis") var synopsis: String? = null,
    @SerializedName("explicit_genres") var explicitGenres: ArrayList<String> = arrayListOf(),
    @SerializedName("images") var images: Images? = Images()
)
