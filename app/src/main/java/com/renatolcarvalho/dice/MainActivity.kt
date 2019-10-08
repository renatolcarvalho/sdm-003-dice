package com.renatolcarvalho.dice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlin.random.Random
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var random: Random? = null
    private var listaNumerosSorteados: ArrayList<Int>? = null
    private var sorteadosTextView: TextView? = null
    private var sorteadoTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        random = Random(System.currentTimeMillis())
        listaNumerosSorteados = ArrayList()
        sorteadosTextView = findViewById(R.id.sorteadosTextView)
        sorteadoTextView = findViewById(R.id.sorteadoTextView)
    }

    fun sortear(botao: View) {
        if (botao.getId() === R.id.sortearButton) {

            if (listaNumerosSorteados!!.size < 75) {

                var numero: Int? = random!!.nextInt(75) + 1
                while (listaNumerosSorteados!!.contains(numero)) {
                    numero = random!!.nextInt(75) + 1
                }

                if (numero != null)
                    listaNumerosSorteados!!.add(numero)

                sorteadosTextView!!.text = sorteadosTextView!!.text.toString() + numero!!.toString() + " "

                var letra = ""
                when (numero) {
                    in 1..15 -> letra = "B"
                    in 16..30 -> letra = "I"
                    in 31..45 -> letra = "N"
                    in 46..60 -> letra = "G"
                    in 61..75 -> letra = "O"
                }
                sorteadoTextView!!.text = letra + numero
            } else {
                Toast.makeText(this, "Todos os números foram sorteados", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun reiniciar(botao: View) {
        if (botao.id == R.id.reiniciarButton) {
            listaNumerosSorteados!!.clear()
            sorteadosTextView!!.text = null
            sorteadoTextView!!.text = null
            Toast.makeText(this, "Sorteio reiniciado", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("sorteadosTxt", sorteadosTextView!!.text.toString())
        outState.putString("sorteadoTxt", sorteadoTextView!!.text.toString())
        outState.putIntegerArrayList("listaNumerosSorteados", listaNumerosSorteados)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState != null) {
            sorteadosTextView!!.text = savedInstanceState.getString("sorteadosTxt", null)
            sorteadoTextView!!.text = savedInstanceState.getString("sorteadoTxt", null)
            listaNumerosSorteados = savedInstanceState.getIntegerArrayList("listaNumerosSorteados")
        }
    }
}
