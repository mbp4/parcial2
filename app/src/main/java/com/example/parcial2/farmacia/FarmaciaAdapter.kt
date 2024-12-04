package com.example.parcial2.farmacia

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import com.example.parcial2.R

class FarmaciaAdapter(context: Context, private val farmacias: List<Farmacia>) : ArrayAdapter<Farmacia>(context, R.layout.itemf_activity, farmacias) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(context)
        val view = convertView ?: inflater.inflate(R.layout.itemf_activity, parent, false)

        val textNombre = view.findViewById<TextView>(R.id.textNombreF)
        val textTelefono = view.findViewById<TextView>(R.id.textTelefono)
        val btnDetalles = view.findViewById<Button>(R.id.btnDetalles2)

        val farmacia = farmacias[position]
        textNombre.text = farmacia.title
        val regexTelefono = """Teléfono:\s*(\d+)""".toRegex()
        val telefono = regexTelefono.find(farmacia.description)?.groupValues?.get(1) ?: "Teléfono no disponible"
        textTelefono.text = telefono

        btnDetalles.setOnClickListener {
            val intent = Intent(context, Detalles::class.java)
            intent.putExtra("latitude", farmacia.latitude)
            intent.putExtra("longitude", farmacia.longitude)
            intent.putExtra("nombre", farmacia.title)
            intent.putExtra("link", farmacia.link)
            context.startActivity(intent)
        }

        return view
    }
}