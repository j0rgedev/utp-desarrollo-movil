package com.example.finalapp.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import com.example.finalapp.data.local.entity.Teacher
import com.example.finalapp.ui.viewmodel.TeacherViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeacherListView(navController: NavController, viewModel: TeacherViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Profesores") },
                actions = {
                    IconButton(onClick = { navController.navigate("add") }) {
                        Icon(Icons.Default.Add, contentDescription = "Agregar Profesor")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                if (viewModel.teachers.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(padding),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No hay profesores registrados.",
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
                            viewModel.teachers.count(),
                            key = { index -> viewModel.teachers[index].code }) { index ->
                            TeacherListItem(
                                teacher = viewModel.teachers[index],
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
fun TeacherListItem(
    teacher: Teacher,
    navController: NavController,
    viewModel: TeacherViewModel
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate("update/${teacher.code}")
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
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "${teacher.names} ${teacher.fathersLastName} ${teacher.mothersLastName}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = "Ingreso: ${teacher.income}",
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
                Text(
                    text = "Hijos: ${teacher.children}",
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
            }

            IconButton(onClick = { viewModel.delete(teacher) }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar Profesor",
                    tint = Color.Red
                )
            }
        }
    }
}

