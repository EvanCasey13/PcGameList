package org.wit.pcgamelist.activities

import android.app.Activity
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import io.reactivex.rxjava3.annotations.NonNull
import kotlinx.android.synthetic.main.review_activity.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.pcgamelist.R
import org.wit.pcgamelist.main.MainApp
import org.wit.pcgamelist.models.Game
import org.wit.pcgamelist.models.ReviewModel
import java.io.ByteArrayOutputStream
import java.io.File
import java.util.*


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
                reviewTitle.text = aGame.name
                reviewRating.text = aGame.rating
                reviewReleased.text = aGame.released
                reviewDescription.setText(aReview.reviewDescription)
                Glide.with(reviewImageView.context)
                        .load(aGame.background_image)
                        .into(reviewImageView)
            }

            btnAdd.setOnClickListener {
                saveReview()
                saveImage(reviewImageView, this)
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
        val query: Query = ref
                .child(getString(R.string.gameTitle))
                .orderByChild("gameTitle")
                .equalTo(gameName)

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(@NonNull dataSnapshot: DataSnapshot) {
                if (dataSnapshot.childrenCount > 0) {
                    Toast.makeText(applicationContext, "A Review for this game already exists", Toast.LENGTH_LONG).show()
                } else {
                    val reviewId = ref.push().key

                    val review = ReviewModel(reviewId!!, gameName, gameRating, gameReleased, reviewDescription)

                    ref.child(reviewId).setValue(review)
                    Toast.makeText(applicationContext, "Review saved successfully", Toast.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e(TAG, databaseError.message)
            }
        })
    }

    fun saveImage(itemImage: View, activity: Activity) {
        val fileName: String
        val imageFromView = getBitmapFromView(itemImage)

        ByteArrayOutputStream().apply {
            imageFromView.compress(Bitmap.CompressFormat.JPEG, 100, this)
            fileName = UUID.nameUUIDFromBytes(this.toByteArray()).toString().replace("-", "")
        }

        val imageFile =  File("${activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES)}/ChatOut/$fileName.jpg/")

        if (!imageFile.exists()) {

            val contentResolver = ContentValues().apply {
                put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis())
                put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
                put(MediaStore.Images.Media.DATA, imageFile.absolutePath)
            }

            activity.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentResolver).apply {
                imageFromView.compress(Bitmap.CompressFormat.JPEG, 100, activity.contentResolver.openOutputStream(this!!))
            }


            Toast.makeText(activity, "saved", Toast.LENGTH_SHORT).show()
        } else
            Toast.makeText(activity, "Already saved", Toast.LENGTH_SHORT).show()
    }



    fun getBitmapFromView(view: View): Bitmap {
        return Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888).apply {
            Canvas(this).apply {
                view.draw(this)
            }
        }
    }

}