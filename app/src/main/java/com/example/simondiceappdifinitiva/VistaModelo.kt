package com.example.simondiceappdifinitiva


import android.app.Application
import android.util.Log
import android.widget.Button
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import kotlin.random.Random

class VistaModelo (application: Application) : AndroidViewModel(application) {

    /**
     * variables
     */
    private var ronda = 1     //número de ronda
    var liveRonda = MutableLiveData<Int>()
    private var record = 1 //numero record
    var liveRecord = MutableLiveData<Int>()
    val secuenciaJ = MutableLiveData<MutableList<Int>>()
    val secuenciaU = MutableLiveData<MutableList<Int>>()
    val gameState = MutableLiveData<Boolean>()

    val context = getApplication<Application>().applicationContext


    private lateinit var fireBaseR: DatabaseReference

    /**
     * Initi donde se ponen las diferencias secuencias que se van hacer,
     * y todo lo relacionado con el sqlite
     */

    init {
        secuenciaU.value = mutableListOf<Int>()
        secuenciaJ.value = mutableListOf<Int>()
        gameState.value = true
        liveRonda.value = ronda
        liveRecord.value = record
        fireBaseR =
            Firebase.database("https://simondice-65f90-default-rtdb.europe-west1.firebasedatabase.app/")
                .getReference("record")
        //Defino el listener del record
        val recordListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                liveRecord.value = dataSnapshot.getValue<Int>()
                Log.d("RecFirebase", liveRecord.value.toString())
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("ReaLTime", "recordListener:OnCancelled", error.toException())
            }
        }
        //Añado el listener a la BD
        fireBaseR.addValueEventListener(recordListener)
    }

    /**
     * inicia el juego
     */
        fun init_game() {

        gameState.value = false;
        reset()
        addToSecu()

    }

    /**
     * Añade a la funcion un color aleatorio con un random
     */
    private fun addToSecu() {
        val numb = Random.nextInt(4) + 1
        //val numb = 1;
        secuenciaJ.value?.add(numb)
        secuenciaJ.postValue(secuenciaJ.value)
    }

    /**
     * Comprueba la secuencia
     */
    fun checkSec(): Boolean {
        var ret = false
        if (secuenciaJ.value == secuenciaU.value && gameState.value == false) {
            addToSecu()
            secuenciaU.value?.clear()
            ret = true;
            ronda++
            liveRonda.value = ronda
            actualizarRecord()
        } else {
            ronda=0
            gameState.value = true;
        }
        return ret;



    }


    /**
     * Funcion para actualizar el record que se llama en la funcion de chequear la secuencia
     */
    fun actualizarRecord() {
        if (record < ronda) {
            liveRecord.value = liveRonda.value
            fireBaseR.setValue(liveRecord.value)
            Log.d("FireAct", liveRecord.toString())
        }
    }

    /**
     * Funcion para reiniciar la secuencia
     */
    private fun reset() {
        secuenciaJ.value?.clear()
        secuenciaU.value?.clear()
    }

    fun addUserSec(color: Int) {
        when (color) {
            1 -> secuenciaU.value?.add(1)
            2 -> secuenciaU.value?.add(2)
            3 -> secuenciaU.value?.add(3)
            else -> secuenciaU.value?.add(4)
        }
    }

    fun getSec(): MutableList<Int> {
        return secuenciaJ.value!!
    }
    /**
     * Funcion que enseña la secuencia
     */
    fun showSec(listButton: List<Button>) {
        CoroutineScope(Dispatchers.Main).launch {
            for (colors in secuenciaJ.value!!) {
                listButton.get(colors-1).setPressed(true)
                delay(1000)
                listButton.get(colors-1).setPressed(false)
            }
        }
    }










}