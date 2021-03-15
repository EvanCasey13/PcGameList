package org.wit.pcgamelist.datasource

import androidx.paging.PageKeyedDataSource
import org.wit.pcgamelist.models.Game
import org.wit.pcgamelist.models.GameApiServiceBuilder
import org.wit.pcgamelist.models.GameResponse
import org.wit.pcgamelist.data.api.GamesApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GameDataSource : PageKeyedDataSource<Int, Game>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Game>
    ) {

        val service = GameApiServiceBuilder.buildService(GamesApi::class.java)
        val call = service.getGames(FIRST_PAGE)

        call.enqueue(object : Callback<GameResponse>{
            override fun onResponse(call: Call<GameResponse>, response: Response<GameResponse>) {

                if (response.isSuccessful){
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.results

                    responseItems.let {
                        callback.onResult(responseItems, null, FIRST_PAGE+1)
                    }
                }

            }

            override fun onFailure(call: Call<GameResponse>, t: Throwable) {

            }

        })

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Game>) {
    val service = GameApiServiceBuilder.buildService(GamesApi::class.java)
        val call = service.getGames(params.key)

        call.enqueue(object : Callback<GameResponse>{
            override fun onResponse(call: Call<GameResponse>, response: Response<GameResponse>) {
                if (response.isSuccessful){
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.results

                    val key = params.key + 1

                    responseItems.let {
                        callback.onResult(responseItems, key)
                    }
                }
            }

            override fun onFailure(call: Call<GameResponse>, t: Throwable) {

            }

        })

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Game>) {
        val service = GameApiServiceBuilder.buildService(GamesApi::class.java)
        val call = service.getGames(params.key)

        call.enqueue(object : Callback<GameResponse>{
            override fun onResponse(call: Call<GameResponse>, response: Response<GameResponse>) {
                if (response.isSuccessful){
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.results

                    val key = if (params.key > 1)params.key - 1 else 0

                    responseItems.let {
                        callback.onResult(responseItems, key)
                    }
                }
            }

            override fun onFailure(call: Call<GameResponse>, t: Throwable) {

            }

        })

    }

    companion object  {
        const val PAGE_SIZE = 40
        const val FIRST_PAGE = 1
    }
}