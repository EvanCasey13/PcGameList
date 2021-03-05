package org.wit.pcgamelist.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_games_list.*
import kotlinx.android.synthetic.main.activity_games_list.toolbar
import kotlinx.android.synthetic.main.mainactivity_pcgames.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivityForResult
import org.wit.pcgamelist.R
import org.wit.pcgamelist.main.MainApp
import retrofit2.Retrofit


class HomeActivity : AppCompatActivity(), AnkoLogger {

        lateinit var app: MainApp

        lateinit var database: DatabaseReference

        override fun onCreate(savedInstanceState: Bundle?) {

            database = Firebase.database.reference

            super.onCreate(savedInstanceState)
            setContentView(R.layout.mainactivity_pcgames)
            app = application as MainApp

            //Add action bar and set title
            toolbar.title = title
            setSupportActionBar(toolbar)

            val picasso = Picasso.get()

            picasso.load("https://static.wikia.nocookie.net/fallout/images/f/ff/FNV_box_art_%28US%29.jpg/revision/latest/scale-to-width-down/1000?cb=20150327233343")
                    .into(databaseImg)

            picasso.load("https://upload.wikimedia.org/wikipedia/en/f/fd/Resident_Evil_2_Remake.jpg").into(listImg)

            onDatabaseButtonPressed()


        }

        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.menu_main, menu)
            return super.onCreateOptionsMenu(menu)
        }

        private fun onDatabaseButtonPressed() {

        val btnDatabase = findViewById<Button>(R.id.databaseButton)

        btnDatabase.setOnClickListener {
            val intent = Intent(this, DatabaseListActivity::class.java)
        startActivity(intent)

        }
    }


    }
