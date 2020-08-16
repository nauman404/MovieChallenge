package com.nauman404.moviechallenge.ui.movies


import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.nauman404.core.utils.MovieUtils
import com.nauman404.data.local.models.State
import com.nauman404.moviechallenge.R
import com.nauman404.moviechallenge.databinding.FragmentMoviesBinding
import com.nauman404.moviechallenge.ui.base.BaseFragment
import kotlinx.android.synthetic.main.progressbar.view.*
import timber.log.Timber


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MovieFragment : BaseFragment<FragmentMoviesBinding>(
    R.layout.fragment_movies) {

    private val viewModel: MovieViewModel by viewModels { viewModelProvider }


    override fun onInitDataBinding() {

        loadDataProcess()
        initObservers()

    }

    private fun loadDataProcess() {
        if (!MovieUtils.isMoviesSavedLocal(requireContext())) {
            parseData()
        }
    }

    private fun parseData() {
        viewModel.parseAndSaveMovies(requireActivity().assets.open("movies.json"))
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading)
            binding.root.progressbar.visibility = View.VISIBLE
        else
            binding.root.progressbar.visibility = View.GONE
    }

    private fun initObservers() {
        viewModel.moviesLiveData.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is State.Loading -> showLoading(true)
                is State.Success -> {
                    MovieUtils.saveMoviesLocal(requireActivity().applicationContext)
                    showLoading(false)
                }
                is State.Error -> {
                    showLoading(false)
                }
            }
        })

    }
}