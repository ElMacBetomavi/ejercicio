package com.practica.ventasmoviles.viewModel
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practica.ventasmoviles.model.ObjectsProvider
import com.practica.ventasmoviles.model.ProductoModel
import com.practica.ventasmoviles.view.RegistrarProductoFragment

class MainMenuFragmentViewModel:ViewModel() {

    var listItems = MutableLiveData<List<ProductoModel>>()
    var fragment = MutableLiveData<Fragment>()
    var registerProductFragment = RegistrarProductoFragment()

    fun onCreateListItems(){
        listItems.postValue(ObjectsProvider.productos)
    }
    fun verDetallesProduct(id:Int){
        println("eliminar detalles " +id)
    }
    fun eliminarProducto(id:Int){
        println("eliminar " +id)
    }
    fun editarProducto(id:Int){
        println("eliminar editar " +id)
    }
    fun chagenRegisterProductoFragment(){
        fragment.postValue(registerProductFragment)
    }


}