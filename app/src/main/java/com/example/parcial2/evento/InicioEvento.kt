package com.example.parcial2.evento

import android.content.ContentValues.TAG
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ListView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial2.MainActivity
import com.example.parcial2.R
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import java.util.Locale

class InicioEvento: AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var eventoadapter: EventoAdapter
    private var listado: MutableList<Evento> = mutableListOf()
    private lateinit var btnAñadir: ImageButton
    private lateinit var btnIdioma: ToggleButton
    private val db: FirebaseFirestore = Firebase.firestore
    private lateinit var btnInicial3: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inicioe_activity)

        val preferences = getSharedPreferences("ajustes", MODE_PRIVATE)
        val language = preferences.getString("idioma", "es") ?: "es"
        cambiarIdioma(language)

        listView = findViewById(R.id.listView)
        btnAñadir = findViewById(R.id.btnAñadir)
        eventoadapter = EventoAdapter(this, listado)
        listView.adapter = eventoadapter
        btnIdioma = findViewById(R.id.btnIdioma)
        btnInicial3 = findViewById(R.id.btnInicial3)

        btnAñadir.setOnClickListener {
            val intent = Intent(this, AñadirEvento::class.java)
            startActivity(intent)
        }

        btnIdioma.setOnClickListener {
            if (btnIdioma.isChecked) cambiarIdioma("es") else cambiarIdioma("en")
        }

        btnInicial3.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        mostrarEventos()
    }

    private fun cambiarIdioma(idioma: String) {
        val currentLanguage = resources.configuration.locales[0].language
        if (currentLanguage != idioma) {
            val locale = Locale(idioma)
            Locale.setDefault(locale)
            val config = Configuration(resources.configuration)
            config.setLocale(locale)
            resources.updateConfiguration(config, resources.displayMetrics)

            val preferences = getSharedPreferences("ajustes", MODE_PRIVATE)
            preferences.edit().putString("idioma", idioma).apply()

            recreate()
        }
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