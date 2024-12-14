package com.example.afinal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.afinal.ui.theme.FinalTheme
import com.example.afinal.ui.view.SearchPostView
import com.example.afinal.ui.viewmodel.SearchPostViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FinalTheme {
                SearchPostView(SearchPostViewModel())
            }
        }
    }
}