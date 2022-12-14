package com.example.simondiceappdifinitiva

import Entities
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update

@Dao
interface DatosDAO {

    @Query("SELECT record FROM Record WHERE id= 1")
    suspend fun getRecord(): Int

    @Query("INSERT INTO Record (record) VALUES (0)")
    suspend fun crearRecord()

    @Update
    suspend fun update(entities: Entities)


    @Delete
    fun delete(user: Entities)

}

