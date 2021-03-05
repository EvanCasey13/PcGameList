package org.wit.pcgamelist.activities

import GameAdapter
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_games_list.*
import kotlinx.android.synthetic.main.activity_games_list.toolbar
import kotlinx.android.synthetic.main.database_list_activity.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivityForResult
import org.wit.pcgamelist.R
import org.wit.pcgamelist.main.MainApp
import org.wit.pcgamelist.models.Game

class DatabaseListActivity: AppCompatActivity(), AnkoLogger {

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

        val picasso = Picasso.get()

        val games = listOf(
                Game("The Witcher 3", "Wild hunt", "CD Project red", "", true),
                Game("The Witcher 3", "Wild hunt", "CD Project red", "", true),

        )

        games_recycler.layoutManager = LinearLayoutManager(this)
        games_recycler.adapter = DatabaseListAdapter(games)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_lists, menu)
        return super.onCreateOptionsMenu(menu)
    }

     override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.app_bar_exit -> startActivityForResult<HomeActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }

}