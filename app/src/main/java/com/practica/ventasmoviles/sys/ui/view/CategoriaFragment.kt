package com.practica.ventasmoviles.sys.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.practica.ventasmoviles.data.entities.CategoriaEntity
import com.practica.ventasmoviles.databinding.FragmentCategoriaBinding
import com.practica.ventasmoviles.sys.ui.view.adapter.CategoriaListAdapter

class CategoriaFragment : Fragment() {

    private var _binding: FragmentCategoriaBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: CategoriaListAdapter
    private var productList = emptyList<CategoriaEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCategoriaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    fun initRecyclerView(){
        if(productList.isNotEmpty()) binding.messageCategoria.visibility =View.GONE
        adapter = CategoriaListAdapter(productList)
        binding.rvCategorias.layoutManager = LinearLayoutManager(parentFragment?.context)
        binding.rvCategorias.adapter = adapter
    }

}