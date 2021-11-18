package com.example.miappta.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.miappta.ConfDados
import com.example.miappta.MainActivity
import com.example.miappta.PopUp
import com.example.miappta.resources.Utils.Companion.tiradas
import com.example.miappta.databinding.FragmentTiradasBinding
import com.example.miappta.resources.Dados
import com.example.miappta.resources.Sounds.Companion.onRollDice
import com.google.android.material.floatingactionbutton.FloatingActionButton

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

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var tirada: MutableList<Dados> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTiradasBinding.inflate(inflater, container, false)
        val root = binding.root

        tirada.add(Dados(3, 3))
        tirada.add(Dados(4, 2))
        tirada.add(Dados(10, 2))


        val fab: FloatingActionButton = binding.fab
        val mas: FloatingActionButton = binding.anyadir

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
            tiradas(requireActivity().supportFragmentManager, tirada)
        }

//        return inflater.inflate(R.layout.fragment_tiradas, container, false)
        return root
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
        fun newInstance(param1: String, param2: String) =
            TiradasFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}