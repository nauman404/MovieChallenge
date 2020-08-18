package com.nauman404.moviechallenge.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nauman404.data.local.models.State
import com.nauman404.data.repositories.MovieRepository
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
class MovieViewModelTest {

    @get:Rule
    var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var movieRepository: MovieRepository

    private lateinit var movieViewModel: MovieViewModel

    @ExperimentalCoroutinesApi
    @Before
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
    fun test_InsertMoviesGetAllMovies() {
        runBlocking {
            val observer = mockk<Observer<State<String>>>(relaxed = true)
            coEvery { movieRepository.insertMovies(TestData.getTestMovies()) } returns Unit
            movieViewModel = MovieViewModel(movieRepository)
            movieViewModel.moviesLiveData.observeForever(observer)
            movieViewModel.insertMovies(TestData.getTestMovies())
            verify { observer.onChanged(match { it == State.success("") }) }
        }
    }

}