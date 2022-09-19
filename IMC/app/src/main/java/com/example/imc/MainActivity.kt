package com.example.imc

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPref: SharedPreferences;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPref = getSharedPreferences("com.example.IMC.PERFIL", Context.MODE_PRIVATE) ?: return

        val pesoAtualizado = recuperaPeso()
        val alturaAtualizado = recuperaAltura()
        val IMCAtualizado = recuperaIMC()
        val situacaoAtualizado = recuperaSituacao()
        if(pesoAtualizado != null && pesoAtualizado != "") {
            findViewById<TextView>(R.id.peso).text = pesoAtualizado
            findViewById<TextView>(R.id.altura).text = alturaAtualizado

            val resultadoIMC = findViewById<TextView>(R.id.resultadoCal)
            val resultadoSituacao = findViewById<TextView>(R.id.situacao)

            resultadoIMC.text = IMCAtualizado
            resultadoSituacao.text = situacaoAtualizado
            resultadoIMC.visibility = View.VISIBLE
            resultadoSituacao.visibility = View.VISIBLE
        }

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

            with(sharedPref.edit()) {
                putString("PESO", peso.text.toString())
                putString("ALTURA", altura.text.toString())
                putString("IMC", imc.toString())
                putString("SITUACAO", situacao)
                commit()
            }

            resultadoIMC.setText(imc.toString())
            resultadoSituacao.setText(situacao)
            resultadoIMC.visibility = View.VISIBLE
            resultadoSituacao.visibility = View.VISIBLE
        }
    }

    fun recuperaPeso() : String? {
        return sharedPref.getString("PESO", null)
    }

    fun recuperaAltura() : String? {
        return sharedPref.getString("ALTURA", null)
    }

    fun recuperaIMC() : String? {
        return sharedPref.getString("IMC", null)
    }

    fun recuperaSituacao() : String? {
        return sharedPref.getString("SITUACAO", null)
    }
}