package com.nauman404.core.utils

import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


private const val MOVIES_SAVED_LOCAL = "is_movies_saved_local"

@RunWith(AndroidJUnit4::class)
class MovieUtilsTest {

    private lateinit var sharedPreferences: SharedPreferences

    @Before
    fun setUp() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(appContext)
    }

    @After
    fun tearDown() {
        sharedPreferences.edit().putBoolean(MOVIES_SAVED_LOCAL, false).apply()
    }

    @Test
    fun test_SaveMoviesLocal() {
        val value1 = true
        sharedPreferences.edit().putBoolean(MOVIES_SAVED_LOCAL, value1).apply()
        val value2 = sharedPreferences.getBoolean(MOVIES_SAVED_LOCAL,false)

        // Verify that the received data is correct.
        assertEquals(value1,value2)

    }

}