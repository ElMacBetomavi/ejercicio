package com.practica.ventasmoviles.sys.ui.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.practica.ventasmoviles.R
import com.practica.ventasmoviles.data.entities.CategoriaEntity


class CategoriaListAdapter(private val productsList: List<CategoriaEntity>) :
    RecyclerView.Adapter<CategoriaListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView
        val description: TextView

        init {
            // Define click listener for the ViewHolder's View.
            name = view.findViewById(R.id.categoria_item)
            description = view.findViewById(R.id.descripcion_item)

        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CategoriaListAdapter.ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_item_categoria, viewGroup, false)

        return CategoriaListAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: CategoriaListAdapter.ViewHolder, position: Int) {
        viewHolder.name.text = productsList[position].name
        viewHolder.description.text = productsList[position].description

    }

    override fun getItemCount() = productsList.size

}