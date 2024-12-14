package com.example.afinal.data.repository

import com.example.afinal.data.remote.api.ApiService
import com.example.afinal.ui.data.remote.RetrofitClient
import com.example.afinal.data.remote.model.Post

class PostRepository {
    private val apiService = RetrofitClient.apiService

    suspend fun getPostById(id: Int): Post {
        return apiService.getPostById(id)
    }
}