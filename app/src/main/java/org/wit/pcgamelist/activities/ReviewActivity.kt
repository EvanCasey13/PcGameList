package org.wit.pcgamelist.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_games_list.toolbar
import kotlinx.android.synthetic.main.card_review.*
import kotlinx.android.synthetic.main.review_activity.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.pcgamelist.R
import org.wit.pcgamelist.main.MainApp
import org.wit.pcgamelist.models.ReviewModel

class ReviewActivity : AppCompatActivity(), AnkoLogger {

        var review = ReviewModel()

        lateinit var app: MainApp

        lateinit var database: DatabaseReference

        override fun onCreate(savedInstanceState: Bundle?) {

            database = Firebase.database.reference

            super.onCreate(savedInstanceState)
            setContentView(R.layout.review_activity)
            app = application as MainApp
            var edit = false

            var addBtnFeedback: String = getString(R.string.text_addBtn)
            var saveBtn: String = getString(R.string.button_saveGame)

            //Add action bar and set title
            toolbar.title = title
            setSupportActionBar(toolbar)

            if (intent.hasExtra("review_edit")) {
                review = intent.extras?.getParcelable<ReviewModel>("review_edit")!!
                edit = true
                reviewTitle.setText(review.gameTitle)
                reviewDescription.setText(review.reviewDescription)

                btnAdd.text = saveBtn
            }

            btnAdd.setOnClickListener() {
                review.gameTitle = reviewTitle.text.toString()
                review.reviewDescription = reviewDescription.text.toString()

                if (review.gameTitle.isEmpty()) {
                    toast(addBtnFeedback)
                } else {
                    if (edit){
                        app.reviews.update(review.copy())
                    } else {
                        app.reviews.create(review.copy())
                    }
                }
                info ("add Button Pressed: $reviewTitle, $reviewDescription")
                finish()
            }
        }
}