package com.example.miappta.ui.main

import android.content.Context
import android.hardware.Sensor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.example.miappta.ConfDados
import com.example.miappta.MainActivity
import com.example.miappta.R
import com.example.miappta.components.dadoSelected
import com.example.miappta.resources.Utils.Companion.tiradas
import com.example.miappta.databinding.FragmentTiradasBinding
import com.example.miappta.resources.Dados
import com.example.miappta.resources.Sounds.Companion.onRollDice
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.chip.Chip

import com.google.android.material.chip.ChipGroup
import com.google.android.material.transition.MaterialContainerTransform
import android.hardware.SensorEvent
import com.example.miappta.MainActivity.Companion.ListaDados


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TiradasFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TiradasFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel

    private var _binding: FragmentTiradasBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        arguments?.let {

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTiradasBinding.inflate(inflater, container, false)
        val root = binding.root

        val fab: FloatingActionButton = binding.fab
        val mas: FloatingActionButton = binding.anyadir

        (context?.getString(R.string.dados) + " " + ListaDados.size.toString()).also { binding.chipCDados.text = it }

        mas.setOnClickListener {
//            val intent = Intent(requireContext(), PopUp::class.java)
//            intent.putExtra("popuptitle", "Error")
//            intent.putExtra("popuptext", "Sorry, that email address is already used!")
//            intent.putExtra("popupbtn", "OK")
//            intent.putExtra("darkstatusbar", false)
//            startActivity(intent)
            (activity as MainActivity?)!!.abrirPopUp(ConfDados(), binding.popUpFrame.id)
        }

        fab.setOnClickListener {
//                view ->
//            Snackbar.make(view, "Numero", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()

            onRollDice( requireContext() )
            tiradas(requireActivity().supportFragmentManager)
        }

        val chipGroup = binding.chipDices

        val genres = arrayOf("20", "10", "6")
        for (genre in genres) {
            val chip = Chip(requireContext())
            "D$genre".also { chip.text = it }
            chip.setOnClickListener {
                val fragment = dadoSelected.newInstance( genre.toInt(), "a")
                expandedChip(fragment, requireActivity().supportFragmentManager)
            }
            chipGroup.addView(chip)
        }

//        return inflater.inflate(R.layout.fragment_tiradas, container, false)
        return root
    }

    private fun expandedChip(fragment: Fragment, fmanager: FragmentManager) {
        val fragmentIntercambio = fmanager.beginTransaction()
        fragmentIntercambio.replace(R.id.chipFragment, fragment)
        fragmentIntercambio.addToBackStack(null)
        fragmentIntercambio.commit()
    }





    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TiradasFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            TiradasFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}