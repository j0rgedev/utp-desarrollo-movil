package com.example.clases3

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clases3.ui.theme.ClaseS3Theme

@Preview
@Composable
fun ServiceApp(viewModel: ServiceViewModel = ServiceViewModel()) {
    val context = LocalContext.current

    ClaseS3Theme {
        Scaffold(
            topBar = {
                TopBar(
                    title = "Registro de Servicios",
                    navigateBack = null
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
                Text(
                    text = "Datos del Cliente",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )

                OutlinedTextField(
                    value = viewModel.clientNames,
                    onValueChange = { viewModel.onClientNamesChange(it) },
                    label = { Text("Nombres Completos") },
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth()
                )

                OutlinedTextField(
                    value = viewModel.clientDni,
                    onValueChange = { viewModel.onClientDniChange(it) },
                    label = { Text("DNI") },
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                )

                Text(
                    text = "Servicios",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(top = 16.dp)
                )

                Column(Modifier.selectableGroup()) {
                    viewModel.serviceList.forEach { service ->
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .height(70.dp)
                                .selectable(
                                    selected = (viewModel.selectedService == service),
                                    onClick = { viewModel.onServiceSelected(service) },
                                    role = Role.RadioButton
                                ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (viewModel.selectedService == service),
                                onClick = null
                            )
                            Text(
                                text = service.description,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(start = 16.dp)
                            )
                        }
                    }
                }

                Text(
                    text = "Costo del Servicio",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(top = 16.dp)
                )
                Text(
                    "S/. ${viewModel.selectedService?.price ?: 0.0}",
                )

                Text(
                    text = "Costo de Instalación",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(top = 12.dp)
                )
                Text(
                    "S/. ${viewModel.selectedService?.installationPrice ?: 0.0}",
                )

                Text(
                    text = "Descuento",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(top = 12.dp)
                )
                Text(
                    "S/. ${viewModel.discountPrice}",
                )

                Text(
                    text = "Total a Pagar",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(top = 12.dp)

                )
                Text(
                    "S/. ${viewModel.totalPrice}",
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { viewModel.calculateTotalPrice() },
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text("Calcular")
                    }
                    Button(
                        onClick = {
                            val intent = Intent(context, PrintActivity::class.java).apply {
                                putExtra("clientNames", viewModel.clientNames)
                                putExtra("clientDni", viewModel.clientDni)
                                putExtra(
                                    "serviceDescription",
                                    viewModel.selectedService?.description
                                )
                                putExtra("servicePrice", viewModel.selectedService?.price)
                                putExtra(
                                    "installationPrice",
                                    viewModel.selectedService?.installationPrice
                                )
                                putExtra("discountAmount", viewModel.discountPrice)
                                putExtra("totalPrice", viewModel.totalPrice)
                            }
                            context.startActivity(intent)
                        },
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text("Imprimir")
                    }
                }
            }
        }
    }
}