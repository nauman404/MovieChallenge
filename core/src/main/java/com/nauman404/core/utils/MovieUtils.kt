package com.nauman404.core.utils

import android.content.Context
import android.preference.PreferenceManager


object MovieUtils {

    private const val MOVIES_SAVED_LOCAL = "movies_saved_local"

    fun saveMoviesLocal(context: Context) {
        val sp = PreferenceManager.getDefaultSharedPreferences(context)
        sp.edit().putBoolean(MOVIES_SAVED_LOCAL, true).apply()
    }

    fun isMoviesSavedLocal(context: Context): Boolean {
        val sp = PreferenceManager.getDefaultSharedPreferences(context)
        return sp.getBoolean(MOVIES_SAVED_LOCAL, false)
    }

}
