package com.nauman404.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nauman404.data.local.models.Movie

@Database(
    entities = [Movie::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(TypeConverter::class)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

    companion object {

        fun getInstance(context: Context): MoviesDatabase =
            Room.databaseBuilder(context, MoviesDatabase::class.java, "movies.db")
                .build()

    }
}
