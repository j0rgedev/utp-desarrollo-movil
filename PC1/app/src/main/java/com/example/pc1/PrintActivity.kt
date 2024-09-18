package com.example.pc1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

class PrintActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val enrollmentDetails = EnrollmentDetails(
            studentNames = intent.getStringExtra("studentNames") ?: "",
            dni = intent.getStringExtra("dni") ?: "",
            major = intent.getStringExtra("major"),
            majorPrice = intent.getDoubleExtra("majorPrice", 0.0),
            firstAdditionalService = intent.getStringExtra("firstAdditionalService") ?: "",
            firstAdditionalServicePrice = intent.getDoubleExtra("firstAdditionalServicePrice", 0.0),
            secondAdditionalService = intent.getStringExtra("secondAdditionalService") ?: "",
            secondAdditionalServicePrice = intent.getDoubleExtra("secondAdditionalServicePrice", 0.0),
            additionalPrice = intent.getDoubleExtra("additionalPrice", 0.0),
            totalPrice = intent.getDoubleExtra("totalPrice", 0.0)
        )

        setContent { PrintApp(enrollmentDetails) }
    }
}

data class EnrollmentDetails (
    val studentNames: String,
    val dni: String,
    val major: String?,
    val majorPrice: Double?,
    val firstAdditionalService: String,
    val firstAdditionalServicePrice: Double,
    val secondAdditionalService: String,
    val secondAdditionalServicePrice: Double,
    val additionalPrice: Double,
    val totalPrice: Double
)