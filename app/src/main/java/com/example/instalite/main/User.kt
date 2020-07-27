package com.example.instalite.main

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User(val id: Int,
           val name: String = "",
           val username: String = "",
           val email: String = "",
           val phone: String = "",
           val website: String = "",
           val address: String = "",
           val company: String = "") : Parcelable {

    override fun toString(): String {
        return ("User, id: $id,   name: $name")
    }
}