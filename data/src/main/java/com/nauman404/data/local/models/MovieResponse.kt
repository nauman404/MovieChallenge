package com.nauman404.data.local.models

import com.squareup.moshi.Json

data class MoviesResponse(
    @Json(name = "movies")
    val movies: MutableList<Movie>
)