package com.example.parcial2.farmacia

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.parcial2.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback as onMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class Detalles:AppCompatActivity(), onMapReadyCallback {
    private lateinit var mapa: MapView
    private var latitud: Double = 0.0
    private var longitud: Double = 0.0
    private lateinit var nombre: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detallesf_activity)

        mapa = findViewById(R.id.mapView)
        latitud = intent.getDoubleExtra("latitude", 0.0)
        longitud = intent.getDoubleExtra("longitude", 0.0)
        nombre = intent.getStringExtra("title") ?: ""

        mapa.onCreate(savedInstanceState)
        mapa.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val location = LatLng(latitud, longitud)
        googleMap.addMarker(MarkerOptions().position(location).title(nombre))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
    }

    override fun onResume() {
        super.onResume()
        mapa.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapa.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapa.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapa.onLowMemory()
    }
}