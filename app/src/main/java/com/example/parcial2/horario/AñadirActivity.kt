package com.example.parcial2.horario

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.parcial2.R

class AñadirActivity: AppCompatActivity() {
    private lateinit var btnGuardar: Button
    private lateinit var btnCancelar: Button
    private lateinit var editAsignatura: EditText
    private lateinit var spinner: Spinner
    private lateinit var editAhora: EditText

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
        val hora = editAhora.text.toString()

        if (asignatura.isNotBlank() && hora.isNotBlank()) {
            Toast.makeText(this, "Clase guardada: $asignatura, $dia, $hora", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
        }
    }
}