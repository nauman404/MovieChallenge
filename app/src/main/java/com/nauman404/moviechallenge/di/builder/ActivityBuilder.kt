package com.nauman404.moviechallenge.di.builder;

import com.nauman404.moviechallenge.ui.movies.MovieActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindMovieActivity(): MovieActivity

}
