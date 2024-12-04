package com.example.parcial2.farmacia

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.parcial2.MainActivity
import com.example.parcial2.R
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class Iniciof: AppCompatActivity() {
    private lateinit var lista: ListView
    private lateinit var farmaciaAdapter: FarmaciaAdapter
    private var listaFarmacias: MutableList<Farmacia> = mutableListOf()
    private val db: FirebaseFirestore = Firebase.firestore
    private lateinit var btnInicial: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.iniciof_activity)

        lista = findViewById(R.id.listView2)
        farmaciaAdapter = FarmaciaAdapter(this, listaFarmacias)
        lista.adapter = farmaciaAdapter
        btnInicial = findViewById(R.id.btnInicial2)

        mostrarFarmacias()

        btnInicial.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        mostrarFarmacias()
    }

    private fun mostrarFarmacias(){
        db.collection("dbFarmacia")
            .get()
            .addOnSuccessListener { documentos ->
                listaFarmacias.clear()
                for (documento in documentos) {
                    val farmacia = documento.toObject(Farmacia::class.java)
                    listaFarmacias.add(farmacia)
                    Log.d(TAG, "${documento.id} => ${documento.data}")
                }
                farmaciaAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener({ exception ->
                Toast.makeText(this, "Error al obtener las farmacias de la base de datos", Toast.LENGTH_SHORT).show()
                Log.w(TAG, "Error al obtener las farmacias de la base de datos", exception)
            })
    }

}