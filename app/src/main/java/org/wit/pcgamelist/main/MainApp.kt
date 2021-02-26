package org.wit.pcgamelist.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.pcgamelist.models.GameMemStore

class MainApp : Application(), AnkoLogger {

    val games = GameMemStore()

    override fun onCreate() {
        super.onCreate()
        info("PC Game List started")
    }
}