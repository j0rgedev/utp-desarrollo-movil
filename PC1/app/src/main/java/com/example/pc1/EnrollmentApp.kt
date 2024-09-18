package com.example.pc1

import android.content.Intent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pc1.ui.theme.Pc1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun EnrollmentApp(viewModel: EnrollmentViewModel = EnrollmentViewModel()) {
    val context = LocalContext.current

    Pc1Theme {
        Scaffold(
            topBar = {
                TopBar(
                    title = "Bienvenido a tu matrícula",
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
                    text = "Matrícula",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                OutlinedTextField(
                    value = viewModel.dni,
                    onValueChange = { viewModel.onDniChange(it) },
                    label = { Text("DNI") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                )

                OutlinedTextField(
                    value = viewModel.studentNames,
                    onValueChange = { viewModel.onStudentNameChange(it) },
                    label = { Text("Nombres del alumno") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                )

                Text(
                    text = "Carrera",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 16.dp)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    ExposedDropdownMenuBox(
                        expanded = viewModel.majorDropDownExpanded,
                        onExpandedChange = {
                            viewModel.onMajorDropDownExpanded()
                        }
                    ) {
                        OutlinedTextField(
                            value = viewModel.selectedMajor?.name.takeIf { it?.isNotEmpty() ?: false } ?: "Seleccionar",
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = viewModel.majorDropDownExpanded) },
                            modifier = Modifier.menuAnchor().fillMaxWidth()
                        )

                        ExposedDropdownMenu(
                            modifier = Modifier.fillMaxWidth(),
                            expanded = viewModel.majorDropDownExpanded,
                            onDismissRequest = { viewModel.onMajorDropDownExpanded() }
                        ) {
                            viewModel.majorList.forEach { major ->
                                DropdownMenuItem(
                                    text = { Text(text = major.name) },
                                    onClick = {
                                        viewModel.onMajorSelected(major)
                                        viewModel.onMajorDropDownExpanded()
                                    }
                                )
                            }
                        }
                    }
                }

                Text(
                    text = "Gastos Adicionales",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 16.dp)
                )
                viewModel.additionalServiceList.forEach { additionalService ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = viewModel.selectedAdditionalServices.contains(additionalService),
                            onCheckedChange = {
                                viewModel.onAdditionalServiceSelected(additionalService)
                            }
                        )
                        Text(additionalService.name)
                    }
                }

                Button(
                    onClick = { viewModel.getTotal() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Text("Calcular")
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Montos",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(width = 1.dp, color = Color.Black)
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        text = "Pensión",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Car/Bibli.",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Car/Pasaje",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "T.Adicional",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(width = 1.dp, color = Color.Black)
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        text = viewModel.getMajorPrice(viewModel.selectedMajor).toString(),
                        fontSize = 16.sp
                    )
                    Text(
                        text = viewModel.getAdditionalServicePrice(viewModel.additionalServiceList[0]).toString(),
                        fontSize = 16.sp
                    )
                    Text(
                        text = viewModel.getAdditionalServicePrice(viewModel.additionalServiceList[1]).toString(),
                        fontSize = 16.sp
                    )
                    Text(
                        text = viewModel.additionalPrice.toString(),
                        fontSize = 16.sp
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(width = 1.dp, color = Color.Black)
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        text = "Total",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "",
                        fontSize = 16.sp
                    )
                    Text(
                        text = "",
                        fontSize = 16.sp
                    )
                    Text(
                        text = viewModel.totalPrice.toString(),
                        fontSize = 16.sp
                    )
                }

                Button(
                    onClick = {
                        Intent(context, PrintActivity::class.java).apply {
                            putExtra("studentNames", viewModel.studentNames)
                            putExtra("dni", viewModel.dni)
                            putExtra("major", viewModel.selectedMajor?.name)
                            putExtra("majorPrice", viewModel.selectedMajor?.price)
                            putExtra("firstAdditionalService", viewModel.additionalServiceList[0].name)
                            putExtra("firstAdditionalServicePrice", viewModel.getAdditionalServicePrice(viewModel.additionalServiceList[0]))
                            putExtra("secondAdditionalService", viewModel.additionalServiceList[1].name)
                            putExtra("secondAdditionalServicePrice", viewModel.getAdditionalServicePrice(viewModel.additionalServiceList[1]))
                            putExtra("additionalPrice", viewModel.additionalPrice)
                            putExtra("totalPrice", viewModel.totalPrice)
                        }.also {
                            context.startActivity(it)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Text("Imprimir")
                }
            }
        }
    }
}