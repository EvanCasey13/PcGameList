package org.wit.pcgamelist.models

interface GameStore {
    fun findAll(): List<PCGamesModel>
    fun create(game: PCGamesModel)
    fun update(game: PCGamesModel)

}