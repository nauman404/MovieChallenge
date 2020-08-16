package com.nauman404.moviechallenge.di.component

import android.app.Application
import com.nauman404.moviechallenge.MovieApp
import com.nauman404.moviechallenge.di.builder.ActivityBuilder
import com.nauman404.moviechallenge.di.builder.FragmentBuilder
import com.nauman404.moviechallenge.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton


@Singleton
@Component(
        modules = [
            AppModule::class,
            AndroidInjectionModule::class,
            DatabaseModule::class,
            ApiModule::class,
            ActivityBuilder::class,
            FragmentBuilder::class,
            ViewModelFactoryModule::class,
            ViewModelModule::class
        ]
)
interface AppComponent : AndroidInjector<MovieApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun create(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(app: MovieApp)
}