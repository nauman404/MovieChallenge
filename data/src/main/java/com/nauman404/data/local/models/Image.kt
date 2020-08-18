package com.nauman404.data.local.models

import com.squareup.moshi.Json

data class Image(
    @Json(name = "owner")
    val owner: String = "",
    @Json(name = "server")
    val server: String = "",
    @Json(name = "ispublic")
    val ispublic: Int = 0,
    @Json(name = "isfriend")
    val isfriend: Int = 0,
    @Json(name = "farm")
    val farm: Int = 0,
    @Json(name = "id")
    val id: String = "",
    @Json(name = "secret")
    val secret: String = "",
    @Json(name = "title")
    val title: String = "",
    @Json(name = "isfamily")
    val isfamily: Int = 0
) {
    fun mapPhotoUrl(): String {
        return "https://farm$farm.static.flickr.com/$server/${id}_$secret.jpg"
    }
}