package com.example.repaso_pc3.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.repaso_pc3.data.remote.model.Product
import com.example.repaso_pc3.ui.viewmodel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListView(navController: NavController, viewModel: ProductViewModel) {
    val products = viewModel.products.value
    val isLoading = viewModel.isLoading.value
    val errorMessage = viewModel.errorMessage.value

    LaunchedEffect(Unit) {
        viewModel.fetchProducts()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        Button(
            onClick = { navController.navigate("addProduct") },
        ) {
            Text("Agregar Nuevo Producto")
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            when {
                isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                errorMessage != null -> {
                    Text(
                        text = errorMessage,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(
                            products.count(),
                            key = { index -> products[index].id }) { index ->
                            ProductItem(
                                product = products[index],
                                navController = navController,
                                viewModel = viewModel
                            )
                        }
                    }
                }
            }
        }
    }


}

@Composable
fun ProductItem(
    product: Product,
    navController: NavController,
    viewModel: ProductViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navController.navigate("editProduct/${product.id}")
            }
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = product.image),
            contentDescription = "Product Image",
            modifier = Modifier.size(80.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = product.title, maxLines = 1)
            Text(text = "$${product.price}")
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = { viewModel.deleteProduct(product.id) },
            modifier = Modifier.padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Eliminar"
            )
        }
    }
}