package org.wit.pcgamelist.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.wit.pcgamelist.data.api.GamesApi
import org.wit.pcgamelist.data.vo.GameDetails
import java.lang.Exception

class GameDetailsDataSource (private val apiService : GamesApi, private val compositeDisposable: CompositeDisposable){

    private val _downloadedGameDetailsResponse = MutableLiveData<GameDetails>()
    val downloadedGameResponse: LiveData<GameDetails>
    get() = _downloadedGameDetailsResponse

    fun fetchGameDetails(gameId: Int) {

        try {
            compositeDisposable.add(
                    apiService.getGameDetails(gameId)
                            .subscribeOn(Schedulers.io())
                            .subscribe(
                                    {
                                        _downloadedGameDetailsResponse.postValue(it)
                                    },
                                    {
                                        it.message?.let { it1 -> Log.e("GameDetailsDataSource", it1) }
                                    }
                            )
            )
        }

        catch (e: Exception){
            e.message?.let { Log.e("GameDetailsDataSource", it) }
        }
    }
}
