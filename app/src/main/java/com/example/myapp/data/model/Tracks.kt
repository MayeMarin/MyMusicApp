package com.example.myapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tracks(
    val album: Albums,
    val artist: Artist,
    val name: String,
    val id: String,
    val popularity: Int,
    @SerializedName("preview_url")
    val previewUrl: String,
    val href: String?,
    val items: List<PlaylistItem>) : Parcelable

data class Resultado( val tracks: List<Tracks>)






