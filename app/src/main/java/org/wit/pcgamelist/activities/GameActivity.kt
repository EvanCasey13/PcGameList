package org.wit.pcgamelist.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_pcgames.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.toast
import org.wit.pcgamelist.R
import org.wit.pcgamelist.main.MainApp
import org.wit.pcgamelist.models.PCGamesModel

class GameActivity : AppCompatActivity(), AnkoLogger {

    var game = PCGamesModel()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pcgames)
        app = application as MainApp
        var edit = false

        var addBtnFeedback: String = getString(R.string.text_addBtn)
        var saveBtn: String = getString(R.string.button_saveGame)

        //Add action bar and set title
        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)

        if (intent.hasExtra("game_edit")) {
            game = intent.extras?.getParcelable<PCGamesModel>("game_edit")!!
            edit = true
            gameTitle.setText(game.title)
            gameDescription.setText(game.description)

            btnAdd.text = saveBtn
        }

        btnAdd.setOnClickListener() {
            game.title = gameTitle.text.toString()
            game.description = gameDescription.text.toString()
            if (game.title.isEmpty()) {
                toast(addBtnFeedback)
            } else {
               if (edit){
                   app.games.update(game.copy())
               } else {
                   app.games.create(game.copy())
               }
            }
            info ("add Button Pressed: $gameTitle, $gameDescription")
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_game, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> startActivityForResult<GameListActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }

}