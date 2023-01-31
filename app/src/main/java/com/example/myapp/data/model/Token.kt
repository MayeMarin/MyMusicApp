package com.example.myapp.data.model

import com.google.gson.annotations.SerializedName

data class Token(
    @SerializedName("grant_type")
    val grantType: String,
    @SerializedName("client_id")
    val clientId: String,
    @SerializedName("client_secret")
    val clientSecret: String
)
