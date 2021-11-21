package com.example.miappta.components

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.miappta.MainActivity.Companion.ListaDados
import com.example.miappta.R
import com.example.miappta.databinding.FragmentDadoSelectedBinding
import com.example.miappta.databinding.FragmentResultadosBinding
import com.example.miappta.databinding.FragmentTiradasBinding
import com.example.miappta.resources.Dados
import com.google.android.material.chip.Chip

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [dadoSelected.newInstance] factory method to
 * create an instance of this fragment.
 */
class dadoSelected : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: Int = 0
    private var param2: String? = null

    private var _binding: FragmentDadoSelectedBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDadoSelectedBinding.inflate(inflater, container, false)
        val root = binding.root
        val chipContador: Chip = requireActivity().findViewById(R.id.chipCDados)

        binding.btnCerrar.setOnClickListener {
            onBackPressed()
        }

        binding.btnMas.setOnClickListener {
            ListaDados.add(Dados(param1))
            (context?.getString(R.string.dados) + " " + ListaDados.size.toString()).also { chipContador.text = it }
        }




        return root
    }

    fun onBackPressed() {
//        activity?.finish()
        getFragmentManager()?.beginTransaction()?.remove(this)?.commit();

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment dadoSelected.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Int, param2: String) =
            dadoSelected().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}