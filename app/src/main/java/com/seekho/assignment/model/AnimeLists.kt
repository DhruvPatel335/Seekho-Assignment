package com.seekho.assignment.model

import com.google.gson.annotations.SerializedName

data class AnimeLists(
    @SerializedName("data") var data: ArrayList<AnimeData> = arrayListOf(),
)