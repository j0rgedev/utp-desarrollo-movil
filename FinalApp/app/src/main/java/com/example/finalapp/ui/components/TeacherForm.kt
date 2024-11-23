package com.example.finalapp.ui.components

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
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finalapp.data.local.entity.Teacher
import com.example.finalapp.R
import com.example.finalapp.utils.TeacherSaver
import kotlin.math.log

data class Sex(val id: Int, val name: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeacherForm(
    teacher: Teacher?,
    onSave: (Teacher) -> Unit,
    modifier: Modifier = Modifier
) {
    val teacherState = rememberSaveable(stateSaver = TeacherSaver) {
        mutableStateOf(teacher ?: Teacher())
    }

    val sexList = listOf(
        Sex(1, "Masculino"),
        Sex(2, "Femenino")
    )

    val initialSex = sexList.find { it.name == teacherState.value.sex }
    val selectedSex = remember { mutableStateOf(initialSex) }
    val sexDropDownExpanded = rememberSaveable { mutableStateOf(false) }

    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        OutlinedTextField(
            value = teacherState.value.names,
            onValueChange = { newValue ->
                teacherState.value = teacherState.value.copy(names = newValue)
            },
            label = { Text("Ingresar Nombre") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Nombre",
                    tint = Color.Gray
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )

        OutlinedTextField(
            value = teacherState.value.fathersLastName,
            onValueChange = { newValue ->
                teacherState.value = teacherState.value.copy(fathersLastName = newValue)
            },
            label = { Text("Ingresar Apellido Paterno") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Apellido Paterno",
                    tint = Color.Gray
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )

        OutlinedTextField(
            value = teacherState.value.mothersLastName,
            onValueChange = { newValue ->
                teacherState.value = teacherState.value.copy(mothersLastName = newValue)
            },
            label = { Text("Ingresar Apellido Materno") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Apellido Materno",
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
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedTextField(
                value = teacherState.value.income.toString(),
                onValueChange = { newValue ->
                    val income = newValue.toDoubleOrNull() ?: 0.0
                    teacherState.value = teacherState.value.copy(income = income)
                },
                label = { Text("Ingresar Ingreso") },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_money),
                        contentDescription = "Ingreso",
                        tint = Color.Gray
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            OutlinedTextField(
                value = teacherState.value.children.toString(),
                onValueChange = { newValue ->
                    val children = newValue.toIntOrNull() ?: 0
                    teacherState.value = teacherState.value.copy(children = children)
                },
                label = { Text("Ingresar Hijos") },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_count),
                        contentDescription = "Hijos",
                        tint = Color.Gray
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.weight(1f)
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
                    sexList.forEach { sex ->
                        DropdownMenuItem(
                            text = { Text(text = sex.name) },
                            onClick = {
                                selectedSex.value = sex
                                sexDropDownExpanded.value = false
                                teacherState.value = teacherState.value.copy(sex = sex.name)
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
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    Log.i("TeacherForm", "Teacher: ${teacherState.value}")
                    onSave(teacherState.value);
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
                onClick = { /* Acción de cerrar o cancelar */ },
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        Icons.Filled.Clear,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(text = "Limpiar")
                }
            }
        }
    }
}