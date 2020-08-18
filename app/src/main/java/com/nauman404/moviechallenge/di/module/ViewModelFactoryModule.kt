package com.nauman404.moviechallenge.di.module

import androidx.lifecycle.ViewModelProvider
import com.nauman404.moviechallenge.di.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory
}