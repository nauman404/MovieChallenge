package com.nauman404.data.repositories

import androidx.paging.DataSource
import com.nauman404.data.local.MoviesDao
import com.nauman404.data.local.models.Movie
import com.nauman404.data.remote.ApiService
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

}