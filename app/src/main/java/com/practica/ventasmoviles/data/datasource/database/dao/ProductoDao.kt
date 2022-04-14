package com.practica.ventasmoviles.data.datasource.database.dao

import androidx.room.*
import com.practica.ventasmoviles.data.entities.CategoriaEntity
import com.practica.ventasmoviles.data.entities.ProductosEntity

@Dao
interface ProductoDao {
    @Query("SELECT * FROM productos_entity")
    fun getAllProductos(): List<ProductosEntity>

    @Query("SELECT * FROM productos_entity WHERE id IN (:productsIds)")
    fun loadAllByIds(productsIds: IntArray): List<ProductosEntity>

    @Insert
    fun addProduct(product: ProductosEntity):Long

    @Delete
    fun deleteProducto(product: ProductosEntity)

    @Update
    suspend fun updateCategoria(producto: ProductosEntity): Int
}