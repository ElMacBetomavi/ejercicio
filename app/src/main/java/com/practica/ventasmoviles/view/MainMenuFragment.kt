package com.practica.ventasmoviles.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.view.ContextMenu.ContextMenuInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.practica.ventasmoviles.R
import com.practica.ventasmoviles.databinding.FragmentMainMenuBinding
import com.practica.ventasmoviles.model.ObjectsProvider
import com.practica.ventasmoviles.model.ProductoModel
import com.practica.ventasmoviles.view.adapter.ProductListAdapter
import com.practica.ventasmoviles.viewModel.MainMenuFragmentViewModel

class MainMenuFragment : Fragment() {

    private var _binding:FragmentMainMenuBinding? = null
    private val binding get() = _binding!!
    private val mainMenuFragmentViewModel: MainMenuFragmentViewModel by viewModels()
    private var productList = emptyList<ProductoModel>()
    private lateinit var adapter: ProductListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainMenuBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainMenuFragmentViewModel.onCreateListItems()
        initRecyclerView()
        registerForContextMenu(binding.rvProducts)

        mainMenuFragmentViewModel.listItems.observe(viewLifecycleOwner, Observer { currentProductList ->
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
        productList = ObjectsProvider.productos
        adapter = ProductListAdapter(productList)
        binding.rvProducts.layoutManager = LinearLayoutManager(parentFragment?.context)
        binding.rvProducts.adapter = adapter
    }

    fun changeFragment(fragment: Fragment){
        val transition = parentFragmentManager
        val fragmentTransition =transition.beginTransaction()
        fragmentTransition.add(R.id.fragment_container,fragment)
        fragmentTransition.remove(this)
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