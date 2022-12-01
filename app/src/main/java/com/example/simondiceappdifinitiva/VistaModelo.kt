package com.example.simondiceappdifinitiva

import android.widget.Button
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class VistaModelo  : ViewModel() {

    val secuenciaJ = MutableLiveData<MutableList<Int>>()
    val secuenciaU = MutableLiveData<MutableList<Int>>()
    val gameState = MutableLiveData<Boolean>()

    init {
        secuenciaU.value = mutableListOf<Int>()
        secuenciaJ.value = mutableListOf<Int>()
        gameState.value = true
    }

    fun init_game() {
        gameState.value = false;
        reset()
        addToSecu()
    }

    private fun addToSecu() {
        val numb = Random.nextInt(4) + 1
        //val numb = 1;
        secuenciaJ.value?.add(numb)
        secuenciaJ.postValue(secuenciaJ.value)
    }

    fun checkSec(): Boolean {
        var ret = false
        if (secuenciaJ.value == secuenciaU.value && gameState.value == false) {
            addToSecu()
            secuenciaU.value?.clear()
            ret = true;
        } else {
            gameState.value = true;
        }
        return ret;
    }

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