package com.nauman404.data.local.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MoviesResponse(
    @Json(name = "movies")
    val movies: MutableList<Movie>
)