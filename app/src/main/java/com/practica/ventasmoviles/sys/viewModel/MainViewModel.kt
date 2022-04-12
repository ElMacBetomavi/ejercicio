package com.practica.ventasmoviles.sys.viewModel

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practica.ventasmoviles.sys.ui.view.CategoriaFragment
import com.practica.ventasmoviles.data.datasource.repository.Repository
import com.practica.ventasmoviles.data.domain.AddItemUseCase

import com.practica.ventasmoviles.sys.ui.view.MainMenuFragment

class MainViewModel() :ViewModel() {

    val repository =  Repository()
    val fragment = MutableLiveData<Fragment>()
    private val mainFragment = MainMenuFragment()

    fun onCreateInitFragment(){
        fragment.postValue(mainFragment)
    }

    fun getFragment(supportFragmentManager: FragmentManager) {
        val addItem = AddItemUseCase()
        val item = addItem.selectObjetcToAdd(supportFragmentManager)
        val curremtFragment:Fragment = addItem.addItem(item)
        fragment.postValue(curremtFragment)
    }

    fun openCategoriaView(){
        fragment.postValue(CategoriaFragment())
    }

    fun openProductView(){
        fragment.postValue(MainMenuFragment())
    }

}