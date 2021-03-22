package org.wit.pcgamelist.models

interface ReviewStore {
    fun findAll(): List<ReviewModel>
    fun create(review: ReviewModel)
    fun update(review: ReviewModel)
}