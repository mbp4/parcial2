package com.example.parcial2.evento

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.parcial2.R

class EventoAdapter(context: Context, private val eventos: List<Evento>) : ArrayAdapter<Evento>(context, R.layout.iteme_activity, eventos) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(context)
        val view = convertView ?: inflater.inflate(R.layout.iteme_activity, parent, false)

        val textNombre = view.findViewById<TextView>(R.id.textEvento)
        val textDescripcion = view.findViewById<TextView>(R.id.textDescripcionEvento)
        val textPrecio = view.findViewById<TextView>(R.id.textPrecio)

        val evento = eventos[position]
        textNombre.text = evento.nombre
        textDescripcion.text = evento.descripcion
        textPrecio.text = "${evento.precio} €"

        return view
    }
}