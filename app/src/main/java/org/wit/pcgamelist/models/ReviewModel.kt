package org.wit.pcgamelist.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReviewModel (
        var id: Long = 0,
        var gameTitle: String = "",
        var reviewDescription: String = "") : Parcelable