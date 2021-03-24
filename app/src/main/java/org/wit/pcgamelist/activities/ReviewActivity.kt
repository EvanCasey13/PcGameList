package org.wit.pcgamelist.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_games_list.toolbar
import kotlinx.android.synthetic.main.review_activity.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.pcgamelist.R
import org.wit.pcgamelist.main.MainApp
import org.wit.pcgamelist.models.ReviewModel

class ReviewActivity : AppCompatActivity(), AnkoLogger {

        lateinit var app: MainApp

        override fun onCreate(savedInstanceState: Bundle?) {

            super.onCreate(savedInstanceState)
            setContentView(R.layout.review_activity)
            app = application as MainApp

            //Add action bar and set title
            toolbar.title = title
            setSupportActionBar(toolbar)

            btnAdd.setOnClickListener() {
                        saveReview()
                info("add Button Pressed: $reviewTitle, $reviewDescription")
                finish()
            }
        }

    private fun saveReview(){
        val gameName = reviewTitle.text.toString()
        val reviewDescription = reviewDescription.text.toString()

        if (gameName.isEmpty() && reviewDescription.isEmpty()){
            toast("Please enter a name and description of the review")
            return
        }

        val ref = FirebaseDatabase.getInstance().getReference("reviews")

        val reviewId = ref.push().key

        val review = ReviewModel(reviewId!!, gameName, reviewDescription)

        ref.child(reviewId).setValue(review)
        Toast.makeText(applicationContext, "Review saved successfully", Toast.LENGTH_LONG).show()
    }

}