package com.atom.searchcoffe.domain.dto


import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("token")
    val token: String,
    @SerializedName("tokenLifetime")
    val tokenLifetime: Int
)