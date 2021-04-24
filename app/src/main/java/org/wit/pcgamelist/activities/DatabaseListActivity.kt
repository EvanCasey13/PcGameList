package org.wit.pcgamelist.activities

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.database_list_activity.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.intentFor
import org.wit.pcgamelist.R
import org.wit.pcgamelist.main.MainApp
import org.wit.pcgamelist.models.Game
import org.wit.pcgamelist.models.GameViewModel

class DatabaseListActivity : AppCompatActivity(), AnkoLogger, GameListener {

    lateinit var app: MainApp

    lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {

        database = Firebase.database.reference

        super.onCreate(savedInstanceState)
        setContentView(R.layout.database_list_activity)
        app = application as MainApp

        //Add action bar and set title
        toolbar.title = title
        setSupportActionBar(toolbar)

        val adapter = DatabaseListAdapter(this, this)

        games_recycler.layoutManager = LinearLayoutManager(this)

        val itemViewModel = ViewModelProviders.of(this)
                .get(GameViewModel::class.java)

        itemViewModel.gamePagedList.observe(this, {
            adapter.submitList(it)
        })

        games_recycler.adapter = adapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_lists, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onGameClick(game: Game) {
        startActivityForResult(intentFor<ReviewActivity>().putExtra("game_review", game), 0)
    }

}