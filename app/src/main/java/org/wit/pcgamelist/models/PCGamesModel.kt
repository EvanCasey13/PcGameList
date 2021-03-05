package org.wit.pcgamelist.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PCGamesModel(
    var id: Long = 0,
    var title: String = "",
    var description: String = "") : Parcelable


