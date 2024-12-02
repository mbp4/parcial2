package com.example.parcial2.horario

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.parcial2.R
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class AñadirActivity: AppCompatActivity() {
    private lateinit var btnGuardar: Button
    private lateinit var btnCancelar: Button
    private lateinit var editAsignatura: EditText
    private lateinit var spinner: Spinner
    private lateinit var editAhora: Spinner
    private val db: FirebaseFirestore = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.anadirh_activity)

        btnGuardar = findViewById(R.id.btnGuardar)
        btnCancelar = findViewById(R.id.btnCancel)
        editAsignatura = findViewById(R.id.editAsignatura)
        spinner = findViewById(R.id.spinner)
        editAhora = findViewById(R.id.editAhora)

        val dias = listOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, dias)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        val horas = listOf("08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30",
            "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00")
        val adapterHoras = ArrayAdapter(this, android.R.layout.simple_spinner_item, horas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        editAhora.adapter= adapterHoras

        btnGuardar.setOnClickListener {
            guardarAsignatura()
        }

        btnCancelar.setOnClickListener {
            finish()
        }

    }

    private fun guardarAsignatura(){
        val asignatura = editAsignatura.text.toString()
        val dia = spinner.selectedItem.toString()
        val hora = editAhora.selectedItem.toString()
        val nuevaAsignatura = Asignatura(asignatura, dia, hora)

        db.collection("dbHorario")
            .whereEqualTo("dia", dia)
            .whereEqualTo("hora", hora)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (querySnapshot.isEmpty) {
                    db.collection("dbHorario")
                        .add(nuevaAsignatura)
                        .addOnSuccessListener {
                            Toast.makeText(this, "Asignatura guardada: ${nuevaAsignatura.nombre}", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(this, "Error al guardar la asignatura: ${e.message}", Toast.LENGTH_SHORT).show()
                        }
                } else {
                    Toast.makeText(this, "Ya existe una clase en este día y hora", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al verificar el horario: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}