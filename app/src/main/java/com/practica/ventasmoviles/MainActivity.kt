package com.practica.ventasmoviles

import android.os.Bundle
import android.view.MenuItem
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.practica.ventasmoviles.databinding.ActivityMainBinding
import com.practica.ventasmoviles.sys.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel.onCreateInitFragment()

        mainViewModel.fragment.observe(this, Observer { currentFragment ->
            changeFragment(currentFragment)
        })

        binding.registerButton.setOnClickListener{
            mainViewModel.getFragment(supportFragmentManager)
            //mainViewModel.clickAddBtn(supportFragmentManager.findFragmentByTag())
        }
        binding.topAppBar.setOnMenuItemClickListener {  menuItem -> setOnClickItems(menuItem) }
        binding.topAppBar.setNavigationOnClickListener { setOnClickMenu() }
    }

    private fun changeFragment(fragment: Fragment){
        val transition = supportFragmentManager
        val fragmentTransition =transition.beginTransaction()
        fragmentTransition.replace(R.id.fragment_container,fragment, "producto")
        fragmentTransition.addToBackStack(null)
        fragmentTransition.commit()
    }
    /*accion del menu de categorias  muestra las opciones: -categoria, descripcion,
     editar, eliminar, agregar categorias*/
    private fun setOnClickMenu(){
        val popupMenu = PopupMenu(this,binding.topAppBar)
        popupMenu.menuInflater.inflate(R.menu.main_menu,popupMenu.menu)
        popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
            when(item.itemId) {
                R.id.categoria ->
                    mainViewModel.openCategoriaView()
                R.id.productos ->
                    mainViewModel.openProductView()
                R.id.compras ->
                    Toast.makeText(this, "You Clicked : " + item.title, Toast.LENGTH_SHORT).show()
                R.id.ventas ->
                    Toast.makeText(this, "You Clicked : " + item.title, Toast.LENGTH_SHORT).show()
                R.id.mis_clientes ->
                    Toast.makeText(this, "You Clicked : " + item.title, Toast.LENGTH_SHORT).show()
                R.id.inventario ->
                    Toast.makeText(this, "You Clicked : " + item.title, Toast.LENGTH_SHORT).show()
            }
            true
        })
        popupMenu.show()
    }

    /*Selecciona una opcion delmenu del appBar correspondiente a filtrar y buscar*/
    private fun setOnClickItems(menuItem: MenuItem):Boolean{
        return when (menuItem.itemId) {
            R.id.filter -> {
                println("filtrar ")
                true
            }
            R.id.search -> {
                println("buscar ")
                true
            }
            else -> false
        }
    }

}