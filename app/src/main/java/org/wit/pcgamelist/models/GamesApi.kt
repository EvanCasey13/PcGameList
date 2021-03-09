package org.wit.pcgamelist.models

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://api.rawg.io/api/"

interface GamesApi {

   @GET("games?key=6f7555a402954a4b938f19c9bbe2b16d&page_size=40&page=1")

    fun getGames(): Call<Game>

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