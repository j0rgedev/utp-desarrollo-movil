package com.example.pc1

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class EnrollmentViewModel : ViewModel() {

    var dni by mutableStateOf("")
        private set

    var studentNames by mutableStateOf("")
        private set

    var majorList by mutableStateOf<List<Major>>(listOf())
        private set

    var additionalServiceList by mutableStateOf<List<AdditionalService>>(listOf())
        private set

    var majorDropDownExpanded by mutableStateOf(false)
        private set

    var selectedMajor by mutableStateOf<Major?>(null)
        private set

    var totalPrice by mutableDoubleStateOf(0.0)
        private set

    var additionalPrice by mutableDoubleStateOf(0.0)
        private set

    var selectedAdditionalServices by mutableStateOf<List<AdditionalService>>(listOf())

    init {
        additionalServiceList = listOf(
            AdditionalService(1, "Carnet Biblioteca", 25.0),
            AdditionalService(2, "Carnet Medio Pasaje", 22.0),
        )

        majorList = listOf(
            Major(1, "Computación e informática", 640.0),
            Major(2, "Administración de redes y comunicaciones", 600.0),
            Major(3, "Administración y sistemas", 540.0),
        )
    }

    fun onDniChange(newDni: String) {
        dni = newDni
    }

    fun onStudentNameChange(newStudentNames: String) {
        studentNames = newStudentNames
    }

    fun onMajorDropDownExpanded() {
        majorDropDownExpanded = !majorDropDownExpanded
    }

    fun onMajorSelected(major: Major) {
        selectedMajor = major
        majorDropDownExpanded = false
    }

    fun getAdditionalServicePrice(additionalService: AdditionalService): Double {
        if (selectedAdditionalServices.contains(additionalService)) {
            return additionalService.price
        } else {
            return 0.0
        }
    }

    fun getMajorPrice(major: Major?): Double {
        if (selectedMajor == major) {
            return major?.price ?: 0.0
        }
        return 0.0
    }

    fun onAdditionalServiceSelected(additionalService: AdditionalService) {
        if (selectedAdditionalServices.contains(additionalService)) {
            selectedAdditionalServices = selectedAdditionalServices.filter { it != additionalService }
        } else {
            selectedAdditionalServices = selectedAdditionalServices + additionalService
        }
    }

    fun getTotal(): Double {
        val majorPrice = selectedMajor?.price ?: 0.0
        additionalPrice = selectedAdditionalServices.sumOf { it.price }
        totalPrice = majorPrice + additionalPrice
        return totalPrice
    }
}

data class Major(val id: Int, val name: String, val price: Double)

data class AdditionalService(val id: Int, val name: String, val price: Double)