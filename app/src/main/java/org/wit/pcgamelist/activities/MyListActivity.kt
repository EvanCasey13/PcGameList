package org.wit.pcgamelist.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_games_list.*
import org.jetbrains.anko.AnkoLogger
import org.wit.pcgamelist.R
import org.wit.pcgamelist.main.MainApp

class MyListActivity : AppCompatActivity(), AnkoLogger {

    lateinit var app: MainApp

    lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {

        database = Firebase.database.reference

        super.onCreate(savedInstanceState)
        setContentView(R.layout.mylist_activity)
        app = application as MainApp

        //Add action bar and set title
        toolbar.title = title
        setSupportActionBar(toolbar)

    }





}