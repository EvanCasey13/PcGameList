package org.wit.pcgamelist.activities

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.review_activity.toolbar
import kotlinx.android.synthetic.main.review_list_activity.*
import org.jetbrains.anko.toast
import org.wit.pcgamelist.R
import org.wit.pcgamelist.main.MainApp
import org.wit.pcgamelist.models.ReviewModel

class ReviewListActivity : AppCompatActivity() {

    lateinit var app: MainApp

    lateinit var ref: DatabaseReference

    lateinit var reviewList: MutableList<ReviewModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.review_list_activity)

        app = application as MainApp

        ref = FirebaseDatabase.getInstance().getReference("reviews")
        reviewList = mutableListOf()

        //Add action bar and set title
        toolbar.title = title
        setSupportActionBar(toolbar)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager


        ref.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
             if (snapshot.exists()){
                 reviewList.clear()
                 for (r in snapshot.children){

                     val review = r.getValue(ReviewModel::class.java)
                     reviewList.add(review!!)
                 }
                 val adapter = ReviewAdapter(reviewList)
                 recyclerView.adapter = adapter
             }
            }

            override fun onCancelled(error: DatabaseError) {
                toast(error.message)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_lists, menu)
        return super.onCreateOptionsMenu(menu)
    }


}