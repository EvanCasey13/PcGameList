import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_game.view.*
import org.wit.pcgamelist.R
import org.wit.pcgamelist.models.PCGamesModel

class GameAdapter constructor(private var games: List<PCGamesModel>,
    private val listener: GameListener) : RecyclerView.Adapter<GameAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card_game,
                parent,
                false
            )
        )
    }

    interface GameListener {
        fun onGameClick(game: PCGamesModel)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val game = games[holder.adapterPosition]
        holder.bind(game, listener)
    }

    override fun getItemCount(): Int = games.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(game: PCGamesModel, listener: GameListener) {
            itemView.gameTitle.text = game.title
            itemView.description.text = game.description
            itemView.setOnClickListener { listener.onGameClick(game) }
        }
    }
}