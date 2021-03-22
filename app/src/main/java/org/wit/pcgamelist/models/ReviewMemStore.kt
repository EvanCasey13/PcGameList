package org.wit.pcgamelist.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class ReviewMemStore : ReviewStore, AnkoLogger {

    val reviews = ArrayList<ReviewModel>()

    override fun findAll(): List<ReviewModel> {
        return reviews
    }

    override fun create(review:ReviewModel) {
        review.id = getId()
        reviews.add(review)
        logAll()
    }

    override fun update(review: ReviewModel) {
        var foundReview: ReviewModel? = reviews.find { r -> r.id == review.id}
        if(foundReview != null){
            foundReview.gameTitle = review.gameTitle
            foundReview.reviewDescription = review.reviewDescription
            logAll()
        }
    }

    fun logAll(){
        reviews.forEach{ info("$it") }
    }

}