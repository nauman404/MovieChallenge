package com.nauman404.moviechallenge.di.builder;

import com.nauman404.moviechallenge.ui.moviedetails.MovieDetailFragment
import com.nauman404.moviechallenge.ui.movies.MovieFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector
    abstract fun moviesFragment(): MovieFragment

    @ContributesAndroidInjector
    abstract fun movieDetailFragment(): MovieDetailFragment

}
