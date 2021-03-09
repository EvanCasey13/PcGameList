/*package org.wit.pcgamelist.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class GameMemStore : GameStore, AnkoLogger {

    val games = ArrayList<PCGamesModel>()

    override fun findAll(): List<PCGamesModel> {
        return games
    }

    override fun create(game: PCGamesModel) {
        game.id = getId()
        games.add(game)
        logAll()
    }

    override fun update(game: PCGamesModel) {
        var foundGame: PCGamesModel? = games.find { g -> g.id == game.id}
        if(foundGame != null){
            foundGame.title = game.title
            foundGame.description = game.description
            logAll()
        }
    }

    fun logAll(){
        games.forEach{ info("$it") }
    }

}*/