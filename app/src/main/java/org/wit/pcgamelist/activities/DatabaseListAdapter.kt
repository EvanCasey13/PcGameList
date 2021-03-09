package org.wit.pcgamelist.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_game.view.*
import org.wit.pcgamelist.R
import org.wit.pcgamelist.models.Game

class DatabaseListAdapter(val games: List<Game>) : RecyclerView.Adapter<DatabaseListAdapter.GameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
    return GameViewHolder(
            LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_game, parent, false)

    )
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
      val game = games[position]

        holder.bind(game)

    }

    override fun getItemCount() = games.size


    class GameViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(game: Game) {
            view.textViewTitle.text = game.name
            view.textViewRating.text = game.rating.toString()
            view.textViewReleased.text = game.released

            Glide.with(view.context)
                .load(game.background_image)
                .into(view.gameImg)

        }
    }

}