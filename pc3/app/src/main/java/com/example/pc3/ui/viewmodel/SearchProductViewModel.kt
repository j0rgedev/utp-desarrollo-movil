package com.example.pc3.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pc3.data.remote.model.Product
import com.example.pc3.data.repository.ProductRepository
import kotlinx.coroutines.launch
import kotlin.text.isBlank

class ProductViewModel : ViewModel() {
    private val repository = ProductRepository()

    var product = mutableStateOf<Product?>(null)
        private set

    var isLoading = mutableStateOf(false)
        private set

    fun fetchProductsById(id: Int) {
        viewModelScope.launch {
            isLoading.value = true
            product.value = null
            try {
                val response = repository.getProductById(id)
                if (response is String && response.isBlank()) {
                    product.value = null
                } else {
                    product.value = response
                }
            } catch (e: Exception) {

            } finally {
                isLoading.value = false
            }
        }
    }
}
