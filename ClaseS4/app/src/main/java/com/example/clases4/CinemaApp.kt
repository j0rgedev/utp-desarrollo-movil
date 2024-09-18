package com.example.clases4

import android.content.Intent
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
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clases4.ui.theme.ClaseS4Theme
import com.example.tareas3.CinemaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CinemaApp( viewModel: CinemaViewModel = CinemaViewModel() ) {
    val context = LocalContext.current

    ClaseS4Theme {
        Scaffold (
            topBar = {
                TopBar(
                    title = "KVBE Cine",
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
                Text(
                    text = "Reserva de Asientos",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                OutlinedTextField(
                    value = viewModel.clientNames,
                    onValueChange = { viewModel.onClientNameChange(it) },
                    label = { Text("Nombre del Cliente") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                )

                Column(Modifier.selectableGroup()) {
                    Text(
                        text = "Géneros",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    viewModel.genreList.forEach { genre ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .height(56.dp)
                                .selectable(
                                    selected = (viewModel.selectedGenre == genre),
                                    onClick = {
                                        viewModel.onGenreSelected(genre)
                                    },
                                    role = Role.RadioButton
                                )
                        ) {
                            RadioButton(
                                selected = (viewModel.selectedGenre == genre),
                                onClick = null
                            )
                            Text(
                                text = genre.name,
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
                        expanded = viewModel.movieDropDownExpanded,
                        onExpandedChange = {
                            viewModel.onMovieDropDownExpand()
                        }
                    ) {
                        OutlinedTextField(
                            value = viewModel.selectedMovie?.title.takeIf { it?.isNotEmpty() ?: false } ?: "Seleccionar",
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = viewModel.movieDropDownExpanded) },
                            modifier = Modifier.menuAnchor().fillMaxWidth()
                        )

                        ExposedDropdownMenu(
                            modifier = Modifier.fillMaxWidth(),
                            expanded = viewModel.movieDropDownExpanded,
                            onDismissRequest = { viewModel.onMovieDropDownExpand() }
                        ) {
                            viewModel.filteredMovies.forEach { movie ->
                                DropdownMenuItem(
                                    text = { Text(text = movie.title) },
                                    onClick = {
                                        viewModel.onMovieSelected(movie)
                                        viewModel.onMovieDropDownExpand()
                                    }
                                )
                            }
                        }
                    }
                }

                OutlinedTextField(
                    value = viewModel.adultSeatsCount.toString(),
                    onValueChange = { newValue ->
                        if (newValue.isEmpty()) {
                            viewModel.onAdultSeatsSelected(0)
                        } else if (newValue.all { it.isDigit() }) {
                            viewModel.onAdultSeatsSelected(newValue.toInt())
                        }
                    },
                    label = { Text("Número de Asientos Adultos") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    )
                )

                OutlinedTextField(
                    value = viewModel.kidsSeatsCount.toString(),
                    onValueChange = { newValue ->
                        if (newValue.isEmpty()) {
                            viewModel.onKidSeatsSelected(0)
                        } else if (newValue.all { it.isDigit() }) {
                            viewModel.onKidSeatsSelected(newValue.toInt())
                        }
                    },
                    label = { Text("Número de Asientos Niños") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    )
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            viewModel.calculateTotalPrice()

                            val intent = Intent(context, CinemaDetailsActivity::class.java)
                            intent.putExtra("clientNames", viewModel.clientNames)
                            intent.putExtra("genreName", viewModel.selectedGenre?.name)
                            intent.putExtra("movieTitle", viewModel.selectedMovie?.title)
                            intent.putExtra("moviePhoto", viewModel.selectedMovie?.photoResId)
                            intent.putExtra("adultSeatsCount", viewModel.adultSeatsCount)
                            intent.putExtra("kidsSeatsCount", viewModel.kidsSeatsCount)
                            intent.putExtra("kidsSeatsPrice", viewModel.kidsSeatsPrice)
                            intent.putExtra("adultSeatsPrice", viewModel.adultSeatsPrice)
                            intent.putExtra("totalFinalPrice", viewModel.calculateTotalPrice())

                            context.startActivity(intent)
                        },
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text("Procesar")
                    }
                }
            }
        }
    }
}