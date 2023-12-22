package com.atom.searchcoffe.domain.dto


import com.google.gson.annotations.SerializedName

data class Point(
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("longitude")
    val longitude: String
)