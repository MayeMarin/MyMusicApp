package com.example.myapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Artist(
    val id: String,
    val name: String,
    val images: List<Image>,
    val genres: List<String>,
    val followers: Followers,
    val popularity: Int,
    val href: String?,
    val type: String,
    val uri: String,
    @SerializedName("external_urls")
    val externalUrls: ExternalUrls) : Parcelable

class Artists(val artists: List<Artist>)