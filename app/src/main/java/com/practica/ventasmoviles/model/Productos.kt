package com.practica.ventasmoviles.model

data class ProductoModel(val id:Int=0,
                         val imagen:String,
                         val nombre:String,
                         val costo:Float,
                         val precioMenudeo:Float,
                         val precioMayoreo:Float,
                         val categoria:String,
                         val marca:String,
                         val color:String,
                         val unidadMedida:String,
                         val cantidadMin:Int)