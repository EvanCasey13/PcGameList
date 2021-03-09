package org.wit.pcgamelist.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_games_list.toolbar
import kotlinx.android.synthetic.main.database_list_activity.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivityForResult
import org.wit.pcgamelist.R
import org.wit.pcgamelist.main.MainApp
import org.wit.pcgamelist.models.Game
import org.wit.pcgamelist.models.GamesApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        GamesApi().getGames().enqueue(object: Callback<Game>{
            override fun onResponse(call: Call<Game>, response: Response<Game>) {

              val games = response.body()

              games?.let {
                  showGames(it.results)
              }


            }
            override fun onFailure(call: Call <Game>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun showGames(games: List<Game>){
        games_recycler.layoutManager = LinearLayoutManager(this)
        games_recycler.adapter = DatabaseListAdapter(games)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_lists, menu)
        return super.onCreateOptionsMenu(menu)
    }

     override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.app_bar_nextPage -> startActivityForResult<DatabaseListActivity>(0)

        }
        return super.onOptionsItemSelected(item)
    }

}