package com.example.parcial2.horario

import android.app.TimePickerDialog
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
import java.util.Calendar

class AñadirActivity: AppCompatActivity() {
    private lateinit var btnGuardar: Button
    private lateinit var btnCancelar: Button
    private lateinit var editAsignatura: EditText
    private lateinit var spinner: Spinner
    private lateinit var editHoraInicio: EditText
    private lateinit var editHoraFin: EditText
    private val db: FirebaseFirestore = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.anadirh_activity)

        btnGuardar = findViewById(R.id.btnGuardar)
        btnCancelar = findViewById(R.id.btnCancel)
        editAsignatura = findViewById(R.id.editAsignatura)
        spinner = findViewById(R.id.spinner)
        editHoraInicio = findViewById(R.id.editHoraInicio)
        editHoraFin = findViewById(R.id.editHoraFin)

        val dias = listOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, dias)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        editHoraInicio.setOnClickListener {
            mostrarTimePicker { hora, minuto ->
                editHoraInicio.setText(String.format("%02d:%02d", hora, minuto))
            }
        }

        editHoraFin.setOnClickListener {
            mostrarTimePicker { hora, minuto ->
                editHoraFin.setText(String.format("%02d:%02d", hora, minuto))
            }
        }

        btnGuardar.setOnClickListener {
            guardarAsignatura()
        }

        btnCancelar.setOnClickListener {
            finish()
        }

    }

    private fun mostrarTimePicker(onTimeSelected: (Int, Int) -> Unit) {
        val calendario = Calendar.getInstance()
        val horaActual = calendario.get(Calendar.HOUR_OF_DAY)
        val minutoActual = calendario.get(Calendar.MINUTE)

        TimePickerDialog(
            this,
            { _, hora, minuto -> onTimeSelected(hora, minuto) },
            horaActual,
            minutoActual,
            true
        ).show()
    }

    private fun guardarAsignatura(){
        val asignatura = editAsignatura.text.toString()
        val dia = spinner.selectedItem.toString()
        val horaInicio = editHoraInicio.text.toString()
        val horaFin = editHoraFin.text.toString()
        val nuevaAsignatura = Asignatura(asignatura, dia, horaInicio, horaFin)

        db.collection("dbHorario")
            .whereEqualTo("dia", dia)
            .whereEqualTo("hora", horaInicio)
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