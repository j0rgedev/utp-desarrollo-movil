package com.example.pc2.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pc2.data.local.entity.Client
import kotlinx.coroutines.flow.Flow

@Dao
interface ClientDao {
    @Query("SELECT * FROM tb_client")
    fun findAll(): Flow<List<Client>>

    @Query("SELECT * FROM tb_client WHERE code = :id")
    suspend fun findById(id: Int): Client?

    @Insert()
    suspend fun add(client: Client)

    @Query("SELECT * FROM tb_client WHERE lastNames LIKE '%' || :lastNames || '%'")
    suspend fun searchClient(lastNames: String): List<Client>

    @Query("SELECT COUNT(1) FROM tb_client WHERE dni = :dni")
    suspend fun existsClient(dni: String): Int
}