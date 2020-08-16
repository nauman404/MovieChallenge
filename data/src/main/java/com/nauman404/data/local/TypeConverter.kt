package com.nauman404.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson

class TypeConverter {

    @TypeConverter
    fun listToJson(value: List<String>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun stringToList(value: String): List<String> {
        return Gson().fromJson(value, Array<String>::class.java).toList()
    }
}