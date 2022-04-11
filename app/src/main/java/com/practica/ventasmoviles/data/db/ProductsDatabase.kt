package com.practica.ventasmoviles.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.practica.ventasmoviles.data.datasource.database.dao.ProductoDao
import com.practica.ventasmoviles.data.entities.ProductosEntity

@Database(entities = [ProductosEntity::class], version = 1)
abstract class ProductsDatabase : RoomDatabase() {
    abstract fun productoDao(): ProductoDao
}