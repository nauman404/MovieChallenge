package com.nauman404.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
@Entity(tableName = "movies")
data class Movie(

    @PrimaryKey(autoGenerate = true) val id: Long = 0,

    @Json(name = "cast")
    @ColumnInfo(name = "cast")
    val cast: List<String> = listOf(),

    @Json(name = "year")
    @ColumnInfo(name = "year")
    val year: Int = 0,

    @Json(name = "genres")
    @ColumnInfo(name = "genres")
    val genres: List<String> = listOf(),

    @Json(name = "rating")
    @ColumnInfo(name = "rating")
    val rating: Int = 0,

    @ColumnInfo(name = "title")
    @Json(name = "title")
    val title: String = ""
) : Serializable