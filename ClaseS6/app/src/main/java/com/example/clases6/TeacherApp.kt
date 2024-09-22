package com.example.clases6

import android.widget.Space
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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.CheckCircle
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
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clases6.ui.theme.ClaseS6Theme

data class Sex(val id: Int, val name: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeacherApp() {
    val names = remember { mutableStateOf("") }
    val fatherLastName = remember { mutableStateOf("") }
    val motherLastName = remember { mutableStateOf("") }
    val income = remember { mutableDoubleStateOf(0.0) }
    val kidsCount = remember { mutableIntStateOf(0) }
    val sexList = remember {
        mutableStateOf(
            listOf(
                Sex(1, "Masculino"),
                Sex(2, "Femenino"),
            )
        )
    }
    val selectedSex = remember { mutableStateOf<Sex?>(null) }
    val sexDropDownExpanded = remember { mutableStateOf(false) }

    val openAlertDialog = remember { mutableStateOf(false) }
    if (openAlertDialog.value) {
        AlertDialog(
            icon = {
                Icon(
                    Icons.Rounded.AccountBox,
                    contentDescription = "Check Circle",
                )
            },
            title = {
                Text(text = "Datos Docente")
            },
            text = {
                Column(
                    modifier = Modifier.padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("Nombre: ${names.value}")
                    Text("Apellido Paterno: ${fatherLastName.value}")
                    Text("Apellido Materno: ${motherLastName.value}")
                    Text("Ingreso: S/.${income.doubleValue}")
                    Text("Cantidad de Hijos: ${kidsCount.intValue}")
                    Text("Sexo: ${selectedSex.value?.name ?: "No seleccionado"}")
                }
            },
            onDismissRequest = { openAlertDialog.value = false },
            confirmButton = {},
            dismissButton = {}
        )
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "KBVE",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                ),
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
                text = "DOCENTE",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )

            OutlinedTextField(
                value = names.value,
                onValueChange = { names.value = it },
                label = { Text("Ingresar Nombre") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Arrow Back",
                        tint = Color.Gray
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )

            OutlinedTextField(
                value = fatherLastName.value,
                onValueChange = { fatherLastName.value = it },
                label = { Text("Ingresar Apellido Paterno") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Arrow Back",
                        tint = Color.Gray
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )

            OutlinedTextField(
                value = motherLastName.value,
                onValueChange = { motherLastName.value = it },
                label = { Text("Ingresar Apellido Materno") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Arrow Back",
                        tint = Color.Gray
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )

            Row(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                OutlinedTextField(
                    value = income.doubleValue.toString(),
                    onValueChange = { value ->
                        if (value.isEmpty()) {
                            income.doubleValue = 0.0
                        } else if (value.all { it.isDigit() || it == '.' }) {
                            income.doubleValue = value.toDouble()
                        }
                    },
                    label = { Text("Ingresar Ingreso") },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_money),
                            contentDescription = "Arrow Back",
                            tint = Color.Gray
                        )
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier
                        .weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                OutlinedTextField(
                    value = kidsCount.intValue.toString(),
                    onValueChange = { value ->
                        if (value.isEmpty()) {
                            kidsCount.intValue = 0
                        } else if (value.all { it.isDigit() }) {
                            kidsCount.intValue = value.toInt()
                        }
                    },
                    label = { Text("Ingresar Hijos") },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_count),
                            contentDescription = "Arrow Back",
                            tint = Color.Gray
                        )
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier
                        .weight(1f)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 22.dp)
            ) {
                ExposedDropdownMenuBox(
                    expanded = sexDropDownExpanded.value,
                    onExpandedChange = {
                        sexDropDownExpanded.value = !sexDropDownExpanded.value
                    }
                ) {
                    OutlinedTextField(
                        value = selectedSex.value?.name ?: "Seleccionar Sexo",
                        onValueChange = {},
                        readOnly = true,
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_search),
                                contentDescription = "Arrow Back",
                                tint = Color.Gray
                            )
                        },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = sexDropDownExpanded.value) },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                    )

                    ExposedDropdownMenu(
                        modifier = Modifier.fillMaxWidth(),
                        expanded = sexDropDownExpanded.value,
                        onDismissRequest = { sexDropDownExpanded.value = false }
                    ) {
                        sexList.value.forEach { sex ->
                            DropdownMenuItem(
                                text = { Text(text = sex.name) },
                                onClick = {
                                    selectedSex.value = sex
                                    sexDropDownExpanded.value = false
                                }
                            )
                        }
                    }
                }
            }

            Row(
                modifier = Modifier
                    .padding(top = 32.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Button(
                    onClick = {
                        openAlertDialog.value = true
                    },
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_save),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(text = "Guardar")
                    }
                }

                Button(
                    onClick = {},
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            Icons.Filled.Close,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(text = "Cerrar")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun TeacherAppPreview() {
    ClaseS6Theme { TeacherApp() }
}