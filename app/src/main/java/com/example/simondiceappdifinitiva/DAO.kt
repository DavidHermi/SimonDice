package com.example.simondiceappdifinitiva

import Entities
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update

@Dao
interface DatosDAO {

    @Query("SELECT MAX (record)  FROM Record ")
    fun getRecord(): Int

    @Query("INSERT INTO Record (record) VALUES (:record)")
     fun guardarRecord(record:Int)

}

