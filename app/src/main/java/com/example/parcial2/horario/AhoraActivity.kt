package com.example.parcial2.horario

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.parcial2.R
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class AhoraActivity: AppCompatActivity() {
    private lateinit var btnVolver: Button
    private lateinit var textFecha: TextView
    private lateinit var textClase: TextView
    private val db: FirebaseFirestore = Firebase.firestore
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ahorah_activity)

        btnVolver = findViewById(R.id.btnVolver2)
        textFecha = findViewById(R.id.textFecha)
        textClase = findViewById(R.id.textClase)

        btnVolver.setOnClickListener {
            finish()
        }

        actualizar()

        handler.postDelayed(object : Runnable {
            override fun run() {
                comprobarClase()
                handler.postDelayed(this, 60000)
            }
        },0)

    }

    private fun actualizar() {
        val fecha = SimpleDateFormat("EEEE, d 'de' MMMM yyyy HH:mm:ss", Locale.getDefault()).format(Date())
        textFecha.text = fecha
    }

    private fun comprobarClase() {
        val horaActual = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
        val diaActual = SimpleDateFormat("EEEE", Locale.getDefault()).format(Date())
        val horaActualDate = SimpleDateFormat("HH:mm", Locale.getDefault()).parse(horaActual)

        val diaTraducido = when (diaActual.lowercase()) {
            "monday" -> "Lunes"
            "tuesday" -> "Martes"
            "wednesday" -> "Miércoles"
            "thursday" -> "Jueves"
            "friday" -> "Viernes"
            else -> "Sin clase"
        }

        db.collection("dbHorario")
            .whereEqualTo("dia", diaTraducido)
            .get()
            .addOnSuccessListener { querySnapshot ->
                var claseEncontrada = false
                querySnapshot.documents.forEach { document ->
                    val horaInicio = document.getString("horaInicio")
                    val horaFin = document.getString("horaFin")
                    val asignatura = document.getString("nombre")

                    val horaInicioDate = SimpleDateFormat("HH:mm", Locale.getDefault()).parse(horaInicio)
                    val horaFinDate = SimpleDateFormat("HH:mm", Locale.getDefault()).parse(horaFin)

                    if (horaActualDate != null && horaInicioDate != null && horaFinDate != null &&
                        horaActualDate.after(horaInicioDate) && horaActualDate.before(horaFinDate)) {
                        textClase.text = "Estás en clase de: $asignatura"
                        claseEncontrada = true
                        return@forEach
                    }
                }

                if (!claseEncontrada) {
                    textClase.text = "No hay clases ahora"
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error al consultar el horario", Toast.LENGTH_SHORT).show()
            }
    }


    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }

}