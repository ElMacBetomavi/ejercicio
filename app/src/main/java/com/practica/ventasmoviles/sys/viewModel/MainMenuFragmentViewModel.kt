package com.practica.ventasmoviles.sys.viewModel
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practica.ventasmoviles.sys.ui.view.CategoriaFragment
import com.practica.ventasmoviles.MainApplication
import com.practica.ventasmoviles.data.datasource.repository.Repository
import com.practica.ventasmoviles.data.domain.DeleteProductUseCase
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
            //val currentProducts = repository.getAllProducts()
            val currentProducts = MainApplication.database.productoDao().getAll()
            products.postValue(currentProducts)
        }
    }

    fun verDetallesProduct(id:Int){
        println("eliminar detalles " +id)

    }

    fun eliminarProducto(producto:ProductosEntity){
        val deleteProductUseCase= DeleteProductUseCase()
        viewModelScope.launch {
            deleteProductUseCase.deleteProduct(producto)
            //val currentProducts = repository.getAllProducts()
            val currentProducts = db.getAll()
            products.postValue(currentProducts)
            println("eliminar editar " +producto.id)
        }
    }

    fun editarProducto(id:Int){
        println("eliminar editar " +id)
    }

    fun openCategoriaView(){
        fragment.postValue(CategoriaFragment())
    }
}