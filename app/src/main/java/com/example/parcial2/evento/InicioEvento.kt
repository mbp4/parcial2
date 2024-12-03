package com.example.parcial2.evento

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial2.R
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class InicioEvento: AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var eventoadapter: EventoAdapter
    private var listado: MutableList<Evento> = mutableListOf()
    private lateinit var btnAñadir: ImageButton
    private val db: FirebaseFirestore = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inicioe_activity)

        listView = findViewById(R.id.listView)
        btnAñadir = findViewById(R.id.btnAñadir)
        eventoadapter = EventoAdapter(this, listado)
        listView.adapter = eventoadapter

        btnAñadir.setOnClickListener {
            val intent = Intent(this, AñadirEvento::class.java)
            startActivity(intent)
        }

        mostrarEventos()
    }

    override fun onResume() {
        super.onResume()
        mostrarEventos()
    }

    private fun mostrarEventos(){
        db.collection("dbEvento")
            .get()
            .addOnSuccessListener { documentos ->
                listado.clear()
                for (documento in documentos) {
                    val evento = documento.toObject(Evento::class.java)
                    listado.add(evento)
                    Log.d(TAG, "${documento.id} => ${documento.data}")
                }
                eventoadapter.notifyDataSetChanged()
            }
            .addOnFailureListener({ exception ->
                Toast.makeText(this, "Error al obtener los eventos de la base de datos", Toast.LENGTH_SHORT).show()
                Log.w(TAG, "Error al obtener loes eventos de la base de datos", exception)
            })
    }


}