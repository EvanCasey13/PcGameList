package org.wit.pcgamelist.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_review.view.*
import org.wit.pcgamelist.R
import org.wit.pcgamelist.models.ReviewModel

class ReviewAdapter constructor(private var reviews: List<ReviewModel>,
                              private val listener: ReviewListener) : RecyclerView.Adapter<ReviewAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.card_review,
                        parent,
                        false
                )
        )
    }

    interface ReviewListener {
        fun onReviewClick(review: ReviewModel)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val review = reviews[holder.adapterPosition]
        holder.bind(review, listener)
    }

    override fun getItemCount(): Int = reviews.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(review: ReviewModel, listener: ReviewListener) {
            itemView.reviewCardTitle.text = review.gameTitle
            itemView.reviewCardDescription.text = review.reviewDescription
            itemView.setOnClickListener { listener.onReviewClick(review) }
        }
    }
    }