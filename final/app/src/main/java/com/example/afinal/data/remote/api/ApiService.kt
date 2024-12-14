package com.example.afinal.data.remote.api

import com.example.afinal.data.remote.model.Post
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("posts/{id}")
    suspend fun getPostById(@Path("id") id: Int): Post
}