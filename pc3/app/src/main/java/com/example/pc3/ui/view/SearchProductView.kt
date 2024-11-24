package com.example.pc3.ui.view

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pc3.ui.viewmodel.ProductViewModel

@Composable
fun SearchProductView(viewModel: ProductViewModel) {
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
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = id,
                    onValueChange = { id = it },
                    label = { Text("ID") },
                    modifier = Modifier
                        .fillMaxWidth(0.5f),
                )
                Button(
                    onClick = {
                        viewModel.fetchProductsById(id.toInt())
                    },
                    modifier = Modifier
                        .fillMaxWidth(1f),
                ) {
                    Text("Buscar")
                }
            }

            if (viewModel.isLoading.value) {
                Text("Cargando productos...")
            }

            if (viewModel.product.value != null && !viewModel.isLoading.value) {
                Text(
                    text = "ID",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Text(viewModel.product.value!!.id.toString())
                Text(
                    text = "Título",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Text(viewModel.product.value!!.title)
                Text(
                    text = "Precio",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Text(viewModel.product.value!!.price.toString())
                Text(
                    text = "Descripción",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Text(viewModel.product.value!!.description)
                Text(
                    text = "Categoría",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Text(viewModel.product.value!!.category)
                Text(
                    text = "Rating Rate",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Text(viewModel.product.value!!.rating.rate.toString())
                Text(
                    text = "Rating Count",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Text(viewModel.product.value!!.rating.count.toString())
            } else if (!viewModel.isLoading.value) {
                Text("Producto no encontrado")
            }
        }
    }

}

@Preview
@Composable
fun PreviewSearchProductView() {
    SearchProductView(ProductViewModel())
}