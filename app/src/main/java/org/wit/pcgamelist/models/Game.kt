package org.wit.pcgamelist.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Game (
        @SerializedName("name")
        val name: String = "",
        @SerializedName("released")
        val released: String = "",
        @SerializedName("rating")
        val rating: String = "",
        @SerializedName("background_image")
        val background_image: String ="",
        @SerializedName("id")
        val id: Int = 0
        ) : Parcelable

data class GameResponse (

        @SerializedName("count")
        val count: Int,
        @SerializedName("next")
        val next: String,
        @SerializedName("previous")
        val previous: String,
        @SerializedName("results")
        var results: List<Game>
)

