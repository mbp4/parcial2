package com.example.parcial2.horario

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.parcial2.MainActivity
import com.example.parcial2.R

class Horario: AppCompatActivity() {
    private lateinit var btnAñadir: Button
    private lateinit var btnVer: Button
    private lateinit var btnAhora: Button
    private lateinit var btnInicial: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inicioh_activity)

        btnAñadir = findViewById(R.id.btnAdd)
        btnVer = findViewById(R.id.btnVer)
        btnAhora = findViewById(R.id.btnAhora)
        btnInicial = findViewById(R.id.btnInicial)

        btnAñadir.setOnClickListener {
            val intent = Intent(this, AñadirActivity::class.java)
            startActivity(intent)
        }

        btnVer.setOnClickListener {
            val intent = Intent(this, VerActivity::class.java)
            startActivity(intent)
        }

        btnAhora.setOnClickListener {
            val intent = Intent(this, AhoraActivity::class.java)
            startActivity(intent)
        }

        btnInicial.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
