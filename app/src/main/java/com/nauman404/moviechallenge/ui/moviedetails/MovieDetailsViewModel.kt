package com.nauman404.moviechallenge.ui.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nauman404.data.local.models.State
import com.nauman404.data.repositories.MovieRepository
import com.nauman404.moviechallenge.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for [MovieDetailFragment]
 */
class MovieDetailsViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {

    private val _imagesUiLiveData = MutableLiveData<State<String>>()

    val imagesUiLiveData: LiveData<State<String>>
        get() = _imagesUiLiveData

    private val _imagesLiveData = MutableLiveData<List<String>>()

    val imagesLiveData: LiveData<List<String>>
        get() = _imagesLiveData

    fun getImagesRequest(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _imagesUiLiveData.postValue(State.loading())
            try {
                val result = movieRepository.getImagesRequest(BuildConfig.API_KEY, title,1,25)
                val apiResponse = result.body()
                if (apiResponse != null) {
                    if(apiResponse.images.images.count() > 0){
                        _imagesLiveData.postValue(State.success(apiResponse.images.images).data.map { it.mapPhotoUrl() })
                    }else {
                        _imagesUiLiveData.postValue(State.error("No Photo Data Found!"))
                    }
                }
            } catch (e: Exception) {
                _imagesUiLiveData.postValue(State.error(e.message.toString()))
            }
        }
    }
}
