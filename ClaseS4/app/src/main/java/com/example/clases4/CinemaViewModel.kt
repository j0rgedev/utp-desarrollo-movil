package com.example.tareas3

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.clases4.R

class CinemaViewModel : ViewModel() {

    val KIDS_PRICE_DISCOUNT = 10.0

    var clientNames by mutableStateOf("")
        private set

    var genreList by mutableStateOf<List<Genre>>(listOf())
        private set

    var movieList by mutableStateOf<List<Movie>>(listOf())
        private set

    var filteredMovies by mutableStateOf<List<Movie>>(listOf())
        private set

    var selectedGenre by mutableStateOf<Genre?>(null)
        private set

    var selectedMovie by mutableStateOf<Movie?>(null)
        private set

    var adultSeatsCount by mutableIntStateOf(0)
        private set

    var kidsSeatsCount by mutableIntStateOf(0)
        private set

    var movieDropDownExpanded by mutableStateOf(false)
        private set

    var kidsSeatsPrice by mutableDoubleStateOf(0.0)
        private set

    var adultSeatsPrice by mutableDoubleStateOf(0.0)
        private set

    var totalFinalPrice by mutableDoubleStateOf(0.0)
        private set

    init {
        genreList = listOf(
            Genre(1, "Comedia"),
            Genre(2, "Dramática"),
        )

        movieList = listOf(
            Movie(1, "Super Cool", 35.50, R.drawable.p00, Genre(1, "Comedia")),
            Movie(2, "¿Qué paso ayer?", 32.50, R.drawable.p01, Genre(1, "Comedia")),
            Movie(3, "Lo imposible", 30.50, R.drawable.p10, Genre(2, "Dramática")),
            Movie(4, "12 años de esclavitud", 28.30, R.drawable.p11, Genre(2, "Dramática")),
            Movie(5, "Historias cruzadas", 25.50, R.drawable.p12, Genre(2, "Dramática")),
        )
    }

    fun onClientNameChange(newClientName: String) {
        clientNames = newClientName
    }

    fun onGenreSelected(genre: Genre) {
        selectedGenre = genre
        filteredMovies = movieList.filter { it.genre.id == genre.id }
    }

    fun onMovieSelected(movie: Movie) {
        selectedMovie = movie
    }

    fun onAdultSeatsSelected(adultSeats: Int) {
        adultSeatsCount = adultSeats
    }

    fun onKidSeatsSelected(kidSeats: Int) {
        kidsSeatsCount = kidSeats
    }

    fun onMovieDropDownExpand() {
        movieDropDownExpanded = !movieDropDownExpanded
    }

    fun calculateTotalPrice(): Double {
        adultSeatsPrice = selectedMovie?.price?.times(adultSeatsCount) ?: 0.0
        kidsSeatsPrice = selectedMovie?.price?.minus(KIDS_PRICE_DISCOUNT)?.times(kidsSeatsCount) ?: 0.0
        totalFinalPrice = adultSeatsPrice + kidsSeatsPrice
        return totalFinalPrice
    }
}


data class Genre(
    val id: Int,
    val name: String
)

data class Movie(
    val id: Int,
    val title: String,
    val price: Double,
    val photoResId: Int,
    val genre: Genre
)