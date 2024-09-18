package com.example.clases4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

class CinemaDetailsActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val cinemaDetails = CinemaDetails(
            clientNames = intent.getStringExtra("clientNames") ?: "",
            genreName = intent.getStringExtra("genreName") ?: "",
            movieTitle = intent.getStringExtra("movieTitle") ?: "",
            moviePhoto = intent.getIntExtra("moviePhoto", 0),
            adultSeatsCount = intent.getIntExtra("adultSeatsCount", 0),
            kidsSeatsCount = intent.getIntExtra("kidsSeatsCount", 0),
            kidsSeatsPrice = intent.getDoubleExtra("kidsSeatsPrice", 0.0),
            adultSeatsPrice = intent.getDoubleExtra("adultSeatsPrice", 0.0),
            totalFinalPrice = intent.getDoubleExtra("totalFinalPrice", 0.0)
        )

        setContent { CinemaDetailsApp(cinemaDetails) }
    }
}

data class CinemaDetails(
    val clientNames : String,
    val genreName : String,
    val movieTitle : String,
    val moviePhoto : Int,
    val adultSeatsCount : Int,
    val kidsSeatsCount : Int,
    val kidsSeatsPrice : Double,
    val adultSeatsPrice : Double,
    val totalFinalPrice : Double
)