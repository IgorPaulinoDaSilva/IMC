package com.example.imc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botao = findViewById<Button>(R.id.botao)
        botao.setOnClickListener {
            val peso = findViewById<EditText>(R.id.peso)
            val altura = findViewById<EditText>(R.id.altura)
            val resultadoIMC = findViewById<TextView>(R.id.resultadoCal)
            val resultadoSituacao = findViewById<TextView>(R.id.situacao)

            var imc = peso.text.toString().toFloat() / (altura.text.toString().toFloat() * altura.text.toString().toFloat())
            imc = ((imc * 100.0).roundToInt() / 100.0).toFloat()
            var situacao = ""

            if (imc < 18.5)
            {
                situacao = "Magreza"
            }
            else if (imc >= 18.5 && imc <= 24.9)
            {
                situacao = "Normal"
            }
            else if (imc >= 25 && imc <= 29.9)
            {
                situacao = "Sobrepeso"
            }
            else if (imc >= 30 && imc <= 34.9)
            {
                situacao = "Obesidade grau I"
            }
            else if (imc >= 35 && imc <= 39.9)
            {
                situacao = "Obesidade grau II"
            }
            else
            {
                situacao = "Obesidade grau III"
            }

            resultadoIMC.setText(imc.toString())
            resultadoSituacao.setText(situacao)
            resultadoIMC.visibility = View.VISIBLE
            resultadoSituacao.visibility = View.VISIBLE
        }
    }
}