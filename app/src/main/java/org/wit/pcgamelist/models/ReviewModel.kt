package org.wit.pcgamelist.models

data class ReviewModel(
        val id: String,
        val gameTitle: String,
        val reviewDescription: String ) {

        constructor() : this("", "", ""){

        }
}

