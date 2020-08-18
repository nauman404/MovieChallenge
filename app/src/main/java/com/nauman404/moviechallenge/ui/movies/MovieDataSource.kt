package com.nauman404.moviechallenge.ui.movies

import androidx.paging.PageKeyedDataSource
import com.nauman404.data.repositories.MovieRepository

class MovieDataSource(
    var movieRepository: MovieRepository,
    var searchQuery: String
) :
    PageKeyedDataSource<Int, Any>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Any>
    ) { callback.onResult(movieRepository.moviesByTitle(searchQuery), null, 1) }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Any>) {
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Any>) {
    }

}