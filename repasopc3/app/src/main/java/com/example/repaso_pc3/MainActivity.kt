package com.example.repaso_pc3

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
import com.example.repaso_pc3.ui.navigation.NavManager
import com.example.repaso_pc3.ui.theme.Repasopc3Theme
import com.example.repaso_pc3.ui.viewmodel.ProductViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Repasopc3Theme {
                val viewModel = ProductViewModel()

                NavManager(viewModel = viewModel)
            }
        }
    }
}