package com.nauman404.moviechallenge
import com.nauman404.data.local.models.Image
import com.nauman404.data.local.models.Images
import com.nauman404.data.local.models.ImagesWrapper
import com.nauman404.data.local.models.Movie

object TestData {

    fun getTestCast(number: Int): List<String> {
        when (number) {
            1 -> return listOf(  "Joseph Gordon-Levitt","Zooey Deschanel")
            2 -> return listOf("John Cena","Ashley Scott","Steve Harris","Aidan Gillen","Brian J. White","Taylor Cole")
            3 -> return listOf( "Zac Efron","Leslie Mann","Thomas Lennon","Matthew Perry","Melora Hardin","Michelle Trachtenberg","Sterling Knight")
            else -> return listOf("John Cusack","Amanda Peet","Danny Glover","Thandie Newton","Oliver Platt","Chiwetel Ejiofor","Woody Harrelson")
        }
    }

    fun getTestGenres(number: Int): List<String> {
        return when (number) {
            1 -> listOf("Romance","Comedy")
            2 -> listOf("Action")
            3 -> listOf( "Comedy","Teen")
            else -> listOf("Disaster")
        }
    }

    fun getTestMovies(): List<Movie> = listOf(
        Movie(1, getTestCast(1),2009,getTestGenres(1),1,"500 Days of summer"),
        Movie(2, getTestCast(2),2009,getTestGenres(2),1,"12 Rounds"),
        Movie(3, getTestCast(3),2009,getTestGenres(3),1,"17 Again"),
        Movie(4, getTestCast(4),2009,getTestGenres(4),1,"2012")
    )

    fun getTestImages(): ImagesWrapper {
        val imageList = listOf(
            Image("35835471@N00","65535",1,0,0,"50238036981"),
            Image("35835471@N00","65535",1,0,0,"50238036981"),
            Image("35835471@N00","65535",1,0,0,"50238036981"),
            Image("35835471@N00","65535",1,0,0,"50238036981"))
        val images = Images(imageList)
        return ImagesWrapper(images)
    }

}