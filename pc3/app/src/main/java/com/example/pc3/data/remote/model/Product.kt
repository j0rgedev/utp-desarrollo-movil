package com.example.pc3.data.remote.model

data class Product(
    val id: Int = 0,
    val title: String = "",
    val price: Double = 0.0,
    val description: String = "",
    val category: String = "",
    val image: String = "",
    val rating: ProductRating = ProductRating()
)

data class ProductRating(
    val rate: Double = 0.0,
    val count: Int = 0
)