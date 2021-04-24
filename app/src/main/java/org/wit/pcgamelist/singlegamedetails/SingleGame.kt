package org.wit.pcgamelist.singlegamedetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_single_game.*
import org.wit.pcgamelist.R
import org.wit.pcgamelist.data.api.GamesApi
import org.wit.pcgamelist.data.api.TheGameDBClient
import org.wit.pcgamelist.data.vo.GameDetails

class SingleGame : AppCompatActivity() {

    private lateinit var viewModel: SingleGameViewModel
    private lateinit var gameRepository: GameDetailsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_game)

        val gameId: Int = intent.getIntExtra("id", 1)

        val apiService: GamesApi = TheGameDBClient.getClient()
        gameRepository = GameDetailsRepository(apiService)

        viewModel = getViewModel(gameId)

        viewModel.gameDetails.observe(this, Observer {
            bindUI(it)
        })


    }

    fun bindUI(it: GameDetails) {
        game_title.text = it.name
        game_description.text = it.description
        game_rating.text = it.metacritic.toString()
        game_released.text = it.released
        game_playtime.text = it.playtime.toString()

        Glide.with(game_poster)
                .load(it.background_image)
                .into(game_poster);

    }


    private fun getViewModel(gameId: Int): SingleGameViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return SingleGameViewModel(gameRepository, gameId) as T
            }
        })[SingleGameViewModel::class.java]
    }
}