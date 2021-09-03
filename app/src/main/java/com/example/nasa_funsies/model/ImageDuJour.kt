package com.example.nasa_funsies.model

import com.squareup.moshi.Json

data class ImageDuJour(
    val url: String,
    val title: String,
    @Json(name = "media_type") val mediaType: String
) {
    fun isDisplayable() = mediaType == VALID_MEDIA_TYPE

    companion object {
        const val VALID_MEDIA_TYPE = "image"
    }
}