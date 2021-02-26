package org.wit.pcgamelist.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.pcgamelist.R
import org.wit.pcgamelist.models.PCGamesModel

class GameActivity : AppCompatActivity(), AnkoLogger {

    var game = PCGamesModel()
    val games = ArrayList<PCGamesModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        info("Games list Activity started")

        btnAdd.setOnClickListener() {
            game.title = gameTitle.text.toString()
            game.description = gameDescription.text.toString()
            if (game.title.isNotEmpty() &&  game.description.isNotEmpty()) {
                games.add(game.copy())
                games.forEach { info("add Button Pressed: ${it.title}, ${it.description}")}
            }
            else {
                toast ("Please Enter a title")
            }
        }
    }
}