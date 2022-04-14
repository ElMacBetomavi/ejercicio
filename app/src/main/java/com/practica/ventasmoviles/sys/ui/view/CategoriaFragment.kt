package com.practica.ventasmoviles.sys.ui.view

import android.app.AlertDialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.practica.ventasmoviles.MainApplication
import com.practica.ventasmoviles.R
import com.practica.ventasmoviles.data.entities.CategoriaEntity
import com.practica.ventasmoviles.databinding.FragmentCategoriaBinding
import com.practica.ventasmoviles.sys.ui.view.adapter.CategoriaListAdapter
import com.practica.ventasmoviles.sys.viewModel.categorias.CategoriaViewModel

class CategoriaFragment : Fragment() {

    private var _binding: FragmentCategoriaBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: CategoriaListAdapter
    private val categoriaViewModel: CategoriaViewModel by viewModels()
    val db = MainApplication.database.categoriaDao()
    private var categoriaList = emptyList<CategoriaEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCategoriaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.findViewById<MaterialToolbar>(R.id.topAppBar)?.title = "Categorias"
        categoriaList = db.getAllCategoria()
        initRecyclerView()

        categoriaViewModel.categorias.observe(viewLifecycleOwner, Observer { currentCategoriatList ->
            adapter.setListProducts(currentCategoriatList)
        })

        categoriaViewModel.id.observe(viewLifecycleOwner, Observer {
            changeEditFragment(it)
        })

    }

    fun initRecyclerView(){
        if(categoriaList.isNotEmpty()) binding.messageCategoria.visibility =View.GONE
        adapter = CategoriaListAdapter()
        adapter.setListProducts(categoriaList)
        binding.rvCategorias.layoutManager = LinearLayoutManager(parentFragment?.context)
        binding.rvCategorias.adapter = adapter
    }

    fun changeEditFragment(idm: Int){
        var bundle =Bundle()
        bundle.putInt("id", idm)
        var fragment = RegistrarCategoriaFragment()
        fragment.arguments = bundle
        val transition = parentFragmentManager
        transition.popBackStack()
        val fragmentTransition =transition.beginTransaction()
        fragmentTransition.replace(R.id.fragment_container,fragment)
        fragmentTransition.addToBackStack(null)
        fragmentTransition.commit()
    }

    /*opciones de la tarjeta del producto, ver detalles, eliminar, editar*/
    override fun onContextItemSelected(item: MenuItem): Boolean {
        val position = adapter.getPosition()
        val categoria = categoriaList[position]

        return when (item.title) {
            "Ver imagen" -> {
                viewImage(categoria.image!!)
                categoriaViewModel.verDetallesCategoria(categoria.id)
                true
            }
            "Eliminar" -> {
                showDialog(categoria)
                true
            }
            "Editar" -> {
                categoriaViewModel.editarCategoria(categoria.id)
                true
            }
            else -> false
        }
    }
    private fun showDialog(categoria:CategoriaEntity) {
        val builder = AlertDialog.Builder(context)
        builder.setMessage("eliminar categoria")
        builder.setCancelable(true)
        builder.setPositiveButton("si"){ dialog, _ ->
            categoriaViewModel.eliminarCategoria(categoria)
            dialog.dismiss()
        }
        builder.setNegativeButton("no"){dialog, _ ->
            dialog.dismiss()
        }
        val alert = builder.create()
        alert.show()
    }

    fun viewImage(image:String){
        if (image!=""){
            val bitmap: Bitmap = BitmapFactory.decodeFile(image)
            val builder = AlertDialog.Builder(context)
            builder.setCancelable(true)
            val view = layoutInflater.inflate(R.layout.image_view,null)
            builder.setView(view)
            val image = view.findViewById<ImageView>(R.id.image_view)
            //image.setImageResource(R.drawable.ic_baseline_image_24)
            image.setImageBitmap(bitmap)
            val alert = builder.create()
            alert.show()
        }else{
            Toast.makeText(context,"no existe imagen asignada", Toast.LENGTH_LONG).show()
        }

    }

    override fun onResume() {
        activity?.findViewById<CoordinatorLayout>(R.id.appbar)?.visibility = View.VISIBLE
        activity?.findViewById<FloatingActionButton>(R.id.register_button)?.visibility = View.VISIBLE
        super.onResume()
    }

}