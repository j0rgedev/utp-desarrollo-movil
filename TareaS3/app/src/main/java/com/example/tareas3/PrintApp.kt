package com.example.tareas3

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tareas3.ui.theme.TareaS3Theme

@Preview
@Composable
fun PrintApp( viewModel: PrintViewModel = PrintViewModel() ) {
    val context = LocalContext.current

    TareaS3Theme {
        Scaffold(
            topBar = {
                TopBar(
                    title = "Impresión de datos",
                    navigateBack = {
                        val intent = Intent(context, MainActivity::class.java)
                        context.startActivity(intent)
                    }
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
                Text(text = "Nombre del Alumno: ${viewModel.studentName}", fontSize = 16.sp)
                Text(text = "Nombre de la Escuela: ${viewModel.schoolName}", fontSize = 16.sp)
                Text(text = "Nombre de la Carrera: ${viewModel.majorName}", fontSize = 16.sp)
                Text(text = "Gastos Adicionales: ${viewModel.additionalCharges}", fontSize = 16.sp)
                Text(text = "Monto de Gastos Adicionales: S/. ${viewModel.additionalChargesAmount}", fontSize = 16.sp)
                Text(text = "Número de Cuotas: ${viewModel.feeNumber}", fontSize = 16.sp)
                Text(text = "Costo de la Carrera: S/. ${viewModel.majorCost}", fontSize = 16.sp)
                Text(text = "Pensión: S/. ${viewModel.pension}", fontSize = 16.sp)
                Text(text = "Gastos Adicionales: S/. ${viewModel.additionalExpenses}", fontSize = 16.sp)
                Text(text = "Total a Pagar: S/. ${viewModel.totalAmount}", fontSize = 16.sp)
            }
        }
    }
}