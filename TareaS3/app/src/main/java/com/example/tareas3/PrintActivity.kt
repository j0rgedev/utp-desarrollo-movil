package com.example.tareas3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider

class PrintActivity : ComponentActivity() {
    private lateinit var viewModel: PrintViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            viewModel = ViewModelProvider(this)[PrintViewModel::class.java]

            viewModel.studentName = intent.getStringExtra("studentName") ?: ""
            viewModel.schoolName = intent.getStringExtra("schoolName") ?: ""
            viewModel.majorName = intent.getStringExtra("majorName") ?: ""
            viewModel.additionalCharges = intent.getStringExtra("additionalCharges") ?: ""
            viewModel.additionalChargesAmount = intent.getIntExtra("additionalChargesAmount", 0)
            viewModel.feeNumber = intent.getIntExtra("feeNumber", 0)
            viewModel.majorCost = intent.getIntExtra("majorCost", 0)
            viewModel.pension = intent.getIntExtra("pension", 0)
            viewModel.additionalExpenses = intent.getIntExtra("additionalExpenses", 0)
            viewModel.totalAmount = intent.getIntExtra("totalAmount", 0)

            PrintApp(viewModel = viewModel)
        }
    }
}