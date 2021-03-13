package org.wit.pcgamelist.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_game.view.*
import org.wit.pcgamelist.R
import org.wit.pcgamelist.models.Game

class DatabaseListAdapter : PagedListAdapter<Game, DatabaseListAdapter.GameViewHolder>(GAME_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
    val view  = LayoutInflater.from(parent.context)
        .inflate(R.layout.layout_game, parent, false)

        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {

        val game = getItem(position)
        game?.let { holder.bind(game) }
    }

    class GameViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        private val gameImage = view.gameImg
        private val gameName = view.gameViewTitle
        private val gameReleasedDate = view.gameViewReleased
        private val gameRating = view.gameViewRating

        fun bind(game : Game){
            gameName.text = game.name
            gameReleasedDate.text = game.released
            gameRating.text = game.rating.toString()

            Glide.with(gameImage.context)
                .load(game.background_image)
                .into(gameImage)
        }

    }

    companion object {
        private val GAME_COMPARATOR = object : DiffUtil.ItemCallback<Game>() {
            override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean  =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean =
                newItem == oldItem



        }
    }

}