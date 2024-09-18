package com.example.tareas4

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CarsViewModel : ViewModel() {

    var cars by mutableStateOf<List<Car>>(listOf())
        private set

    var models by mutableStateOf<List<Model>>(listOf())

    var modelDropDownExpanded by mutableStateOf(false)
        private set

    var selectedCar by mutableStateOf<Car?>(null)
        private set

    var selectedModel by mutableStateOf<Model?>(null)
        private set

    var filteredModels by mutableStateOf<List<Model>>(listOf())

    init {
        cars = listOf(
            Car(1, "Toyota"),
            Car(2, "Nissan"),
        )

        models = listOf(
            Model(1, "Corolla", 1, R.drawable.corolla),
            Model(2, "Corolla Hatchback", 1, R.drawable.hatchback),
            Model(3, "Camry", 1, R.drawable.camry),
            Model(4, "Sentra", 2, R.drawable.sentra),
            Model(5, "370Z", 2, R.drawable.z370),
        )
    }

    fun onModelDropDownExpanded() {
        modelDropDownExpanded = !modelDropDownExpanded
    }

    fun onCarSelected(car: Car) {
        selectedCar = car
        filteredModels = models.filter { it.carId == car.id }
    }

    fun onModelSelected(model: Model) {
        selectedModel = model
        modelDropDownExpanded = false
    }
}

data class Car(
    val id: Int,
    val brand: String,
)

data class Model(
    val id: Int,
    val name: String,
    val carId: Int,
    val photoResId: Int
)