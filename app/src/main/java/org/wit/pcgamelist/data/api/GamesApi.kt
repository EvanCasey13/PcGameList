package org.wit.pcgamelist.data.api

import io.reactivex.Single
import org.wit.pcgamelist.data.vo.GameDetails
import org.wit.pcgamelist.models.GameResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//https://api.rawg.io/api/games/3498?key=fae5e07b82494776b3df9791c164ba3d

interface GamesApi {

    @GET("games")

    fun getGames(@Query("page") page :Int): Call<GameResponse>


    @GET("{id}")
    fun getGameDetails(@Path("id") id: Int): Single<GameDetails>


}