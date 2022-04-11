package com.practica.ventasmoviles.sys.viewModel

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practica.ventasmoviles.sys.ui.view.MainMenuFragment

class MainViewModel() :ViewModel() {

    val fragment = MutableLiveData<Fragment>()
    private val mainFragment = MainMenuFragment()

    fun onCreateInitFragment(){
        fragment.postValue(mainFragment)
    }

    fun changeFragment(currentFragment: Fragment){
        fragment.postValue(currentFragment)
    }




}