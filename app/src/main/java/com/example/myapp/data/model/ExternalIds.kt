package com.example.myapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ExternalIds(val upc: String) : Parcelable
