package org.wit.pcgamelist.singlegamedetails

import androidx.lifecycle.LiveData
import io.reactivex.disposables.CompositeDisposable
import org.wit.pcgamelist.data.api.GamesApi
import org.wit.pcgamelist.data.repository.GameDetailsDataSource
import org.wit.pcgamelist.data.vo.GameDetails

class GameDetailsRepository(private val apiService: GamesApi) {

    lateinit var gameDetailsDataSource: GameDetailsDataSource

    fun fetchSingleGameDetails(compositeDisposable: CompositeDisposable, gameId: Int): LiveData<GameDetails> {

        gameDetailsDataSource = GameDetailsDataSource(apiService, compositeDisposable)
        gameDetailsDataSource.fetchGameDetails(gameId)

        return gameDetailsDataSource.downloadedGameResponse

    }


}