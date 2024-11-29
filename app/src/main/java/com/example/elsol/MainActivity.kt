package com.example.elsol

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    //Listado con cada elemento del Sol
    private val solarImages = mutableListOf(
        TarjetaSol(R.drawable.corona_solar, "corona solar"),
        TarjetaSol(R.drawable.erupcionsolar, "erupción solar"),
        TarjetaSol(R.drawable.espiculas, "espículas"),
        TarjetaSol(R.drawable.filamentos, "filamentos"),
        TarjetaSol(R.drawable.magnetosfera, "magnetosfera"),
        TarjetaSol(R.drawable.manchasolar, "mancha solar")
    )

    private lateinit var adapter: TarjetaSolAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbarSol)
        setSupportActionBar(toolbar)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        adapter = TarjetaSolAdapter(solarImages) { menuItem, solarImage ->
            handleMenuAction(menuItem.itemId, solarImage)
        }
        recyclerView.adapter = adapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_app, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.comparar -> {
                val intent = Intent(this, CompararPlanetas::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun handleMenuAction(actionId: Int, solarImage: TarjetaSol) {
        when (actionId) {
            //Opción renombrar
            R.id.rename -> {
                val position = solarImages.indexOf(solarImage)
                if (position != -1) {
                    solarImages[position] = solarImage.copy(nombre = "Nuevo Nombre")
                    adapter.notifyItemChanged(position)
                }
            }
            // Opción copiar
            R.id.copy -> {
                val copiedItem = solarImage.copy()
                val originalIndex = solarImages.indexOf(solarImage)

                if (originalIndex != -1) {
                    solarImages.add(originalIndex + 1, copiedItem)
                    adapter.notifyItemInserted(originalIndex + 1)
                }
            }

            //Opción cortar
            R.id.cut -> {
                val position = solarImages.indexOf(solarImage)
                if (position != -1) {
                    solarImages.removeAt(position)
                    adapter.notifyItemRemoved(position)
                }
            }

            //Opción eliminar
            R.id.delete -> {
                val position = solarImages.indexOf(solarImage)
                if (position != -1) {
                    solarImages.removeAt(position)
                    adapter.notifyItemRemoved(position)
                }
            }

            //Opción mover
            R.id.move -> {
                val currentPosition = solarImages.indexOf(solarImage)
                if (currentPosition != -1) {
                    val item = solarImages.removeAt(currentPosition)
                    solarImages.add(item) // Añadir al final
                    adapter.notifyItemMoved(currentPosition, solarImages.size - 1)
                }
            }
        }
    }
}