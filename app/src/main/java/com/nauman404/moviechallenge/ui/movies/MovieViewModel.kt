package com.nauman404.moviechallenge.ui.movies

import androidx.lifecycle.*
import com.nauman404.core.utils.StringUtils
import com.nauman404.data.local.models.Movie
import com.nauman404.data.local.models.MoviesResponse
import com.nauman404.data.local.models.State
import com.nauman404.data.repositories.MovieRepository
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

    private val _moviesLiveData = MutableLiveData<State<List<Movie>>>()

    val moviesLiveData: LiveData<State<List<Movie>>>
        get() = _moviesLiveData

    fun parseAndSaveMovies(inputStream: InputStream) {
        viewModelScope.launch(Dispatchers.IO) {
            _moviesLiveData.postValue(State.loading())
            try {
                val result = parseMovies(inputStream)
                result?.let {
                    insertMovies(it)
                    _moviesLiveData.postValue(State.success(it))
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

    private fun insertMovies(list: List<Movie>) {
        movieRepository.insertMovies(list)
    }

}
