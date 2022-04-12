package com.practica.ventasmoviles.sys.viewModel

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practica.ventasmoviles.data.datasource.repository.Repository
import com.practica.ventasmoviles.data.domain.AddProductUseCase
import com.practica.ventasmoviles.data.entities.ProductosEntity
import com.practica.ventasmoviles.sys.ui.view.MainMenuFragment
import kotlinx.coroutines.launch

class RegistrarProductoViewModel:ViewModel() {

    val repository =  Repository()
    var products = MutableLiveData<List<ProductosEntity>>()
    var errorMessage = MutableLiveData<ErrorMessage>()
    val registerValidation = RegisterValidation()
    var fragment = MutableLiveData<Fragment>()

    fun validateProduct(producto:ProductosEntity){
        val currentErrorMessage = registerValidation.validateProduct(producto)
        errorMessage.postValue(currentErrorMessage)
        if(currentErrorMessage.status){
            println("producto agregado")
            addNewProduct(producto)
            fragment.postValue(MainMenuFragment())
        }
    }

    private fun addNewProduct(producto:ProductosEntity){
        val addProductUseCase= AddProductUseCase()
        viewModelScope.launch {
            addProductUseCase.addProduct(producto)
            val currentProducts = repository.getAllProducts()
            products.postValue(currentProducts)
        }
    }

    fun selectPicture(){

    }

}