package com.example.repaso_pc3.ui.navigation

import com.example.repaso_pc3.ui.views.ProductListView
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.repaso_pc3.ui.viewmodel.ProductViewModel
import com.example.repaso_pc3.ui.views.AddProductView
import com.example.repaso_pc3.ui.views.EditProductView

@Composable
fun NavManager(viewModel: ProductViewModel) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "index") {
        composable("index") {
            ProductListView(navController, viewModel)
        }

        composable("addProduct") {
            AddProductView(navController = navController, viewModel = viewModel)
        }

        composable("editProduct/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")?.toInt() ?: 0
            val product = viewModel.products.value.find { it.id == productId }
            product?.let {
                EditProductView(navController = navController, viewModel = viewModel, product = it)
            }
        }
    }

}