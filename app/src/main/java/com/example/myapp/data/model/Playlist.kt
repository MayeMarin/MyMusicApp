package com.example.myapp.data.model

import com.google.gson.annotations.SerializedName

data class Playlist(
    val id: String,
    val name: String, // name of the playlist
    val description: String,
    val images: List<Image>,
    val tracks: Tracks,
    val collaborative: Boolean,
    @SerializedName("external_urls")
    val externalUrls: ExternalUrls,
    val followers: Followers,
    val href: String?,
    val owner: Owner,
    @SerializedName("primary_color")
    val primaryColor: String,
    val public: Boolean,
    @SerializedName("snapshot_id")
    val snapshotId: String,
    val artist: Artist
)

