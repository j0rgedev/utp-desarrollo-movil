package com.example.clases3

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ServiceViewModel : ViewModel() {
    var clientNames by mutableStateOf("")
        private set

    var clientDni by mutableStateOf("")
        private set

    var serviceList by mutableStateOf<List<Service>>(listOf())
        private set

    var selectedService by mutableStateOf<Service?>(null)
        private set

    var totalPrice by mutableDoubleStateOf(0.0)
        private set

    var discountPrice by mutableDoubleStateOf(0.0)
        private set

    init {
        serviceList = listOf(
            Service(1, "Dúo - Cámaras de Seguridad Y Alarmas contra robos", 119.30, 37.00, 0.07),
            Service(2, "Trio - Cámaras de Seguridad - Alarmas contra robos - Alarmas contra Incendios", 169.80, 65.00, 0.12),
            Service(3, "Solo Cámaras de Seguridad", 59.50, 21.00, 0.04),
            Service(4, "Solo Alarmas contra Robos", 49.20, 17.00, 0.04),
            Service(5, "Solo Alarmas contra Incendios", 42.30, 15.00, 0.04),
            Service(6, "Solo Cerco Eléctrico", 125.70, 35.00, 0.05),
        )
    }

    fun onClientNamesChange(newClientNames: String) {
        clientNames = newClientNames
    }

    fun onClientDniChange(newClientDni: String) {
        clientDni = newClientDni
    }

    fun onServiceSelected(service: Service) {
        selectedService = service
    }

    fun calculateTotalPrice(): Double {
        val service = selectedService ?: return 0.0
        val price = service.price
        val installationPrice = service.installationPrice
        val discount = service.discount
        discountPrice = price * discount
        totalPrice = price + installationPrice - discountPrice
        return totalPrice
    }
}

data class Service(
    val id: Int,
    val description: String,
    val price: Double,
    val installationPrice: Double,
    val discount: Double
)