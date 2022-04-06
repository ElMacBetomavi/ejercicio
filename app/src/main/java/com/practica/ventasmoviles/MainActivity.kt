package com.practica.ventasmoviles
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.practica.ventasmoviles.databinding.ActivityMainBinding
import com.practica.ventasmoviles.viewModel.MainViewModel
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

    }

    private fun changeFragment(fragment: Fragment){
        val transition = supportFragmentManager
        val fragmentTransition =transition.beginTransaction()
        fragmentTransition.add(R.id.fragment_container,fragment)
        fragmentTransition.commit()
    }

}