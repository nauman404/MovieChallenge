package com.nauman404.data

import androidx.room.Room
import androidx.room.paging.LimitOffsetDataSource
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nauman404.data.local.MoviesDatabase
import com.nauman404.moviechallenge.TestData
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MoviesDaoTest {

    private lateinit var moviesDatabase: MoviesDatabase

    @Before
    fun init() {
        moviesDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MoviesDatabase::class.java
        ).build()
    }

    @Test
    @Throws(InterruptedException::class)
    fun test_InsertAndSelectMovies()  {

        moviesDatabase.moviesDao().insertAll(TestData.getTestMovies())
        val dbMovies = moviesDatabase.moviesDao().getAllMovies()

        MatcherAssert.assertThat(dbMovies, CoreMatchers.equalTo(TestData.getTestMovies()))
    }

    @Test
    @Throws(InterruptedException::class)
    fun test_MoviesByTitle() {

        moviesDatabase.moviesDao().insertAll(TestData.getTestMovies())

        val dbMovies = moviesDatabase.moviesDao().moviesByTitle("summer")

        MatcherAssert.assertThat(dbMovies[0], CoreMatchers.equalTo(TestData.getTestMovies()[0]))

    }

    @After
    fun cleanup() {
        moviesDatabase.close()
    }
}
