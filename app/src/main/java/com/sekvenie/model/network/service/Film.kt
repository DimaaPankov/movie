package com.sekvenie.model.network.service

import com.google.gson.annotations.SerializedName

data class Film(
    val description: String,
    val genres: List<String>,
    val id: Int,
    @SerializedName("image_url")
    val imageurl: String,
    @SerializedName("localized_name")
    val localizedname: String,
    val name: String,
    val rating: Double,
    val year: Int
)