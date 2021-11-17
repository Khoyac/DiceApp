package com.example.miappta.resources

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.miappta.R
import com.example.miappta.ui.main.ResultadosFragment
import kotlin.random.Random

class Utils : AppCompatActivity() {


    companion object: AppCompatActivity() {
        private lateinit var fragment: Fragment

        fun cargarFragment(fragment: Fragment, context: FragmentManager) {
            val fragmentIntercambio = context.beginTransaction()
            fragmentIntercambio.add(R.id.llTiradas, fragment)
            fragmentIntercambio.addToBackStack(null)
            fragmentIntercambio.commit()
        }

        fun tiradas(context: FragmentManager) {
            fragment = ResultadosFragment.newInstance( roller(10).toString(), "test2")
            cargarFragment(fragment, context)
        }

        fun roller(number: Int): Int {
            return Random.nextInt(0, number)
        }

    }


}