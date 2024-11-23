package com.example.repaso_pc3.data.remote.api

import com.example.repaso_pc3.data.remote.model.Product
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("products")
    suspend fun getProducts(): List<Product>

    @POST("products")
    suspend fun addProduct(@Body product: Product): Product

    @PUT("products/{id}")
    suspend fun updateProduct(@Path("id") id: Int, @Body product: Product): Product

    @DELETE("products/{id}")
    suspend fun deleteProduct(@Path("id") id: Int)
}