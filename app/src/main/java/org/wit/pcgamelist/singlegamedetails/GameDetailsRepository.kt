package org.wit.pcgamelist.singlegamedetails

import androidx.lifecycle.LiveData
import io.reactivex.disposables.CompositeDisposable
import org.wit.pcgamelist.data.api.GamesApi
import org.wit.pcgamelist.data.repository.GameDetailsNetworkDataSource
import org.wit.pcgamelist.data.vo.GameDetails

class GameDetailsRepository (private val apiService : GamesApi) {

    lateinit var gameDetailsNetworkDataSource: GameDetailsNetworkDataSource

    fun fetchSingleMovieDetails (compositeDisposable: CompositeDisposable, gameId: Int) : LiveData<GameDetails> {

        gameDetailsNetworkDataSource = GameDetailsNetworkDataSource(apiService,compositeDisposable)
        gameDetailsNetworkDataSource.fetchGameDetails(gameId)

        return gameDetailsNetworkDataSource.downloadedGameResponse

    }





}