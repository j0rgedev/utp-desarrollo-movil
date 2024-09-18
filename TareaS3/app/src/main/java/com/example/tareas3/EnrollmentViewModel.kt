package com.example.tareas3

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class EnrollmentViewModel : ViewModel() {

    var studentNames by mutableStateOf("")
        private set

    var schoolDropDownExpanded by mutableStateOf(false)
        private set

    var majorDropDownExpanded by mutableStateOf(false)
        private set

    var selectedAditionalCharges by mutableStateOf(listOf<String>())
        private set

    var majors by mutableStateOf(listOf<String>())
        private set

    var majorFinalPrice by mutableIntStateOf(0)
    var aditionalChargesFinalPrice by mutableIntStateOf(0)
    var feeFinalPrice by mutableIntStateOf(0)
    var totalFinalPrice by mutableIntStateOf(0)

    var selectedFee by mutableStateOf("")

    var selectedSchool by mutableStateOf("")

    var selectedMajor by mutableStateOf("")

    val additionalCharges = listOf("Carnet Biblioteca", "Carnet Medio Pasaje")

    val feeNumbers = listOf("4", "5", "6")

    val schools = listOf("Seleccionar", "Tecnologías de la Información", "Gestión y Negocios")

    // Majors depending on the selected school
    private val majorsMap = mapOf(
        "Tecnologías de la Información" to listOf(
            "Computación e informática",
            "Administración de redes y comunicaciones",
            "Administración y sistemas",
            "Industrial y sistemas"
        ),
        "Gestión y Negocios" to listOf(
            "Administración de empresas",
            "Contabilidad",
            "Marketing",
            "Administración de negocios internacionales",
            "Administración de negocios bancarios y financieros",
            "Gestión de recursos humanos",
            "Gestión de logística",
            "Administración de operaciones turísticas"
        )
    )

    // Major prices
    private val majorPrices = mapOf(
        "Computación e informática" to 3200,
        "Administración de redes y comunicaciones" to 3100,
        "Administración y sistemas" to 3000,
        "Industrial y sistemas" to 0,
        "Administración de empresas" to 2800,
        "Contabilidad" to 2700,
        "Marketing" to 2600,
        "Administración de negocios internacionales" to 2650,
        "Administración de negocios bancarios y financieros" to 2750,
        "Gestión de recursos humanos" to 2550,
        "Gestión de logística" to 0,
        "Administración de operaciones turísticas" to 0
    )

    // Fee percentage based on the number of installments
    private val feePercentages = mapOf(
        "4" to 0.0,
        "5" to 0.12,
        "6" to 0.16
    )

    // Additional charges
    private val additionalChargesPrices = mapOf(
        "Carnet Biblioteca" to 25,
        "Carnet Medio Pasaje" to 22
    )

    fun onStudentNamesChange(studentNames: String) {
        this.studentNames = studentNames
    }

    fun onSchoolDropDownExpand() {
        schoolDropDownExpanded = !schoolDropDownExpanded
    }

    fun onMajorDropDownExpand() {
        majorDropDownExpanded = !majorDropDownExpanded
    }

    fun onMajorSelected(major: String) {
        majorFinalPrice = majorPrices[major] ?: 0
        selectedMajor = major
    }

    fun onSchoolSelected(school: String) {
        selectedSchool = school
        updateMajorsBasedOnSchool(school)
        selectedMajor = ""
        majorFinalPrice = 0
    }

    private fun updateMajorsBasedOnSchool(school: String) {
        majors = majorsMap[school] ?: listOf()
    }

    fun onAditionalChargesSelected(aditionalCharges: List<String>) {
        selectedAditionalCharges = aditionalCharges
        aditionalChargesFinalPrice = aditionalCharges.sumOf {
            additionalChargesPrices[it] ?: 0
        }
    }

    fun onFeeSelected(fee: String) {
        selectedFee = fee
    }

    fun calculateTotalPrice() {
        val percentage = feePercentages[selectedFee] ?: 0.0
        val totalMajorCost = majorFinalPrice + (majorFinalPrice * percentage)
        val feeNumber = selectedFee.toIntOrNull() ?: 1
        feeFinalPrice = (totalMajorCost / feeNumber).toInt()
        totalFinalPrice = feeFinalPrice + aditionalChargesFinalPrice + majorFinalPrice
    }

    fun resetValues() {
        studentNames = ""
        schoolDropDownExpanded = false
        majorDropDownExpanded = false
        majorFinalPrice = 0
        aditionalChargesFinalPrice = 0
        feeFinalPrice = 0
        totalFinalPrice = 0
    }
}