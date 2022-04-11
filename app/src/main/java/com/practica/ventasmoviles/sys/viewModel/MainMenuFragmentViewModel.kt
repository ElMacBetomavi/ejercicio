package com.practica.ventasmoviles.sys.viewModel
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practica.ventasmoviles.MainApplication
import com.practica.ventasmoviles.data.datasource.repository.Repository
import com.practica.ventasmoviles.data.entities.ProductosEntity
import com.practica.ventasmoviles.sys.ui.view.RegistrarProductoFragment
import kotlinx.coroutines.launch

class MainMenuFragmentViewModel:ViewModel() {

    val repository =  Repository()
    var products = MutableLiveData<List<ProductosEntity>>()
    var fragment = MutableLiveData<Fragment>()
    var registerProductFragment = RegistrarProductoFragment()
    var db = MainApplication.database.productoDao()

    fun onCreateListItems(){
        viewModelScope.launch {
            val currentProducts = repository.getAllProducts()
            products.postValue(currentProducts)
        }
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