package com.example.simondiceappdifinitiva


import Entities
import android.app.Application
import android.util.Log
import android.widget.Button
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
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


    var room:AppDatabase? = null

    /**
     * Initi donde se ponen las diferencias secuencias que se van hacer,
     * y todo lo relacionado con el sqlite
     */

    init {
        secuenciaU.value = mutableListOf<Int>()
        secuenciaJ.value = mutableListOf<Int>()
        gameState.value = true
        room = Room
            .databaseBuilder(
                context,
                AppDatabase::class.java, "Record"
            )
            .build()
        //recogerRecord()

        val roomCorrutine = GlobalScope.launch(Dispatchers.Main) {
            try {
                liveRecord.value = room!!.datosDao().getRecord()
                Log.d("recSQLite", liveRecord.value.toString())
            } catch (ex: java.lang.NullPointerException) {
                room!!.datosDao().crearRecord()
                liveRecord.value = room!!.datosDao().getRecord()
            }
        }
        roomCorrutine.start()
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
        }
        val updateCorrutine = GlobalScope.launch(Dispatchers.Main) {

            room!!.datosDao().update(Entities(1,liveRecord.value))
        }
        updateCorrutine.start()
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