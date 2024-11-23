package com.example.pc2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavArgumentBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pc2.ui.viewmodel.ClientViewModel
import com.example.pc2.ui.views.AddClientView
import com.example.pc2.ui.views.ClientDetailView
import com.example.pc2.ui.views.ListClientView

@Composable
fun NavManager(viewModel: ClientViewModel) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "index") {
        composable("index") {
            ListClientView(navController, viewModel)
        }

        composable("add") {
            AddClientView(navController, viewModel)
        }

        composable(
            "detail/{id}", arguments = listOf(
                navArgument("id") { type = NavType.Companion.IntType },
            )
        ) {
            ClientDetailView(
                navController,
                viewModel,
                it.arguments!!.getInt("id")
            )
        }
    }

}