package com.example.webservice.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    var id: Int,
    var name: String,
    var username: String,
    var email: String,
    var company: String,
    var website: String
    ) : Parcelable
