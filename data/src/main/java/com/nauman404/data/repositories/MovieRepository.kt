package com.nauman404.data.repositories

import androidx.paging.DataSource
import com.nauman404.data.local.MoviesDao
import com.nauman404.data.local.models.ImagesWrapper
import com.nauman404.data.local.models.Movie
import com.nauman404.data.remote.ApiService
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor (
    private val apiService: ApiService,
    private val moviesDao: MoviesDao
){

    fun insertMovies(list: List<Movie>){
        moviesDao.insertAll(list)
    }

    fun moviesDataSource(): DataSource.Factory<Int, Any> {
        return moviesDao.moviesDataSource().map {
            it as Any
        }
    }

    fun moviesByTitle(title: String): List<Any> {
        return moviesDao.moviesByTitle(title)
            .groupBy {
                it.year
            }.flatMap {
                //To get top 5 of each category
                var list = it.value.toMutableList<Any>()
                if (list.size > 5)
                    list = list.subList(0, 5)
                // append the year value
                list.add(0, it.key)
                list
            }
    }

    suspend fun getImagesRequest(apiKey: String, title: String, page:Int, perPage: Int): Response<ImagesWrapper> {
        return apiService.getImages(apiKey =  apiKey, format = "json", callback =  1, title = title, page = page, perPage = perPage)
    }

}