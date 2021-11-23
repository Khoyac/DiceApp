package com.example.miappta.resources

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.miappta.MainActivity.Companion.ListaDados
import com.example.miappta.R
import com.example.miappta.ui.main.ResultadosFragment
import kotlin.random.Random

class Utils : AppCompatActivity() {


    companion object: AppCompatActivity() {
        private lateinit var fragment: Fragment

        private fun cargarFragment(fragment: Fragment, context: FragmentManager) {
            val fragmentIntercambio = context.beginTransaction()
            fragmentIntercambio.replace(R.id.llTiradas, fragment)
            fragmentIntercambio.addToBackStack(null)
            fragmentIntercambio.commit()
        }

        fun tiradas(context: FragmentManager) {

            // Ordeno la lista de dados para mostrarlos ordenados
            ListaDados.sortBy { it.caras }
            tirarDados()

            fragment = ResultadosFragment.newInstance()
            cargarFragment(fragment, context)
        }

        fun roller(number: Int): Int {
            return Random.nextInt(1, number)
        }

        fun tirarDados() {
            for (dado in ListaDados) {
                dado.resultado = roller(dado.caras)
            }
        }

        fun crearDados() {
            ListaDados.clear()
            for (i in 1..roller(20)) {
                ListaDados.add(Dados(6, "basechikita_red"))
            }

        }

//        fun abrirPopUp(fragment: Fragment, id: Int) {
//            val fragmentIntercambio = supportFragmentManager.beginTransaction()
//            fragmentIntercambio.replace(id, fragment)
//            fragmentIntercambio.commit()
//        }
    }


}