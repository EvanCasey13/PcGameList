package org.wit.pcgamelist.models

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://api.rawg.io/api/"

interface GamesApi {

   @GET("games")

    fun getGames(@Query("page") page :Int): Call<GameResponse>

    companion object {
        operator fun invoke() : GamesApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GamesApi::class.java)
        }
    }
}