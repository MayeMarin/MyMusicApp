package com.example.myapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Copyrights(val text: String, val type: String) : Parcelable
