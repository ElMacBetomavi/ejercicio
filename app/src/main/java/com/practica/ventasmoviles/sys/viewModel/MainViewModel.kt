package com.practica.ventasmoviles.sys.viewModel

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practica.ventasmoviles.data.domain.AddItemUseCase

class MainViewModel :ViewModel() {

    val addItemFragment = MutableLiveData<Fragment>()
    var visibleFragment = MutableLiveData<VisibleFragment>()

    fun changeAddItemFragment(supportFragmentManager: FragmentManager) {
        val addItem = AddItemUseCase()
        val item = addItem.selectObjetcToAdd(supportFragmentManager)
        val curremtFragment:Fragment = addItem.getAddItemFragment(item)
        addItemFragment.postValue(curremtFragment)
    }

    fun changeCategoriaFragment(currentVisibleFragment: VisibleFragment){
        visibleFragment.postValue(currentVisibleFragment)
    }

    fun changeProductFragment(currentVisibleFragment: VisibleFragment){
        visibleFragment.postValue(currentVisibleFragment)
    }

    fun search(){

    }

}