package com.nauman404.moviechallenge.ui.movies


import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
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

    lateinit var moviesAdapter: MoviesAdapter
    lateinit var moviesSearchAdapter: MoviesAdapter


    override fun onInitDataBinding() {

        binding.movieSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                Timber.e(query)
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if(query.isNullOrEmpty()) loadDataProcess()
                else movieByTitle(query)
                return true
            }
        })

        moviesAdapter = MoviesAdapter()
        moviesSearchAdapter = MoviesAdapter()
        setRecyclerAdapter(false)
        binding.moviesRecycler.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        loadDataProcess()
        initObservers()

    }

    private fun loadDataProcess() {
        if (!MovieUtils.isMoviesSavedLocal(requireContext())) {
            parseData()
        }else{
            setRecyclerAdapter(false)
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

    private fun movieByTitle(title:String){
        viewModel.movieByTitle(title).observe(this, Observer {
            showLoading(false)
            moviesSearchAdapter.submitList(it)
            if(it.size > 0) setRecyclerAdapter(true)
            else showEmptyData()
        })
    }

    private fun showEmptyData() {
        binding.errorText.visibility = View.VISIBLE
        binding.root.progressbar.visibility = View.GONE
        binding.moviesRecycler.visibility = View.GONE
    }

    private fun initObservers() {
        viewModel.moviesLiveData.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is State.Loading -> showLoading(true)
                is State.Success -> {
                    binding.moviesRecycler.visibility = View.VISIBLE
                    MovieUtils.saveMoviesLocal(requireActivity().applicationContext)
                    showLoading(false)
                }
                is State.Error -> {
                    showLoading(false)
                }
            }
        })

        viewModel.movieList().observe(this, Observer {
            showLoading(false)
            moviesAdapter.submitList(it)
            setRecyclerAdapter(false)
        })

    }

    private fun setRecyclerAdapter(isSearch: Boolean){
        if(isSearch) binding.moviesRecycler.adapter = moviesSearchAdapter
        else binding.moviesRecycler.adapter = moviesAdapter

        binding.errorText.visibility = View.GONE
        binding.root.progressbar.visibility = View.GONE
        binding.moviesRecycler.visibility = View.VISIBLE

    }
}