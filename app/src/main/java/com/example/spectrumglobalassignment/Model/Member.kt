package com.example.spectrumglobalassignment.Model

import android.os.Parcelable
import java.io.Serializable

data class Member(
    val _id: String,
    val age: Int,
    val email: String,
    val name: Name,
    val phone: String,
    var isFavourite: Boolean = false
):Serializable