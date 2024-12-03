package com.example.parcial2.evento

import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.parcial2.R
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import java.util.Calendar

class AñadirEvento: AppCompatActivity() {
    private lateinit var btnGuardar: Button
    private lateinit var btnCerrar: Button
    private lateinit var editNombre: EditText
    private lateinit var textDescripcion: EditText
    private lateinit var editDireccion: EditText
    private lateinit var editPrecio: EditText
    private lateinit var editFecha: EditText
    private lateinit var editAforo: EditText
    private val db: FirebaseFirestore = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.anadire_activity)

        btnGuardar = findViewById(R.id.btnGuardar2)
        btnCerrar = findViewById(R.id.btnCerrar)
        editNombre = findViewById(R.id.editNombre)
        textDescripcion = findViewById(R.id.textDescripcion)
        editDireccion = findViewById(R.id.editDireccion)
        editPrecio = findViewById(R.id.editPrecio)
        editFecha = findViewById(R.id.editFecha)
        editAforo = findViewById(R.id.editAforo)

        btnGuardar.setOnClickListener {
            guardarEvento()
        }

        btnCerrar.setOnClickListener {
            finish()
        }

        editFecha.inputType = InputType.TYPE_NULL
        editFecha.isFocusable = false

        editFecha.setOnClickListener {
            mostrarCalendario()
        }

    }

    fun mostrarCalendario() {
        val dialog = AlertDialog.Builder(this)
        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.HORIZONTAL

        val pickerDia = NumberPicker(this)
        pickerDia.minValue = 1
        pickerDia.maxValue = 31
        pickerDia.value = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

        val pickerMes = NumberPicker(this)
        pickerMes.minValue = 1
        pickerMes.maxValue = 12
        pickerMes.value = Calendar.getInstance().get(Calendar.MONTH) + 1

        val pickerAño = NumberPicker(this)
        val añoActual = Calendar.getInstance().get(Calendar.YEAR)
        pickerAño.minValue = 1000
        pickerAño.maxValue = añoActual + 100
        pickerAño.value = añoActual

        layout.addView(pickerDia)
        layout.addView(pickerMes)
        layout.addView(pickerAño)

        dialog.setTitle("Selecciona una fecha")
        dialog.setView(layout)

        dialog.setPositiveButton("Aceptar") { _, _ ->
            val dia = pickerDia.value
            val mes = pickerMes.value
            val año = pickerAño.value
            val fechaSeleccionada = "$dia/$mes/$año"
            editFecha.setText(fechaSeleccionada)
        }

        dialog.setNegativeButton("Cancelar", null)
        dialog.show()
    }


    private fun guardarEvento(){
        val nombre = editNombre.text.toString()
        val descripcion = textDescripcion.text.toString()
        val direccion = editDireccion.text.toString()
        val precio = editPrecio.text.toString()
        val fecha = editFecha.text.toString()
        val aforo = editAforo.text.toString().toInt()
        val eventoNuevo = Evento(nombre, descripcion, direccion, precio, fecha, aforo)

        db.collection("dbEvento")
            .add(eventoNuevo)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(this, "Evento guardado: ${eventoNuevo.nombre}", Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al guardar el evento: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

}