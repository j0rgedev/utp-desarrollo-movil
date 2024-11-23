package com.example.pc2.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pc2.data.local.dao.ClientDao
import com.example.pc2.data.local.entity.Client

@Database(
    entities = [Client::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun clientsDao(): ClientDao
}