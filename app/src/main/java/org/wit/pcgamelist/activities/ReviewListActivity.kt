package org.wit.pcgamelist.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_games_list.*
import kotlinx.android.synthetic.main.review_activity.toolbar
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult
import org.wit.pcgamelist.R
import org.wit.pcgamelist.main.MainApp
import org.wit.pcgamelist.models.ReviewModel

class ReviewListActivity : AppCompatActivity(), ReviewAdapter.ReviewListener {
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.review_list_activity)
        app = application as MainApp
        //Add action bar and set title
        toolbar.title = title
        setSupportActionBar(toolbar)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = ReviewAdapter(app.reviews.findAll(), this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_review, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> startActivityForResult<ReviewActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onReviewClick(review: ReviewModel) {
        startActivityForResult(intentFor<ReviewActivity>().putExtra("review_edit", review), 0)
    }

}