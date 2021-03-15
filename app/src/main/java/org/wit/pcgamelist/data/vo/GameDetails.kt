package org.wit.pcgamelist.data.vo

import com.google.gson.annotations.SerializedName

data class GameDetails (
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("background_image")
    val background_image: String
)