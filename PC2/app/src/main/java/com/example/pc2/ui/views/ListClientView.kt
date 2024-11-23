package com.example.pc2.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pc2.data.local.entity.Client
import com.example.pc2.ui.viewmodel.ClientViewModel
import com.example.pc2.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListClientView(navController: NavController, viewModel: ClientViewModel) {
    val clientSearch = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Clientes") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
            )
        },
        floatingActionButton = {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .padding(16.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable {
                        navController.navigate("add")
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add client",
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
            }
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding)
                    .padding(12.dp)
            ) {
                OutlinedTextField(
                    value = clientSearch.value,
                    onValueChange = { newValue ->
                        clientSearch.value = newValue
                        viewModel.searchClient(newValue)
                    },
                    label = { Text("Ingresar apellido") },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = "Search client",
                            tint = Color.Gray
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                if (viewModel.clients.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(padding),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Sin resultados",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                } else {
                    LazyColumn(
                        contentPadding = padding,
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(
                            viewModel.clients.count(),
                            key = { index -> viewModel.clients[index].code }) { index ->
                            ClientListItem(
                                client = viewModel.clients[index],
                                navController = navController,
                                viewModel = viewModel
                            )
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun ClientListItem(
    client: Client,
    navController: NavController,
    viewModel: ClientViewModel
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate("detail/${client.code}")
            }
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(75.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
            ) {
                client.photo.let { photoId ->
                    if (photoId != 0){
                        Image(
                            painter = painterResource(id = photoId),
                            contentDescription = "Client Photo",
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(28.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Nombres: ${client.names}",
                    fontSize = 20.sp
                )
                Text(
                    text = "Apellidos: ${client.lastNames}",
                    fontSize = 20.sp,
                    color = Color.DarkGray
                )
            }
        }
    }
}