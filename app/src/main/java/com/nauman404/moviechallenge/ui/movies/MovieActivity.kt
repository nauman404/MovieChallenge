package com.nauman404.moviechallenge.ui.movies

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.nauman404.core.utils.viewModelOf
import com.nauman404.moviechallenge.R
import com.nauman404.moviechallenge.databinding.ActivityMovieBinding
import com.nauman404.moviechallenge.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_movie.*

class MovieActivity : BaseActivity<MovieViewModel, ActivityMovieBinding>(
    R.layout.activity_movie
) {

    private lateinit var navController: NavController

    override fun getViewModel() = viewModelOf<MovieViewModel>(viewModelProvider)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navController = findNavController(R.id.nav_host_fragment)
        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}
