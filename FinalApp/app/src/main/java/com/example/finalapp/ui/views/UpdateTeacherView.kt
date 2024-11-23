package com.example.finalapp.ui.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.finalapp.ui.components.TeacherForm
import com.example.finalapp.ui.theme.FinalAppTheme
import com.example.finalapp.ui.viewmodel.TeacherViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateTeacherView(navController: NavController, viewModel: TeacherViewModel, id: Int) {
    val teacher = viewModel.teachers.find { it.code == id }

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
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        if (teacher != null) {
            TeacherForm(
                teacher = teacher,
                onSave = { updatedTeacher ->
                    viewModel.update(updatedTeacher)
                    navController.popBackStack()
                },
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(vertical = 8.dp, horizontal = 16.dp)
                    .fillMaxWidth()
            )
        } else {
            Text("Profesor no encontrado")
        }
    }
}