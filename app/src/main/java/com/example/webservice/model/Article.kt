package com.example.webservice.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article (
    var id: Int,
    var userid: Int,
    var title: String,
    var content: String
) : Parcelable

