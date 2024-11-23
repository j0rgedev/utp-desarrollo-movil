package com.example.repaso_pc3.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repaso_pc3.data.remote.model.Product
import com.example.repaso_pc3.data.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    private val repository = ProductRepository()

    var products = mutableStateOf<List<Product>>(emptyList())
        private set

    var isLoading = mutableStateOf(false)
        private set

    var errorMessage = mutableStateOf<String?>(null)
        private set

    fun fetchProducts() {
        viewModelScope.launch {
            isLoading.value = true
            errorMessage.value = null
            try {
                products.value = repository.getProducts()
            } catch (e: Exception) {
                errorMessage.value = "Error: ${e.message}"
            } finally {
                isLoading.value = false
            }
        }
    }

    fun addProduct(product: Product, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                repository.addProduct(product)
                fetchProducts()
                onSuccess()
            } catch (e: Exception) {
                errorMessage.value = "Error: ${e.message}"
            }
        }
    }

    fun updateProduct(product: Product, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                repository.updateProduct(product.id, product)
                fetchProducts()
                onSuccess()
            } catch (e: Exception) {
                errorMessage.value = "Error: ${e.message}"
            }
        }
    }

    fun deleteProduct(productId: Int) {
        viewModelScope.launch {
            try {
                repository.deleteProduct(productId)
                fetchProducts()
            } catch (e: Exception) {
                errorMessage.value = "Error: ${e.message}"
            }
        }
    }
}
