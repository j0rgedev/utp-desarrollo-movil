package com.example.tareas3

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tareas3.ui.theme.TareaS3Theme

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun EnrollmentApp( viewModel: EnrollmentViewModel = EnrollmentViewModel() ) {
    val context = LocalContext.current

    TareaS3Theme {
        Scaffold (
            topBar = {
                TopBar(
                    title = "Matrícula",
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
                OutlinedTextField(
                    value = viewModel.studentNames,
                    onValueChange = { viewModel.onStudentNamesChange(it) },
                    label = { Text("Nombre del alumno") },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Box (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ){
                    ExposedDropdownMenuBox(
                        expanded = viewModel.schoolDropDownExpanded,
                        onExpandedChange = {
                            viewModel.onSchoolDropDownExpand()
                        }
                    ) {
                        OutlinedTextField(
                            value = viewModel.selectedSchool.ifEmpty { "Selecciona un colegio" },
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon( expanded = viewModel.schoolDropDownExpanded) },
                            modifier = Modifier.menuAnchor().fillMaxWidth()
                        )

                        ExposedDropdownMenu(
                            modifier = Modifier.fillMaxWidth(),
                            expanded = viewModel.schoolDropDownExpanded,
                            onDismissRequest = { viewModel.onSchoolDropDownExpand() }
                        ) {
                            viewModel.schools.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(text = item) },
                                    onClick = {
                                        viewModel.onSchoolSelected(item)
                                        viewModel.onSchoolDropDownExpand()
                                    }
                                )
                            }
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    ExposedDropdownMenuBox(
                        expanded = viewModel.majorDropDownExpanded,
                        onExpandedChange = {
                            viewModel.onMajorDropDownExpand()
                        }
                    ) {
                        OutlinedTextField(
                            value = viewModel.selectedMajor.ifEmpty { "Selecciona una carrera" },
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = viewModel.majorDropDownExpanded) },
                            modifier = Modifier.menuAnchor().fillMaxWidth()
                        )

                        ExposedDropdownMenu(
                            modifier = Modifier.fillMaxWidth(),
                            expanded = viewModel.majorDropDownExpanded,
                            onDismissRequest = { viewModel.onMajorDropDownExpand() }
                        ) {
                            viewModel.majors.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(text = item) },
                                    onClick = {
                                        viewModel.onMajorSelected(item)
                                        viewModel.onMajorDropDownExpand()
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
                viewModel.additionalCharges.forEach { charge ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = viewModel.selectedAditionalCharges.contains(charge),
                            onCheckedChange = { isChecked ->
                                val newCharges = if (isChecked) {
                                    viewModel.selectedAditionalCharges + charge
                                } else {
                                    viewModel.selectedAditionalCharges - charge
                                }
                                viewModel.onAditionalChargesSelected(newCharges)
                            }
                        )
                        Text(charge)
                    }
                }

                Text(
                    text = "Número de Cuotas",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 16.dp)
                )
                viewModel.feeNumbers.forEach { fee ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .height(56.dp)
                            .selectable(
                            selected = viewModel.selectedFee == fee,
                            onClick = {
                                viewModel.onFeeSelected(fee)
                            },
                            role = Role.RadioButton
                        )
                    ) {
                        RadioButton(
                            selected = viewModel.selectedFee == fee,
                            onClick = null
                        )
                        Text(
                            text = fee,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.padding(top = 16.dp))

                Text("Costo Carrera: S/. ${viewModel.majorFinalPrice}")
                Text("Pensión: S/. ${viewModel.feeFinalPrice}")
                Text("Gastos Adicionales: S/. ${viewModel.aditionalChargesFinalPrice}")
                Text("Total Pagar: S/. ${viewModel.totalFinalPrice}")

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    Button(onClick = { viewModel.calculateTotalPrice() }) {
                        Text("CALCULAR")
                    }
                    Button(onClick = {
                        val intent = Intent(context, PrintActivity::class.java).apply {
                            putExtra("studentName", viewModel.studentNames)
                            putExtra("schoolName", viewModel.selectedSchool)
                            putExtra("majorName", viewModel.selectedMajor)
                            putExtra("additionalCharges", viewModel.selectedAditionalCharges.joinToString(", "))
                            putExtra("additionalChargesAmount", viewModel.aditionalChargesFinalPrice)
                            putExtra("feeNumber", viewModel.selectedFee)
                            putExtra("majorCost", viewModel.majorFinalPrice)
                            putExtra("pension", viewModel.feeFinalPrice)
                            putExtra("additionalExpenses", viewModel.aditionalChargesFinalPrice)
                            putExtra("totalAmount", viewModel.totalFinalPrice)
                        }

                        context.startActivity(intent)
                    }) {
                        Text("IMPRIMIR")
                    }
                }
            }
        }
    }
}