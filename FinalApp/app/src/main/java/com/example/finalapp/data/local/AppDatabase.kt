package com.example.finalapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.finalapp.data.local.dao.TeacherDao
import com.example.finalapp.data.local.entity.Teacher

@Database(
    entities = [Teacher::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun teachersDao(): TeacherDao
}