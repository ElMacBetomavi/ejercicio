package com.practica.ventasmoviles.data.datasource.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.practica.ventasmoviles.data.entities.ProductosEntity

@Dao
interface ProductoDao {
    @Query("SELECT * FROM productos_entity")
    fun getAll(): List<ProductosEntity>

    @Query("SELECT * FROM productos_entity WHERE id IN (:productsIds)")
    fun loadAllByIds(productsIds: IntArray): List<ProductosEntity>

    @Insert
    fun addProduct(product: ProductosEntity):Long

    @Delete
    fun delete(product: ProductosEntity)
}