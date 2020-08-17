package com.nauman404.data.local.models

import com.squareup.moshi.Json

data class ImagesWrapper(
    @Json(name = "photos")
    val images: Images
)