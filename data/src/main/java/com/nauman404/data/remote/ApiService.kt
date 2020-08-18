package com.nauman404.data.remote

import com.nauman404.data.local.models.ImagesWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Service to fetch Data using end point [API_URL].
 */
interface ApiService {

    @GET("services/rest/")
    suspend fun getImages(
        @Query("method") method: String = "flickr.photos.search",
        @Query("api_key") apiKey: String,
        @Query("format") format: String,
        @Query("nojsoncallback") callback: Int,
        @Query("text") title: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<ImagesWrapper>

}