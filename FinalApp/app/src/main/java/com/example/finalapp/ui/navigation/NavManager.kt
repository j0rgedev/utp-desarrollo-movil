package com.example.finalapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.finalapp.ui.viewmodel.TeacherViewModel
import com.example.finalapp.ui.views.AddTeacherView
import com.example.finalapp.ui.views.TeacherListView
import com.example.finalapp.ui.views.UpdateTeacherView

@Composable
fun NavManager(viewModel: TeacherViewModel) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "index") {
        composable("index") {
            TeacherListView(navController, viewModel)
        }

        composable("add") {
            AddTeacherView(navController, viewModel)
        }

        composable("update/{id}", arguments = listOf(
            navArgument("id") { type = NavType.IntType },
        )) {
            UpdateTeacherView(
                navController,
                viewModel,
                it.arguments!!.getInt("id"),
            )
        }
    }

}