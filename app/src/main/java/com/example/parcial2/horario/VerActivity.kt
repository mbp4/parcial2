package com.example.parcial2.horario

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial2.R
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class VerActivity: AppCompatActivity() {
    private lateinit var spinnerDia: Spinner
    private lateinit var listViewHorario: ListView
    private val db = FirebaseFirestore.getInstance()
    private val asignaturas = mutableListOf<String>()
    private lateinit var btnVolver: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.verh_activity)

        btnVolver = findViewById(R.id.btnVolver)
        spinnerDia = findViewById(R.id.spinner)
        listViewHorario = findViewById(R.id.listViewHorario)

        val dias = listOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, dias)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerDia.adapter = spinnerAdapter

        val listAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, asignaturas)
        listViewHorario.adapter = listAdapter

        btnVolver.setOnClickListener {
            finish()
        }

        spinnerDia.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val diaSeleccionado = dias[position]
                cargarHorario(diaSeleccionado, listAdapter)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun cargarHorario(dia: String, listAdapter: ArrayAdapter<String>) {
        db.collection("dbHorario")
            .whereEqualTo("dia", dia)
            .get()
            .addOnSuccessListener { querySnapshot ->
                asignaturas.clear()
                if (!querySnapshot.isEmpty) {
                    querySnapshot.documents.forEach { document ->
                        val asignatura = document.getString("nombre")
                        val hora = document.getString("hora")
                        asignaturas.add("$asignatura - $hora")
                    }
                    listAdapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(this, "No hay clases para $dia", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error al cargar el horario", Toast.LENGTH_SHORT).show()
            }
    }

}