package com.example.tareas4

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jc4.TopBar
import com.example.tareas4.ui.theme.TareaS4Theme

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CarsApp( viewModel: CarsViewModel = CarsViewModel() ) {
    val context = LocalContext.current

    TareaS4Theme {
        Scaffold (
            topBar = {
                TopBar(
                    title = "KVBE Carros",
                    navigateBack = null
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
                Column(Modifier.selectableGroup()) {
                    Text(
                        text = "Marcas",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    viewModel.cars.forEach { car ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .height(56.dp)
                                .selectable(
                                    selected = (viewModel.selectedCar == car),
                                    onClick = {
                                        viewModel.onCarSelected(car)
                                    },
                                    role = Role.RadioButton
                                )
                        ) {
                            RadioButton(
                                selected = (viewModel.selectedCar == car),
                                onClick = null
                            )
                            Text(
                                text = car.brand,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(start = 16.dp)
                            )
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    ExposedDropdownMenuBox(
                        expanded = viewModel.modelDropDownExpanded,
                        onExpandedChange = {
                            viewModel.onModelDropDownExpanded()
                        }
                    ) {
                        OutlinedTextField(
                            value = viewModel.selectedModel?.name.takeIf { it?.isNotEmpty() ?: false } ?: "Seleccionar",
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = viewModel.modelDropDownExpanded) },
                            modifier = Modifier.menuAnchor().fillMaxWidth()
                        )

                        ExposedDropdownMenu(
                            modifier = Modifier.fillMaxWidth(),
                            expanded = viewModel.modelDropDownExpanded,
                            onDismissRequest = { viewModel.onModelDropDownExpanded() }
                        ) {
                            viewModel.filteredModels.forEach { model ->
                                DropdownMenuItem(
                                    text = { Text(text = model.name) },
                                    onClick = {
                                        viewModel.onModelSelected(model)
                                        viewModel.onModelDropDownExpanded()
                                    }
                                )
                            }
                        }
                    }
                }

                if (viewModel.selectedModel != null) {
                    Image(
                        painter = painterResource(id = viewModel.selectedModel?.photoResId ?: 0),
                        contentDescription = "Foto de la película",
                        modifier = Modifier
                            .padding(26.dp)
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                }
            }
        }
    }
}