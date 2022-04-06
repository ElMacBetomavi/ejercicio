package com.practica.ventasmoviles.viewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practica.ventasmoviles.model.ObjectsProvider
import com.practica.ventasmoviles.model.ProductoModel

class MainMenuFragmentViewModel:ViewModel() {

    var listItems = MutableLiveData<List<ProductoModel>>()

    fun onCreateListItems(){
        listItems.postValue(ObjectsProvider.productos)
    }

}