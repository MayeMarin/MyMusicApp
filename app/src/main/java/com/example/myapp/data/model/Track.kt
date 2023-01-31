package com.example.myapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Track(
    val id: String,
    val name: String,
    val album: Album,
    val externalUrls: ExternalUrls,
    val artists: List<Artist>,
    @SerializedName("preview_url")
    val previewUrl: String? = null
    ) : Parcelable

