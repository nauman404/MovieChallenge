package com.nauman404.data.local

import androidx.paging.DataSource
import androidx.room.*
import com.nauman404.data.local.models.Movie

@Dao
@TypeConverters(TypeConverter::class)
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<Movie>)

    @Query("SELECT * FROM movies ORDER BY id ASC")
    fun getAllMovies(): List<Movie>

    @Query("SELECT * FROM movies ORDER BY id ASC")
    fun moviesDataSource(): DataSource.Factory<Int, Movie>

    @Query("SELECT * FROM movies WHERE title LIKE '%' || :title || '%' ORDER BY year DESC, rating DESC")
    fun moviesByTitle(title: String): List<Movie>
}