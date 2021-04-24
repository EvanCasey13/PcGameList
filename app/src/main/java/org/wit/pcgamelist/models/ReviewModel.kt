package org.wit.pcgamelist.models

data class ReviewModel(
        val id: String,
        val gameTitle: String,
        val gameRating: String,
        val gameReleased: String,
        val reviewDescription: String,
        val background_image: String,
        val gameId: Int = 0) {

        constructor() : this("", "", "", "", "", ""){

        }


}

