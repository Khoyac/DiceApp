package com.example.miappta

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.miappta.ui.main.ResultadosFragment
import kotlin.random.Random

class Utils : AppCompatActivity() {

    private lateinit var fragment: Fragment

    fun tiradas(context: FragmentManager) {
        fragment = ResultadosFragment()
        cargarFragment(fragment, context)
    }
    fun cargarFragment(fragment: Fragment, context: FragmentManager) {
        val fragmentIntercambio = context.beginTransaction()
        fragmentIntercambio.add(R.id.llTiradas, fragment)
        fragmentIntercambio.addToBackStack(null)
        fragmentIntercambio.commit()
    }

    fun roller(number: Int): Int {
        return Random.nextInt(0, number)
    }


}