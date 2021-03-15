package org.wit.pcgamelist.singlegamedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import org.wit.pcgamelist.data.vo.GameDetails

class SingleGameViewModel (private val gameRepository : GameDetailsRepository, gameId: Int)  : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val  gameDetails : LiveData<GameDetails> by lazy {
        gameRepository.fetchSingleMovieDetails(compositeDisposable,gameId)
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }



}