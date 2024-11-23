package com.example.finalapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.finalapp.data.local.entity.Teacher
import kotlinx.coroutines.flow.Flow

@Dao
interface TeacherDao {
    @Query("SELECT * FROM tb_teacher")
    fun getAllTeachers(): Flow<List<Teacher>>

    @Query("SELECT * FROM tb_teacher WHERE code = :id")
    suspend fun getTeacherById(id: Int): Teacher?

    @Insert()
    suspend fun insertTeacher(teacher: Teacher)

    @Update
    suspend fun updateTeacher(teacher: Teacher)

    @Delete
    suspend fun deleteTeacher(teacher: Teacher)
}