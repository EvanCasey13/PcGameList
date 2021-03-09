package org.wit.pcgamelist.models

import androidx.appcompat.app.AppCompatActivity
import okhttp3.OkHttpClient
import org.jetbrains.anko.AnkoLogger
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GameApiServiceBuilder {

    //Base Url
    private val BASE_URL = "https://api.rawg.io/api/"

    //create okHttp client
    private val okHttp = OkHttpClient.Builder()

    //create Retrofit builder
    private val builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())

    //create retrofit instance
    private val retrofit = builder.build()

    fun<T> buildService(serviceType : Class<T>) : T {
        return retrofit.create(serviceType)
    }
}