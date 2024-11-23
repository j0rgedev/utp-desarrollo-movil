package com.example.pc2.ui.views

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.pc2.R
import com.example.pc2.ui.viewmodel.ClientViewModel
import kotlinx.coroutines.launch

data class PhoneOperator(val id: Int, val name: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddClientView(navController: NavController, viewModel: ClientViewModel) {

    val phoneOperatorList = listOf(
        PhoneOperator(1, "Claro"),
        PhoneOperator(2, "Movistar"),
        PhoneOperator(3, "Entel"),
        PhoneOperator(4, "Bitel")
    )

    val selectedPhoneOperator = remember { mutableStateOf<PhoneOperator?>(null) }
    val phoneOperatorExpanded = remember { mutableStateOf(false) }
    val showAlert = remember { mutableStateOf(false) }
    val alertMessage = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Añadir Cliente",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(vertical = 8.dp, horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = viewModel.client.dni,
                onValueChange = { newValue ->
                    viewModel.updateClientDni(newValue)
                },
                label = { Text("Ingresar DNI") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )

            OutlinedTextField(
                value = viewModel.client.names,
                onValueChange = { newValue ->
                    viewModel.updateClientNames(newValue)
                },
                label = { Text("Ingresar Nombres") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )

            OutlinedTextField(
                value = viewModel.client.lastNames,
                onValueChange = { newValue ->
                    viewModel.updateClientLastNames(newValue)
                },
                label = { Text("Ingresar Apellidos") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 22.dp)
            ) {
                ExposedDropdownMenuBox(
                    expanded = phoneOperatorExpanded.value,
                    onExpandedChange = {
                        phoneOperatorExpanded.value = !phoneOperatorExpanded.value
                    }
                ) {
                    OutlinedTextField(
                        value = selectedPhoneOperator.value?.name ?: "Seleccionar Operadora",
                        onValueChange = {},
                        readOnly = true,
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_search),
                                contentDescription = "Arrow Back",
                                tint = Color.Gray
                            )
                        },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = phoneOperatorExpanded.value) },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                    )

                    ExposedDropdownMenu(
                        modifier = Modifier.fillMaxWidth(),
                        expanded = phoneOperatorExpanded.value,
                        onDismissRequest = { phoneOperatorExpanded.value = false }
                    ) {
                        phoneOperatorList.forEach { phoneOperator ->
                            DropdownMenuItem(
                                text = { Text(text = phoneOperator.name) },
                                onClick = {
                                    selectedPhoneOperator.value = phoneOperator
                                    phoneOperatorExpanded.value = false
                                    viewModel.updateClientPhoneOperator(phoneOperator.name)
                                    if (phoneOperator.name == "Claro") {
                                        viewModel.updatePhoto(R.drawable.claro)
                                    } else if (phoneOperator.name == "Movistar") {
                                        viewModel.updatePhoto(R.drawable.movistar)
                                    } else if (phoneOperator.name == "Entel") {
                                        viewModel.updatePhoto(R.drawable.entel)
                                    } else if (phoneOperator.name == "Bitel") {
                                        viewModel.updatePhoto(R.drawable.bitel)
                                    }
                                }
                            )
                        }
                    }
                }
            }

            OutlinedTextField(
                value = viewModel.client.phoneNumber,
                onValueChange = { newValue ->
                    viewModel.updateClientPhoneNumber(newValue)
                },
                label = { Text("Ingresar Número Celular") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )

            Button(
                onClick = {
                    if (viewModel.client.dni.isEmpty() ||
                        viewModel.client.names.isEmpty() ||
                        viewModel.client.lastNames.isEmpty() ||
                        viewModel.client.phoneOperator.isEmpty() ||
                        viewModel.client.phoneNumber.isEmpty()) {
                        alertMessage.value = "Por favor, complete todos los campos"
                        showAlert.value = true
                    } else {
                        viewModel.existsClient(viewModel.client.dni) { exists ->
                            if (exists) {
                                alertMessage.value = "El cliente ya existe"
                                showAlert.value = true
                            } else {
                                viewModel.add()
                                navController.popBackStack()
                            }
                        }
                    }
                },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Guardar")
            }
        }
    }

    if (showAlert.value) {
        AlertDialog(
            onDismissRequest = { showAlert.value = false },
            title = {
                Text(text = "Error", fontWeight = FontWeight.Bold)
            },
            text = {
                Text(text = alertMessage.value)
            },
            confirmButton = {
                Button(onClick = { showAlert.value = false }) {
                    Text("OK")
                }
            }
        )
    }
}