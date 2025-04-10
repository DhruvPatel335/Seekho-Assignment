package com.seekho.assignment.model

import com.google.gson.annotations.SerializedName

data class AnimeDetails(
    @SerializedName("data") var data: AnimeDetailsData? = AnimeDetailsData()
)