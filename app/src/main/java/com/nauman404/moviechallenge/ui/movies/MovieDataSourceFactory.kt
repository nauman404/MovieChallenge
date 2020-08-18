package com.nauman404.moviechallenge.ui.movies

import androidx.paging.DataSource
import com.nauman404.data.repositories.MovieRepository
import javax.inject.Inject

class MovieDataSourceFactory @Inject constructor(
    private var moviesRepository: MovieRepository,
    private var searchQuery:String
) :
    DataSource.Factory<Int, Any>() {
    override fun create(): DataSource<Int, Any> {
        return MovieDataSource(moviesRepository, searchQuery)
    }

}