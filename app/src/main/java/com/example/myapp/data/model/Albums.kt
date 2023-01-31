package com.example.myapp.data.model
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Albums(
    @SerializedName("album_type")
    val albumType: String,
    val artists: List<Artist>,
    @SerializedName("available_markets")
    val availableMarkets: List<String>,
    val id: String,
    val name: String,
    val images: List<Image>,
    val genres: List<String>,
    val tracks: Tracks,
    val href: String?,
    val popularity: Int,
    val copyrights: List<Copyrights>,
    @SerializedName("external_urls")
    val externalUrls: ExternalUrls,
    val label: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("release_date_precision")
    val releaseDatePrecision: String,
    @SerializedName("total_tracks")
    val totalTracks: Int,
    val type: String,
    val uri: String,
    val externalIds: ExternalIds) : Parcelable

data class Result(val albums: List<Albums> = listOf())