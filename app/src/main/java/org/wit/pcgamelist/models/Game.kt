package org.wit.pcgamelist.models

import com.google.gson.annotations.SerializedName

data class Game (
        @SerializedName("results")
        var results: List<Game> = ArrayList(),
        @SerializedName("name")
        val name: String,
        @SerializedName("released")
        val released: String,
        @SerializedName("rating")
        val rating: Double,
        @SerializedName("background_image")
        val background_image: String,
        @SerializedName("next")
        val next: String

        )