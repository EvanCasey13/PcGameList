package org.wit.pcgamelist.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.pcgamelist.models.PCGamesModel

class MainApp : Application(), AnkoLogger {

    val games = ArrayList<PCGamesModel>()

    override fun onCreate() {
        super.onCreate()
        info("PC Game List started")
    }
}