package com.example.clases4

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CinemaDetailsApp(
    cinemaDetails: CinemaDetails
) {
    val context = LocalContext.current

    Scaffold (
        topBar = {
            TopBar(
                title = "KVBE Cine",
                navigateBack = {
                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(vertical = 8.dp, horizontal  = 16.dp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Detalles de la Compra",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp)
            )

            Text(
                text = "Cliente: ${cinemaDetails.clientNames}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(top = 16.dp)
            )

            Text(
                text = "Género: ${cinemaDetails.genreName}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(top = 16.dp)
            )

            Text(
                text = "Película: ${cinemaDetails.movieTitle}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(top = 16.dp)
            )

            Text(
                text = "Foto de la película",
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(top = 16.dp)
            )

            Image(
                painter = painterResource(id = cinemaDetails.moviePhoto),
                contentDescription = "Foto de la película",
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Text(
                text = "Adultos: ${cinemaDetails.adultSeatsCount}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(top = 16.dp)
            )

            Text(
                text = "Niños: ${cinemaDetails.kidsSeatsCount}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(top = 16.dp)
            )

            Text(
                text = "Precio por Niño: ${cinemaDetails.kidsSeatsPrice}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(top = 16.dp)
            )

            Text(
                text = "Precio por Adulto: ${cinemaDetails.adultSeatsPrice}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(top = 16.dp)
            )

            Text(
                text = "Precio Total: ${cinemaDetails.totalFinalPrice}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}