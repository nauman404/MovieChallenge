package com.nauman404.moviechallenge.ui.movies

import androidx.lifecycle.*
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.nauman404.core.utils.StringUtils
import com.nauman404.data.local.models.Movie
import com.nauman404.data.local.models.MoviesResponse
import com.nauman404.data.local.models.State
import com.nauman404.data.repositories.MovieRepository
import com.nauman404.moviechallenge.BuildConfig
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStream
import javax.inject.Inject

/**
 * ViewModel for [MovieActivity] & [MovieFragment]
 */
class MovieViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {

    private val _moviesUiLiveData = MutableLiveData<State<String>>()

    val moviesLiveData: LiveData<State<String>>
        get() = _moviesUiLiveData

    fun movieList(): LiveData<PagedList<Any>> {
        return movieRepository.moviesDataSource().toLiveData(pageSize = 25)
    }

    fun movieByTitle(title: String): LiveData<PagedList<Any>>{
        val dataSourceFactory = MovieDataSourceFactory(movieRepository, title)
        return dataSourceFactory.toLiveData(
            pageSize = 25 //move to constants later.
        )
    }

    fun parseAndSaveMovies(inputStream: InputStream) {
        viewModelScope.launch(Dispatchers.IO) {
            _moviesUiLiveData.postValue(State.loading())
            try {
                val result = parseMovies(inputStream)
                result?.let {
                    insertMovies(it)
                    _moviesUiLiveData.postValue(State.success(""))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun parseMovies(inputStream: InputStream): List<Movie>? {
        val moviesString = StringUtils.inputStreamToString(inputStream)
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter(MoviesResponse::class.java)
        return adapter.fromJson(moviesString)?.movies
    }

    fun insertMovies(list: List<Movie>) {
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.insertMovies(list)
            _moviesUiLiveData.postValue(State.success(""))
        }
    }

}
