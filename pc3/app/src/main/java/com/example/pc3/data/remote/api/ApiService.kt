package com.example.pc3.data.remote.api

import com.example.pc3.data.remote.model.Product
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Product
}