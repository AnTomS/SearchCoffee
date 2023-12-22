package com.atom.searchcoffe.domain.dto


import com.google.gson.annotations.SerializedName

data class LocationRespondItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("point")
    val point: Point
)