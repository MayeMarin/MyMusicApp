package com.example.myapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AddedBy(
    @SerializedName("external_urls")
    val externalUrls: ExternalUrls,
    val href: String?,
    val id: String,
    val type: String,
    val uri: String): Parcelable
