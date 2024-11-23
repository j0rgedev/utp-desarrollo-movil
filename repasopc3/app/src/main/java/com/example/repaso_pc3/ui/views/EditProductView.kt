package com.example.repaso_pc3.ui.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.repaso_pc3.data.remote.model.Product
import com.example.repaso_pc3.ui.viewmodel.ProductViewModel

@Composable
fun EditProductView(navController: NavController, viewModel: ProductViewModel, product: Product) {
    var title by remember { mutableStateOf(product.title) }
    var price by remember { mutableStateOf(product.price.toString()) }
    var description by remember { mutableStateOf(product.description) }
    var category by remember { mutableStateOf(product.category) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Título") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Precio") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = category,
            onValueChange = { category = it },
            label = { Text("Categoría") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                val updatedProduct = product.copy(
                    title = title,
                    price = price.toDoubleOrNull() ?: product.price,
                    description = description,
                    category = category,
                )
                viewModel.updateProduct(updatedProduct) {
                    navController.popBackStack()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar Producto")
        }
    }
}
