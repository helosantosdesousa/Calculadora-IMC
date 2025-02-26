package com.helosantosdesousa.calculadoraimc

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.helosantosdesousa.calculadoraimc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btCalcular.setOnClickListener{
            calcular()
        }

    }

    private fun calcular(){
        val peso = binding.etPeso.text.toString().toDouble()
        val altura = binding.etAltura.text.toString().toDouble()

        val imc = peso/(altura*altura)

        when(imc){
            in 0.0 .. 18.5 -> configuraIMC(imc, R.drawable.masc_abaixo, R.string.magreza)
            in 18.6 .. 24.9 -> configuraIMC(imc, R.drawable.masc_ideal, R.string.peso_normal)
            in 25.0 .. 29.9 -> configuraIMC(imc, R.drawable.masc_sobre, R.string.sobre_peso)
            in 30.0 .. 34.9 -> configuraIMC(imc, R.drawable.masc_obeso, R.string.obesidade_grau_i)
            in 35.0 .. 39.9 -> configuraIMC(imc, R.drawable.masc_extremo_obeso, R.string.obesidade_grau_ii)
            else -> configuraIMC(imc, R.drawable.masc_extremo_obeso, R.string.obesidade_grau_iii)
        }
    }

    private fun configuraIMC(imc: Double, drawableID: Int, stringId: Int){
        binding.tvIMC.text = "Seu IMC é: ${imc}"

        binding.ivIMCStatus.setImageDrawable(
            ContextCompat.getDrawable(this, drawableID)
        )
        binding.tvIMCStatus.text = getString(stringId)
    }


}