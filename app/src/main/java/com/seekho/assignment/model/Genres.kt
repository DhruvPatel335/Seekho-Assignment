package com.seekho.assignment.model

import com.google.gson.annotations.SerializedName

data class Genres(
    @SerializedName("name") var name: String? = null,
)