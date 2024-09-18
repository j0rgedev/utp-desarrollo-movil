package com.example.clases3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.clases3.ui.theme.ClaseS3Theme

class PrintActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val serviceDetails = ServiceDetails(
            clientNames = intent.getStringExtra("clientNames") ?: "",
            clientDni = intent.getStringExtra("clientDni") ?: "",
            serviceDescription = intent.getStringExtra("serviceDescription") ?: "",
            servicePrice = intent.getDoubleExtra("servicePrice", 0.0),
            installationPrice = intent.getDoubleExtra("installationPrice", 0.0),
            discountAmount = intent.getDoubleExtra("discountAmount", 0.0),
            totalPrice = intent.getDoubleExtra("totalPrice", 0.0)
        )

        setContent {
            ClaseS3Theme {
                PrintApp(serviceDetails)
            }
        }
    }
}

data class ServiceDetails(
    val clientNames: String,
    val clientDni: String,
    val serviceDescription: String,
    val servicePrice: Double,
    val installationPrice: Double,
    val discountAmount: Double,
    val totalPrice: Double
)