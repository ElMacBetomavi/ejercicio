package com.practica.ventasmoviles.sys.viewModel

import com.practica.ventasmoviles.data.entities.ProductosEntity

class RegisterValidation {


    fun validateProduct(producto: ProductosEntity): ErrorMessage{
        var currentErrorMessage = ErrorMessage()
        var productoReadyFlat=false

        if(!validateName(producto.nombre!!)){
            currentErrorMessage.name = "el nombre debe de tener minimo 5 caracteres maximo 70"
            currentErrorMessage.status=false
        }else {
            currentErrorMessage.name=null
            currentErrorMessage.status=true
        }

        if(validateCosto(producto.costo)){
            currentErrorMessage.costo = null
            currentErrorMessage.status=true

        }else {
            currentErrorMessage.costo = "obligatorio"
            currentErrorMessage.status=false
        }

        if(!validateCosto(producto.precioMenudeo)){
            currentErrorMessage.precioMenudeo = "obligatorio"
            currentErrorMessage.status=false
        }else {
            currentErrorMessage.precioMenudeo = null
            currentErrorMessage.status=false
        }

        if(!validateCosto(producto.precioMayoreo)){
            currentErrorMessage.precioMayoreo = "obligatorio"
            currentErrorMessage.status=false

        }else {
            currentErrorMessage.precioMayoreo = null
            currentErrorMessage.status=true
        }

        if(!validateMarca(producto.marca!!)){
            currentErrorMessage.marca = "marca debe de tener minimo 3 caracteres maximo 40"
            currentErrorMessage.status=false
        }else {
            currentErrorMessage.marca = null
            currentErrorMessage.status=true
        }
        if(!validateMarca(producto.color!!)){
            currentErrorMessage.color = "color debe de tener minimo 3 caracteres maximo 40"
            currentErrorMessage.status=false
        }else {
            currentErrorMessage.color = null
            currentErrorMessage.status=true
        }
        return currentErrorMessage
    }

    fun validateName(name:String): Boolean {
        return name.length in 5..68
    }

    fun validateCosto(costo: Float?): Boolean {
        val newcosto:Float
        newcosto = if(costo != null) costo else 0f
        return newcosto > 0.1f
    }

    fun validateMarca(marca:String):Boolean{
        return marca.length in 3..40
    }
}