package com.example.myapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlaylistItem(
    var name: String,
    val track: Track,
    @SerializedName("added_at")
    val addedAt: String,
    @SerializedName("added_by")
    val addedBy: AddedBy,
    @SerializedName("is_local")
    val isLocal: Boolean,
    @SerializedName("primary_color")
    val primaryColor: String,
    ) : Parcelable
