package com.practica.ventasmoviles.view
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.practica.ventasmoviles.R
import com.practica.ventasmoviles.databinding.FragmentRegistrarProductoBinding


class RegistrarProductoFragment : Fragment() {

    private var _binding:FragmentRegistrarProductoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegistrarProductoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initoptionsRegisterField()



    }

    fun initoptionsRegisterField(){
        val itemsCategory = listOf("Material", "Design", "Components", "Android")
        val itemsUnidades = listOf("Material", "Design", "Components", "Android")
        val adapterCategory = ArrayAdapter(requireContext(), R.layout.list_item_options, itemsCategory)
        (binding.categoriaField.editText as? AutoCompleteTextView)?.setAdapter(adapterCategory)
        val adapterUnidadMedida = ArrayAdapter(requireContext(), R.layout.list_item_options, itemsUnidades)
        (binding.unidadMedidaField.editText as? AutoCompleteTextView)?.setAdapter(adapterUnidadMedida)
    }

}