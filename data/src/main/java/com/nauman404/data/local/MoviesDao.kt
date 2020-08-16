package com.nauman404.data.local

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
    fun allMovies(): List<Movie>
}