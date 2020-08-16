package com.nauman404.data.local.models

import androidx.room.ColumnInfo
import com.squareup.moshi.Json
import java.io.Serializable

data class Movie(

    @Json(name = "cast")
    val cast: List<String> = listOf(),

    @Json(name = "year")
    val year: Int = 0,

    @Json(name = "genres")
    val genres: List<String> = listOf(),

    @Json(name = "rating")
    val rating: Int = 0,

    @ColumnInfo(name = "title")
    val title: String = ""

) : Serializable