package com.example.parcial2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.example.parcial2.evento.InicioEvento
import com.example.parcial2.farmacia.Iniciof
import com.example.parcial2.horario.Horario
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : ComponentActivity() {
    private lateinit var btnHorario: Button
    private lateinit var btnListado: Button
    private lateinit var btnFarmacias: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.main_activity)

        btnHorario = findViewById(R.id.button1)
        btnListado = findViewById(R.id.button2)
        btnFarmacias = findViewById(R.id.button3)

        btnHorario.setOnClickListener {
            val intent = Intent(this, Horario::class.java)
            startActivity(intent)
        }

        btnListado.setOnClickListener {
            val intent = Intent(this, InicioEvento::class.java)
            startActivity(intent)
        }

        btnFarmacias.setOnClickListener {
            val intent = Intent(this, Iniciof::class.java)
            startActivity(intent)
        }

    }

    //este metodo se ha utilizado junto a un boton para poner todos los datos en firebase, como solo se necesita realizar una vez lo dejamos sin uso
    private fun loadFarmaciasToFirestore() {
        FirebaseApp.initializeApp(this)
        val firestore = FirebaseFirestore.getInstance()

        val inputStream = resources.openRawResource(R.raw.farmacias_equipamiento)
        val jsonData = BufferedReader(InputStreamReader(inputStream)).use { it.readText() }

        try {
            val jsonObject = JSONObject(jsonData)
            val featuresArray = jsonObject.getJSONArray("features")

            for (i in 0 until featuresArray.length()) {
                val feature = featuresArray.getJSONObject(i)
                val properties = feature.getJSONObject("properties")
                val geometry = feature.getJSONObject("geometry")
                val coordinates = geometry.getJSONArray("coordinates")

                val data = mapOf(
                    "title" to properties.getString("title"),
                    "description" to properties.getString("description"),
                    "link" to properties.getString("link"),
                    "icon" to properties.getString("icon"),
                    "coordinates" to mapOf(
                        "latitude" to coordinates.getDouble(1),
                        "longitude" to coordinates.getDouble(0)
                    )
                )

                firestore.collection("dbFarmacia")
                    .add(data)
                    .addOnSuccessListener { documentRef ->
                        println("Documento añadido con ID: ${documentRef.id}")
                    }
                    .addOnFailureListener { e ->
                        println("Error al añadir documento: $e")
                    }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}
