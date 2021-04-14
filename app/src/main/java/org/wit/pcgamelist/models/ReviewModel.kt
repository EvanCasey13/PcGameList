package org.wit.pcgamelist.models

data class ReviewModel(
        val id: String,
        val gameTitle: String,
        val gameRating: String,
        val gameReleased: String,
        val reviewDescription: String ) {

        constructor() : this("", "", "", "", ""){

        }
}

