package com.example.pc2.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pc2.ui.viewmodel.ClientViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientDetailView(navController: NavController, viewModel: ClientViewModel, id: Int) {
    val client = viewModel.clients.find { it.code == id }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Detalles del Cliente",
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
            if (client != null) {
                Text(text = "DNI", fontWeight = FontWeight.Bold)
                Text(text = client.dni)

                Text(text = "Nombre", fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 8.dp))
                Text(text = client.names)

                Text(text = "Apellido", fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 8.dp))
                Text(text = client.lastNames)

                Text(text = "Operadora", fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 8.dp))
                Text(text = client.phoneOperator)

                Text(text = "Número", fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 8.dp))
                Text(text = client.phoneNumber)

                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                        .padding(top = 16.dp)
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
            } else {
                Text("Cliente no encontrado")
            }
        }
    }
}