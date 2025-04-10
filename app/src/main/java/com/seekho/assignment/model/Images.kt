package com.seekho.assignment.model

import com.google.gson.annotations.SerializedName


data class Images(
    @SerializedName("jpg"  ) var jpg  : Jpg?  = Jpg(),
)
