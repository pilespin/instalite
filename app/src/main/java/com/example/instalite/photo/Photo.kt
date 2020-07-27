package com.example.instalite.photo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Photo(val id: Int,
            val albumId: Int,
            val title: String = "",
            val url: String = "",
            val thumbnailUrl: String = "") : Parcelable {

    override fun toString(): String {
        return ("Photo, id: $id,   albumId: $albumId,   title: $title,   url: $url,   thumbnailUrl: $thumbnailUrl")
    }
}