package com.practica.ventasmoviles.data.domain

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.practica.ventasmoviles.MainActivity
import com.practica.ventasmoviles.R
import com.practica.ventasmoviles.sys.ui.view.MainMenuFragment
import com.practica.ventasmoviles.sys.ui.view.RegistrarProductoFragment

class AddItemUseCase() {

    fun selectObjetcToAdd(item: FragmentManager):String{
        var addItem= ""
        if(item.findFragmentByTag("producto")?.isVisible == true){
            addItem="producto"
        }
        if(item.findFragmentByTag("categoria")?.isVisible == true){
            addItem="categoria"
        }

        return addItem
    }

    fun addItem(item:String):Fragment{
        return when(item){
            "producto"-> RegistrarProductoFragment()
            "categoria"-> MainMenuFragment()
            else -> MainMenuFragment()
        }
    }




}