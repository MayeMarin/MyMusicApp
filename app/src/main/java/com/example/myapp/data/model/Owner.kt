package com.example.myapp.data.model

import com.example.myapp.data.model.ExternalUrls
import com.google.gson.annotations.SerializedName

data class Owner(
    @SerializedName("display_name")
    val displayName: String?,
    @SerializedName("external_urls")
    val externalUrls: ExternalUrls,
    val href: String,
    val id: String,
    val type: String,
    val uri: String)
