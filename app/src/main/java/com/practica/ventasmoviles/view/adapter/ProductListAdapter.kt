package com.practica.ventasmoviles.view.adapter
import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.practica.ventasmoviles.R
import com.practica.ventasmoviles.model.ProductoModel

class ProductListAdapter(private val productsList: List<ProductoModel>) :
    RecyclerView.Adapter<ProductListAdapter.ViewHolder>(){

    private var position:Int = 0

    fun getPosition():Int{
        return position
    }

    fun setPosition(position: Int){
        this.position=position
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombre: TextView
        val categoria: TextView
        val marca: TextView

        init {
            // Define click listener for the ViewHolder's View.
            nombre = view.findViewById(R.id.nombre_label)
            categoria = view.findViewById(R.id.categoria_label)
            marca = view.findViewById(R.id.marca_label)
        }

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.product_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.nombre.text = productsList[position].nombre
        viewHolder.categoria.text = productsList[position].categoria
        viewHolder.marca.text = productsList[position].marca

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = productsList.size

}