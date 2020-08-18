package com.nauman404.moviechallenge.ui.moviedetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nauman404.data.repositories.MovieRepository
import com.nauman404.moviechallenge.BuildConfig
import com.nauman404.data.TestData
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MovieDetailsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var movieRepository: MovieRepository

    private lateinit var movieDetailsViewModel: MovieDetailsViewModel

    @Before
    @ExperimentalCoroutinesApi
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(TestCoroutineDispatcher())
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun test_getImagesForMoviesObservedResult() {
        runBlocking {
            val observer = mockk<Observer<List<String>>>(relaxed = true)
            coEvery { movieRepository.getImagesRequest(BuildConfig.API_KEY,"500 Days of summer",1,25).body() } returns TestData.getTestImages()
            movieDetailsViewModel = MovieDetailsViewModel(movieRepository)
            movieDetailsViewModel.imagesLiveData.observeForever(observer)
            movieDetailsViewModel.getImagesRequest("500 Days of summer")
            verify { observer.onChanged(match { it.size == 4  })}
        }
    }

}