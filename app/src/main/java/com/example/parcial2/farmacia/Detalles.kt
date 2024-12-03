package com.example.parcial2.farmacia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.parcial2.R
import com.google.android.gms.maps.MapView

class Detalles:AppCompatActivity() {
    private lateinit var mapa: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detallesf_activity)

        mapa = findViewById(R.id.mapView)
    }
}