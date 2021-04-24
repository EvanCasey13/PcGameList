package org.wit.pcgamelist.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import org.wit.pcgamelist.datasource.GameDataSource
import org.wit.pcgamelist.datasource.GameDataSourceFactory

class GameViewModel : ViewModel() {

    val gamePagedList: LiveData<PagedList<Game>>

    private val liveDataSource: LiveData<GameDataSource>

    init {
        val itemDataSourceFactory = GameDataSourceFactory()

        liveDataSource = itemDataSourceFactory.gameLiveDataSource

        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(GameDataSource.PAGE_SIZE)
                .build()

        gamePagedList = LivePagedListBuilder(itemDataSourceFactory, config)
                .build()
    }
}