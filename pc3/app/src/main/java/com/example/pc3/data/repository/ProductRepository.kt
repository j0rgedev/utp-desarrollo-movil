package com.example.pc3.data.repository

import com.example.pc3.data.remote.RetrofitClient
import com.example.pc3.data.remote.model.Product

class ProductRepository {
    private val apiService = RetrofitClient.apiService

    suspend fun getProductById(id: Int): Product {
        return apiService.getProductById(id)
    }
}