package com.example.pc2.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_client")
data class Client(
    @PrimaryKey(autoGenerate = true)
    val code: Int = 0,
    var dni: String = "",
    var names: String = "",
    var lastNames: String = "",
    var phoneOperator: String = "",
    var phoneNumber: String = "",
    var photo: Int = 0,
)