package com.example.finalapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_teacher")
data class Teacher(
    @PrimaryKey(autoGenerate = true)
    val code: Int = 0,
    val names: String = "",
    val fathersLastName: String = "",
    val mothersLastName: String = "",
    val sex: String = "",
    val income: Double = 0.0,
    val children: Int = 0,
    val photo: String = "",
)