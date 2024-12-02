package com.example.parcial2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.example.parcial2.evento.InicioEvento
import com.example.parcial2.farmacia.Farmacia
import com.example.parcial2.horario.Horario

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
            val intent = Intent(this, Farmacia::class.java)
            startActivity(intent)
        }
    }
}
