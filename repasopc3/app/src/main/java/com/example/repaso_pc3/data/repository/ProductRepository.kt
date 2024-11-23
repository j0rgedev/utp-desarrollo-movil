package com.example.repaso_pc3.data.repository

import com.example.repaso_pc3.data.remote.RetrofitClient
import com.example.repaso_pc3.data.remote.model.Product

class ProductRepository {
    private val apiService = RetrofitClient.apiService

    suspend fun getProducts(): List<Product> {
        return apiService.getProducts()
    }

    suspend fun addProduct(product: Product): Product {
        return apiService.addProduct(product)
    }

    suspend fun updateProduct(id: Int, product: Product): Product {
        return apiService.updateProduct(id, product)
    }

    suspend fun deleteProduct(id: Int) {
        return apiService.deleteProduct(id)
    }
}