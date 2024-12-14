package com.example.afinal.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.afinal.ui.viewmodel.SearchPostViewModel

@Composable
fun SearchPostView(viewModel: SearchPostViewModel) {
    var id by remember { mutableStateOf("") }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = id,
                    onValueChange = {
                        // Filtrar solo números
                        id = it.filter { char -> char.isDigit() }
                    },
                    label = { Text("ID del Post") },
                    modifier = Modifier.fillMaxWidth(0.5f),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )
                Button(
                    onClick = {
                        val postId = id.toIntOrNull() ?: 0
                        viewModel.fetchPostById(postId)
                    },
                    modifier = Modifier.fillMaxWidth(1f),
                ) {
                    Text("Buscar")
                }
            }

            when (val state = viewModel.uiState) {
                is SearchPostViewModel.PostUiState.Initial -> {
                    Text("Introduce un ID para buscar")
                }
                is SearchPostViewModel.PostUiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(50.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }
                is SearchPostViewModel.PostUiState.Success -> {
                    val post = state.post
                    Column {
                        Text(
                            text = "ID Usuario",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                        Text(post.userId.toString())

                        Text(
                            text = "ID post",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                        Text(post.id.toString())

                        Text(
                            text = "Título",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                        Text(post.title)

                        Text(
                            text = "Body",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                        Text(post.body)
                    }
                }
                is SearchPostViewModel.PostUiState.NotFound -> {
                    Text(
                        "No se encontró un post con ese ID",
                        color = MaterialTheme.colorScheme.error
                    )
                }
                is SearchPostViewModel.PostUiState.Error -> {
                    Text(
                        "Error: ${state.message}",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewSearchPostView() {
    SearchPostView(SearchPostViewModel())
}