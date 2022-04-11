package com.practica.ventasmoviles.data.datasource.repository
import com.practica.ventasmoviles.MainApplication
import com.practica.ventasmoviles.data.entities.ProductosEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository {

    val db = MainApplication.database.productoDao()

    suspend fun getAllProducts(): List<ProductosEntity> {
        return withContext(Dispatchers.IO) {
            db.getAll()
        }
    }

}