package com.example.tareas3

import androidx.lifecycle.ViewModel

class PrintViewModel : ViewModel() {
    var studentName: String = ""
    var schoolName: String = ""
    var majorName: String = ""
    var additionalCharges: String = ""
    var additionalChargesAmount: Int = 0
    var feeNumber: Int = 0
    var majorCost: Int = 0
    var pension: Int = 0
    var additionalExpenses: Int = 0
    var totalAmount: Int = 0
}