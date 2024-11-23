package com.example.finalapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.room.Room
import com.example.finalapp.data.local.AppDatabase
import com.example.finalapp.data.local.dao.TeacherDao
import com.example.finalapp.ui.navigation.NavManager
import com.example.finalapp.ui.theme.FinalAppTheme
import com.example.finalapp.ui.viewmodel.TeacherViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FinalAppTheme {
                val database = Room.databaseBuilder(
                    applicationContext,
                    AppDatabase::class.java,
                    "db_finalapp"
                ).build()
                val dao = database.teachersDao()

                val viewModel = TeacherViewModel(dao = dao)

                NavManager(viewModel = viewModel)
            }
        }
    }
}