package com.practica.ventasmoviles.sys.ui.view

import android.os.Bundle
import android.view.*
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.practica.ventasmoviles.MainApplication
import com.practica.ventasmoviles.R
import com.practica.ventasmoviles.data.entities.ProductosEntity
import com.practica.ventasmoviles.databinding.FragmentMainMenuBinding
import com.practica.ventasmoviles.sys.ui.view.adapter.ProductListAdapter
import com.practica.ventasmoviles.sys.viewModel.MainMenuFragmentViewModel

class MainMenuFragment : Fragment() {

    private var _binding:FragmentMainMenuBinding? = null
    private val binding get() = _binding!!
    private val mainMenuFragmentViewModel: MainMenuFragmentViewModel by viewModels()
    private var productList = emptyList<ProductosEntity>()
    private lateinit var adapter: ProductListAdapter
    val db = MainApplication.database.productoDao()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMainMenuBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productList = db.getAll()

        initRecyclerView()

        mainMenuFragmentViewModel.products.observe(viewLifecycleOwner, Observer { currentProductList ->
            productList = currentProductList
            adapter.notifyDataSetChanged()
        })

        mainMenuFragmentViewModel.fragment.observe(viewLifecycleOwner, Observer { CurrentFragment ->
            changeFragment(CurrentFragment)
        })

        //inicializa la lista de productos
        binding.topAppBar.visibility=View.GONE
        //mainMenuFragmentViewModel.onCreateListItems()
    }

    fun initRecyclerView(){
        adapter = ProductListAdapter(productList)
        binding.rvProducts.layoutManager = LinearLayoutManager(parentFragment?.context)
        binding.rvProducts.adapter = adapter
    }

    private fun changeFragment(fragment: Fragment){
        val transition = parentFragmentManager
        val fragmentTransition =transition.beginTransaction()
        fragmentTransition.replace(R.id.fragment_container,fragment, "categoria")
        fragmentTransition.addToBackStack(null)
        fragmentTransition.commit()
    }

    /*opciones de la tarjeta del producto, ver detalles, eliminar, editar*/
    override fun onContextItemSelected(item: MenuItem): Boolean {
        val position = adapter.getPosition()
        val producto = productList[position]
        return when (item.title) {
            "Ver detalles" -> {
                mainMenuFragmentViewModel.verDetallesProduct(producto.id)
                true
            }
            "Eliminar" -> {
                mainMenuFragmentViewModel.eliminarProducto(producto)
                true
            }
            "Editar" -> {
                mainMenuFragmentViewModel.editarProducto(producto.id)
                true
            }
            else -> false
        }
    }

    override fun onResume() {
        activity?.findViewById<CoordinatorLayout>(R.id.appbar)?.visibility = View.VISIBLE
        activity?.findViewById<FloatingActionButton>(R.id.register_button)?.visibility = View.VISIBLE
        super.onResume()
    }

}