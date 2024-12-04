package com.example.parcial2.farmacia

import android.graphics.Bitmap
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.parcial2.R

class Detalles:AppCompatActivity(){
    private lateinit var mapa: ImageView
    private var latitud: Double = 0.0
    private var longitud: Double = 0.0
    private lateinit var nombre: String
    private lateinit var btnVolver: Button
    private lateinit var nombreFarmacia: TextView
    private lateinit var link: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detallesf_activity)

        mapa = findViewById(R.id.imageView3)
        nombreFarmacia = findViewById(R.id.idMapa)

        latitud = intent.getDoubleExtra("latitude", 0.0)
        longitud = intent.getDoubleExtra("longitude", 0.0)
        nombre = intent.getStringExtra("nombre") ?: ""
        link = intent.getStringExtra("link") ?: ""

        nombreFarmacia.text = nombre

        btnVolver = findViewById(R.id.btnVolver3)

        btnVolver.setOnClickListener {
            finish()
        }

        mapa.setImageResource(R.drawable.mapa)

    }

}

