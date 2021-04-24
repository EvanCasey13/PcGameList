package org.wit.pcgamelist.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import org.wit.pcgamelist.models.Game

class GameDataSourceFactory : DataSource.Factory<Int, Game>() {

    val gameLiveDataSource = MutableLiveData<GameDataSource>()

    override fun create(): DataSource<Int, Game> {
        val gameDataSource =
                GameDataSource()
        gameLiveDataSource.postValue(gameDataSource)

        return gameDataSource
    }

}