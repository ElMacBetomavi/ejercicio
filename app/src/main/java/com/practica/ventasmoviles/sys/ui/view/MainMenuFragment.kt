package com.practica.ventasmoviles.sys.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
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
    //private var productList = emptyList<ProductoModel>()
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

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainMenuFragmentViewModel.onCreateListItems()

        productList = MainApplication.database.productoDao().getAll()

        initRecyclerView()
        registerForContextMenu(binding.rvProducts)

        mainMenuFragmentViewModel.products.observe(viewLifecycleOwner, Observer { currentProductList ->
            productList = currentProductList
            adapter.notifyDataSetChanged()
        })
        mainMenuFragmentViewModel.fragment.observe(viewLifecycleOwner, Observer { CurrentFragment ->
            changeFragment(CurrentFragment)
        })

        binding.topAppBar.setOnMenuItemClickListener {  menuItem -> setOnClickItems(menuItem) }
        binding.topAppBar.setNavigationOnClickListener { setOnClickMenu() }
        binding.registerProductButton.setOnClickListener{ mainMenuFragmentViewModel.chagenRegisterProductoFragment() }
    }

    fun initRecyclerView(){
        productList = productList
        adapter = ProductListAdapter(productList)
        binding.rvProducts.layoutManager = LinearLayoutManager(parentFragment?.context)
        binding.rvProducts.adapter = adapter
    }

    fun changeFragment(fragment: Fragment){
        val transition = parentFragmentManager
        val fragmentTransition =transition.beginTransaction()
        fragmentTransition.replace(R.id.fragment_container,fragment)
        fragmentTransition.addToBackStack(null)
        fragmentTransition.commit()
    }

    /*Selecciona una opcion delmenu del appBar correspondiente a filtrar y buscar*/
    private fun setOnClickItems(menuItem:MenuItem):Boolean{
        return when (menuItem.itemId) {
            R.id.filter -> {
                println("filtrar ")

                true
            }
            R.id.search -> {
                println("buscar ")
                true
            }
            else -> false
        }
    }

    /*accion del menu de categorias  muestra las opciones: -categoria, descripcion,
    editar, eliminar, agregar categorias*/
    private fun setOnClickMenu(){
        println("menu de opciones")
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
                mainMenuFragmentViewModel.eliminarProducto(producto.id)
                true
            }
            "Editar" -> {
                mainMenuFragmentViewModel.editarProducto(producto.id)
                true
            }
            else -> false
        }
    }

}