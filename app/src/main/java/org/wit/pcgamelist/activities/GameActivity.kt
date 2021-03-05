package org.wit.pcgamelist.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
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

    lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {

        database = Firebase.database.reference

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pcgames)
        app = application as MainApp
        var edit = false

        var addBtnFeedback: String = getString(R.string.text_addBtn)

        //Add action bar and set title
        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)

        if (intent.hasExtra("game_edit")) {
            game = intent.extras?.getParcelable<PCGamesModel>("game_edit")!!
            edit = true
            gameTitle.setText(game.title)
            gameDescription.setText(game.description)

            btnAdd.setText(R.string.button_saveGame)
        }

        btnAdd.setOnClickListener() {
            game.title = gameTitle.text.toString()
            game.description = gameDescription.text.toString()

            if (game.title.isEmpty()) {
                toast(addBtnFeedback)
            } else {
               if (edit){
                   app.games.update(game.copy())
                   updateGame()
               } else {
                   app.games.create(game.copy())
                   writeNewGame()
               }
            }
            info("add Button Pressed: $gameTitle, $gameDescription")
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

   private fun writeNewGame(){
        val game = game

        val gameId = database.push().key

        database.child("Games").child(gameId!!).setValue(game)
    }

    private fun updateGame() {

    }

}