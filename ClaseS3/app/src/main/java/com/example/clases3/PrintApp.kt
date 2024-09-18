package com.example.clases3

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.clases3.ui.theme.ClaseS3Theme

@Composable
fun PrintApp(serviceDetails: ServiceDetails) {
    val context = LocalContext.current

    ClaseS3Theme {
        Scaffold(
            topBar = {
                TopBar(
                    title = "Registro de Servicios",
                    navigateBack = {
                        context.startActivity(Intent(context, MainActivity::class.java))
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(vertical = 8.dp, horizontal = 16.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                Row {
                    Text("Cliente: ")
                }
                Row {
                    Text(serviceDetails.clientNames, modifier = Modifier.padding(start = 8.dp))
                }

                Row {
                    Text("Dni: ")
                }
                Row {
                    Text(serviceDetails.clientDni, modifier = Modifier.padding(start = 8.dp))
                }

                Row {
                    Text("Servicio: ")
                }
                Row {
                    Text(serviceDetails.serviceDescription, modifier = Modifier.padding(start = 8.dp))
                }

                Row {
                    Text("Costo Servicio: ")
                }
                Row {
                    Text(serviceDetails.servicePrice.toString(), modifier = Modifier.padding(start = 8.dp))
                }

                Row {
                    Text("Costo Instalación: ")
                }
                Row {
                    Text(serviceDetails.installationPrice.toString(), modifier = Modifier.padding(start = 8.dp))
                }

                Row {
                    Text("Descuento: ")
                }
                Row {
                    Text(serviceDetails.discountAmount.toString(), modifier = Modifier.padding(start = 8.dp))
                }

                Row {
                    Text("Total a Pagar: ")
                }
                Row {
                    Text(serviceDetails.totalPrice.toString(), modifier = Modifier.padding(start = 8.dp))
                }
            }
        }
    }
}