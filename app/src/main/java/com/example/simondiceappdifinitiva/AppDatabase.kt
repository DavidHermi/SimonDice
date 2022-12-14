package com.example.simondiceappdifinitiva



import Entities
import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Entities::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun datosDao(): DatosDAO
}