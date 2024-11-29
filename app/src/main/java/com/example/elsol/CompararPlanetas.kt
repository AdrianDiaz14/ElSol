package com.example.elsol

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity

class CompararPlanetas : AppCompatActivity() {

    // Mapa con la información de los planetas
    private val planetasData = mapOf(
        "Mercurio" to TarjetaPlaneta(0.382, 0.387, 5400),
        "Venus" to TarjetaPlaneta(0.949, 0.723, 5250),
        "Tierra" to TarjetaPlaneta(1.0, 1.0, 5520),
        "Marte" to TarjetaPlaneta(0.53, 1.542, 3960),
        "Júpiter" to TarjetaPlaneta(11.2, 5.203, 1350),
        "Saturno" to TarjetaPlaneta(9.41, 9.539, 700),
        "Urano" to TarjetaPlaneta(3.38, 19.81, 1200),
        "Neptuno" to TarjetaPlaneta(3.81, 30.07, 1500),
        "Plutón" to TarjetaPlaneta(0.0, 39.44, 0)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.compara_planetas)

        // Configura la Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbarComparar)
        setSupportActionBar(toolbar)

        val planetas = arrayOf("Mercurio", "Venus", "Tierra", "Marte", "Jupiter", "Saturno", "Urano", "Neptuno", "Pluton")

        val autoCompletePlaneta1 = findViewById<AutoCompleteTextView>(R.id.autoCompletePlaneta1)
        val adaptador1 = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, planetas)
        autoCompletePlaneta1.setAdapter(adaptador1)
        autoCompletePlaneta1.threshold = 1 // Mostrar sugerencias después de 1 carácter

        val autoCompletePlaneta2 = findViewById<AutoCompleteTextView>(R.id.autoCompletePlaneta2)
        val adaptador2 = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, planetas)
        autoCompletePlaneta2.setAdapter(adaptador2)
        autoCompletePlaneta2.threshold = 1

        // Obtener los TextViews donde se mostrará la información
        val diametroPlaneta1 = findViewById<TextView>(R.id.diametroPlaneta1)
        val diametroPlaneta2 = findViewById<TextView>(R.id.diametroPlaneta2)
        val distanciaPlaneta1 = findViewById<TextView>(R.id.distanciaPlaneta1)
        val distanciaPlaneta2 = findViewById<TextView>(R.id.distanciaPlaneta2)
        val densidadPlaneta1 = findViewById<TextView>(R.id.densidadPlaneta1)
        val densidadPlaneta2 = findViewById<TextView>(R.id.densidadPlaneta2)

        // Setear los listeners para AutoCompleteTextViews
        autoCompletePlaneta1.setOnItemClickListener { _, _, position, _ ->
            val planetaSeleccionado = autoCompletePlaneta1.adapter.getItem(position).toString()
            val data = planetasData[planetaSeleccionado]
            data?.let {
                diametroPlaneta1.text = it.diametro.toString()
                distanciaPlaneta1.text = it.distancia.toString()
                densidadPlaneta1.text = it.densidad.toString()
            }
        }

        autoCompletePlaneta2.setOnItemClickListener { _, _, position, _ ->
            val planetaSeleccionado = autoCompletePlaneta2.adapter.getItem(position).toString()
            val data = planetasData[planetaSeleccionado]
            data?.let {
                diametroPlaneta2.text = it.diametro.toString()
                distanciaPlaneta2.text = it.distancia.toString()
                densidadPlaneta2.text = it.densidad.toString()
            }
        }
    }
}