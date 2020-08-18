package com.nauman404.moviechallenge.ui.moviedetails

import androidx.fragment.app.Fragment
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.nauman404.data.local.models.Movie
import com.nauman404.data.local.models.State
import com.nauman404.moviechallenge.R
import com.nauman404.moviechallenge.databinding.FragmentMovieDetailsBinding
import com.nauman404.moviechallenge.ui.base.BaseFragment
import kotlinx.android.synthetic.main.activity_movie.*
import kotlinx.android.synthetic.main.fragment_movie_details.view.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MovieDetailFragment : BaseFragment<FragmentMovieDetailsBinding>(
    R.layout.fragment_movie_details) {

    private val viewModel: MovieDetailsViewModel by viewModels { viewModelProvider }
    private lateinit var movie: Movie // State should be in VM. Later I will change.
    private val imageAdapter = ImageAdapter()

    override fun onInitDataBinding() {
        movie = MovieDetailFragmentArgs.fromBundle(requireArguments()).movie
        requireActivity().toolbar.title = movie.title
        binding.moviesNameText.text = movie.title
        binding.movieYearText.text = movie.year.toString()

        val castStr = StringBuilder()
        for (item in movie.cast) {
            castStr.append(item)
            if (item != movie.cast.last())
                castStr.append(", ")
        }

        binding.movieCastText.text = castStr.toString()

        val genreStr = StringBuilder()
        for (item in movie.genres) {
            genreStr.append(item)
            if (item != movie.genres.last())
                genreStr.append(", ")
        }

        binding.movieGenresText.text = genreStr.toString()
        binding.photosRecycler.adapter = imageAdapter

        initObservers()
        initImagesRequest()

    }

    private fun initImagesRequest() {
        viewModel.getImagesRequest(movie.title)
    }


    private fun showLoading(isLoading: Boolean) {
        if (isLoading)
            binding.root.progressbar.visibility = View.VISIBLE
        else
            binding.root.progressbar.visibility = View.GONE
    }

    private fun initObservers() {
        viewModel.imagesUiLiveData.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is State.Loading -> showLoading(true)
                is State.Success -> {
                    showLoading(false)
                }
                is State.Error -> {
                    showError(state.message)
                    showLoading(false)
                }
            }
        })


        viewModel.imagesLiveData.observe(this, Observer {
            showLoading(false)
            imageAdapter.setImages(it)
        })

    }

    private fun showError(errorText: String) {
        binding.photosRecycler.visibility = View.GONE
        binding.progressbar.visibility = View.GONE
        binding.errorText.text = errorText
        binding.errorText.visibility = View.VISIBLE

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, findNavController())
                || super.onOptionsItemSelected(item)
    }


}
