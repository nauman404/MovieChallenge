package com.nauman404.data.local.models

import com.squareup.moshi.Json

data class Images(
    @Json(name = "photo")
    val images: List<Image>
)