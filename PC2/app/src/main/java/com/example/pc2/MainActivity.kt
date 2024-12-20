package com.example.pc2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.example.pc2.data.local.AppDatabase
import com.example.pc2.ui.navigation.NavManager
import com.example.pc2.ui.theme.PC2Theme
import com.example.pc2.ui.viewmodel.ClientViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PC2Theme {
                val database = Room.databaseBuilder(
                    applicationContext,
                    AppDatabase::class.java,
                    "db_finalapp"
                ).build()
                val dao = database.clientsDao()

                val viewModel = ClientViewModel(clientDao = dao)

                NavManager(viewModel = viewModel)
            }
        }
    }
}