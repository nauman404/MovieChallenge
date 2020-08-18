package com.nauman404.moviechallenge

import androidx.multidex.MultiDexApplication
import com.nauman404.moviechallenge.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import timber.log.Timber
import javax.inject.Inject

class MovieApp : MultiDexApplication(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        // Initialize Dependency Injection
        DaggerAppComponent.builder()
            .create(this)
            .build()
            .inject(this)

        if (BuildConfig.DEBUG) Timber.plant(MyDebugTree())

    }

    /**
     * I am using Timber for logging.
     * I customized to improving readability
     * LogCat shows line, method name and class name
     */
    inner class MyDebugTree : Timber.DebugTree() {
        override fun createStackElementTag(element: StackTraceElement): String? = String.format(
                "[Line:%s] [Method:%s] [Class:%s]",
                element.lineNumber,
                element.methodName,
                super.createStackElementTag(element)
        )
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}