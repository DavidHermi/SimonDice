package com.example.simondiceappdifinitiva



import Entities
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Entities::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){

    abstract fun DatosDAO():DatosDAO


    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "myDatabase.db"
                    ).allowMainThreadQueries().build()
                }
            }
            return INSTANCE!!
        }
    }

}
