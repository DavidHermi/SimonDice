package com.example.simondiceappdifinitiva

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var state : Boolean
        val button_red : Button = findViewById(R.id.rojo)
        val button_yellow : Button = findViewById(R.id.amarillo)
        val button_blue : Button = findViewById(R.id.azul)
        val button_green : Button = findViewById(R.id.verde)
        val button_start : Button = findViewById(R.id.start)
        val button_check : Button = findViewById(R.id.check)
        val Btnscolor = listOf(button_red,button_green,button_yellow,button_blue)
        val toast = Toast.makeText(applicationContext,"Juego terminado", Toast.LENGTH_SHORT)
        val toast2 = Toast.makeText(applicationContext,"Inicio", Toast.LENGTH_SHORT)


        val ModeloJuego by viewModels<VistaModelo>()

        ModeloJuego.gameState.observe(this, Observer{
                gs -> state = gs
            if(state) {
                button_red.setEnabled(false)
                button_yellow.setEnabled(false)
                button_blue.setEnabled(false)
                button_green.setEnabled(false)
                button_check.setEnabled(false)
            }else{
                button_red.setEnabled(true)
                button_yellow.setEnabled(true)
                button_blue.setEnabled(true)
                button_green.setEnabled(true)
                button_check.setEnabled(true)
            }
        })

        ModeloJuego.secuenciaJ.observe(this, Observer{
            ModeloJuego.showSec(Btnscolor)
        })

        button_start.setOnClickListener{
            toast2.show()
            ModeloJuego.init_game()
        }

        button_check.setOnClickListener{
            if(!ModeloJuego.checkSec()){
                toast.show()
            }
        }
        button_red.setOnClickListener{
            ModeloJuego.addUserSec(1)
        }
        button_green.setOnClickListener{
            ModeloJuego.addUserSec(2)
        }
        button_yellow.setOnClickListener{
            ModeloJuego.addUserSec(3)
        }
        button_blue.setOnClickListener{
            ModeloJuego.addUserSec(4)
        }
    }
    }
