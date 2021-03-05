package org.wit.pcgamelist.models

data class Game (
        val name: String,
        val description: String,
        val deck: String,
        val image: String,
        val isNew: Boolean
        )