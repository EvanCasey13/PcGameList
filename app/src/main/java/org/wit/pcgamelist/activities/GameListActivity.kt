package org.wit.pcgamelist.activities

import GameAdapter
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_games_list.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult
import org.wit.pcgamelist.R
import org.wit.pcgamelist.main.MainApp
import org.wit.pcgamelist.models.PCGamesModel

class GameListActivity : AppCompatActivity(), GameAdapter.GameListener {

    lateinit var app: MainApp

    lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {

        database = Firebase.database.reference

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_games_list)
        app = application as MainApp

        //Add action bar and set title
        toolbar.title = title
        setSupportActionBar(toolbar)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = GameAdapter(app.games.findAll(), this)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.app_bar_search -> startActivityForResult<GameActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onGameClick(game: PCGamesModel) {
        startActivityForResult(intentFor<GameActivity>().putExtra("game_edit", game), 0)
    }

    private fun addGameEventListener(gameReference: DatabaseReference) {
        // [START game_value_event_listener]
        val gameListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Game object and use the values to update the UI
                val game = dataSnapshot.getValue<PCGamesModel>()
                // ...
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Game failed, log a message
                Log.w("loadGame:onCancelled", databaseError.toException())
            }
        }
        gameReference.addValueEventListener(gameListener)
        // [END Game_value_event_listener]
    }

    }


