package com.practica.ventasmoviles.viewModel

import android.view.Menu
import android.view.MenuInflater
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practica.ventasmoviles.R
import com.practica.ventasmoviles.view.MainMenuFragment

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