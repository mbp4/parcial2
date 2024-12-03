package com.example.parcial2.farmacia

import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.parcial2.R

class Iniciof: AppCompatActivity() {
    private lateinit var lista: ListView
    private lateinit var adapter: FarmaciaAdapter
    private var listaFarmacias: MutableList<Farmacia> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.iniciof_activity)

        lista = findViewById(R.id.listView2)
        adapter = FarmaciaAdapter(this, listaFarmacias)
        lista.adapter = adapter

        btnDetalles.setOnClickListener {
            val intent = Intent(this, Detalles::class.java)
            startActivity(intent)
        }
    }

}