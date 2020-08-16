package com.nauman404.moviechallenge.di.module

import android.app.Application
import com.nauman404.data.local.MoviesDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application) = MoviesDatabase.getInstance(application)

    @Singleton
    @Provides
    fun provideMoviesDao(database: MoviesDatabase) = database.moviesDao()

}