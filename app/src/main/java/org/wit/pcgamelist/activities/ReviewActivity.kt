package org.wit.pcgamelist.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.card_review.*
import kotlinx.android.synthetic.main.review_activity.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.pcgamelist.R
import org.wit.pcgamelist.main.MainApp
import org.wit.pcgamelist.models.Game
import org.wit.pcgamelist.models.ReviewModel

class ReviewActivity : AppCompatActivity(), AnkoLogger {

        var aGame = Game()
        var aReview = ReviewModel()
        lateinit var app: MainApp

        override fun onCreate(savedInstanceState: Bundle?) {

            super.onCreate(savedInstanceState)
            setContentView(R.layout.review_activity)
            app = application as MainApp

            //Add action bar and set title
            toolbar.title = title
            setSupportActionBar(toolbar)

            if (intent.hasExtra("game_review")) {
                aGame = intent.extras?.getParcelable("game_review")!!
                reviewTitle.setText(aGame.name)
                reviewRating.setText(aGame.rating)
                reviewReleased.setText(aGame.released)
                reviewDescription.setText(aReview.reviewDescription)
                Glide.with(reviewImageView.context)
                        .load(aGame.background_image)
                        .into(reviewImageView)
            }

            btnAdd.setOnClickListener() {
                saveReview()
                info("add Button Pressed: $reviewTitle, $reviewDescription")
                finish()
            }
        }

    private fun saveReview(){
        val gameName = reviewTitle.text.toString()
        val gameRating = reviewRating.text.toString()
        val gameReleased = reviewReleased.text.toString()
        val reviewDescription = reviewDescription.text.toString()

        if (reviewDescription.isEmpty()){
            toast("Please write your review")
            return
        }

        val ref = FirebaseDatabase.getInstance().getReference("reviews")

        val reviewId = ref.push().key

        val review = ReviewModel(reviewId!!, gameName, gameRating, gameReleased, reviewDescription)

        ref.child(reviewId).setValue(review)
        Toast.makeText(applicationContext, "Review saved successfully", Toast.LENGTH_LONG).show()
    }

}