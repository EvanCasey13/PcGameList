package org.wit.pcgamelist.activities

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.card_review.view.*
import kotlinx.android.synthetic.main.layout_update_review.view.*
import kotlinx.android.synthetic.main.review_activity.*
import org.wit.pcgamelist.R
import org.wit.pcgamelist.models.ReviewModel

class ReviewAdapter constructor(private val reviews: List<ReviewModel>) : RecyclerView.Adapter<ReviewAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card_review,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val review = reviews[holder.adapterPosition]
        holder.bind(review)
    }

    override fun getItemCount(): Int = reviews.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textViewUpdate = itemView.reviewCardUpdate
        val textViewDelete = itemView.reviewCardDelete

        fun bind(review: ReviewModel) {
            itemView.gameReviewTitle.text = review.gameTitle
            itemView.gameReviewDescription.text = review.reviewDescription
            itemView.gameReviewRating.text = review.gameRating
            itemView.gameReviewReleased.text = review.gameReleased

            textViewUpdate.setOnClickListener {
                showUpdateDialog(review)
            }

          textViewDelete.setOnClickListener {
                deleteReview(review)
            }

        }

        fun showUpdateDialog(review: ReviewModel) {
            val builder = AlertDialog.Builder(itemView.context)
            builder.setTitle("Update Review")

            val inflater = LayoutInflater.from(itemView.context)

            val view = inflater.inflate(R.layout.layout_update_review, null)

            val updateTitle = view.findViewById<TextView>(R.id.reviewUpdatedTitle)

            val updateReleased = view.findViewById<TextView>(R.id.reviewUpdatedReleased)

            val updateRating = view.findViewById<TextView>(R.id.reviewUpdatedRating)

            //val updateImage = view.findViewById<TextView>(R.id.reviewUpdatedImageView)

            val updateDescription = view.findViewById<TextView>(R.id.reviewUpdatedDescription)

            updateTitle.text = review.gameTitle

            updateReleased.text = review.gameReleased

            updateRating.text = review.gameRating

            updateDescription.text = review.reviewDescription

            builder.setView(view)

            builder.setPositiveButton("Update") { dialog, which ->

                val dbReview = FirebaseDatabase.getInstance().getReference("reviews")

                val reviewUpdatedTitle = updateTitle.text

                val reviewUpdatedReleased = updateReleased.text

                val reviewUpdatedRating = updateRating.text

                val reviewUpdatedDescription = updateDescription.text

                if (reviewUpdatedDescription.isEmpty()){
                    updateDescription.error = "Please enter your review of this game"
                    return@setPositiveButton
                }

                val reviewUp = ReviewModel(review.id, reviewUpdatedTitle.toString(), reviewUpdatedReleased.toString(), reviewUpdatedRating.toString(), reviewUpdatedDescription.toString())

                dbReview.child(review.id).setValue(reviewUp)

                Toast.makeText(itemView.context, "Review Updated", Toast.LENGTH_LONG).show()
            }

            builder.setNegativeButton("Cancel") { dialog, which ->
            }

            val alert = builder.create()
            alert.show()
        }

        fun deleteReview(review: ReviewModel){

            val dbReview = FirebaseDatabase.getInstance().getReference("reviews")

            dbReview.child(review.id).setValue(null)

        }
    }
}
