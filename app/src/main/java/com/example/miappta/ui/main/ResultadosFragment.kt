package com.example.miappta.ui.main

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ShapeDrawable
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity.CENTER
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.gridlayout.widget.GridLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.example.miappta.R
import com.example.miappta.databinding.FragmentResultadosBinding
import com.example.miappta.resources.Dados
import android.graphics.drawable.GradientDrawable
import android.widget.Toast

import androidx.core.content.ContextCompat
import com.example.miappta.MainActivity.Companion.ListaDados


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ResultadosFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResultadosFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: MutableList<Dados>? = null
    private var param2: String? = null

    private var _binding: FragmentResultadosBinding? = null
    private val binding get() = _binding!!
    private lateinit var gridLayout: GridLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
//            param1 = it.
//            param2 = it.getString(ARG_PARAM2)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultadosBinding.inflate(inflater, container, false)
        val root = binding.root
        //binding.tvResultado.text = param1


        gridLayout = binding.glDados
        for (dado in ListaDados) {

            // Adding imageView Dado
           // val ivDado = ImageView(activity)
            //ivDado.setImageResource(R.drawable.basedado)
            val fLayout = activity?.let { FrameLayout(it) }

//            val bg = activity?.getDrawable(com.example.miappta.R.drawable.dado_layout)
//
//            var bgSD: ShapeDrawable = bg as ShapeDrawable
//            val color = ContextCompat.getColor(requireContext(), R.color.black)
//            bgSD.paint.color = color
//            fLayout?.background = bgSD

            val ivDice = ImageView(activity)
            ivDice.setImageResource(R.drawable.basedado_xikito)
          //  ivDice.layoutParams.height = 10
           // ivDice.layoutParams.width = 10
//            val param = ivDice.layoutParams as ViewGroup.MarginLayoutParams
//            param.setMargins(10,10,10,10)
//            ivDice.layoutParams = param

            // Adding team 1 TextView
            val txtView1 = TextView(activity)
            txtView1.text = dado.resultado.toString()
            txtView1.setTextColor(Color.WHITE)
            txtView1.typeface = ResourcesCompat.getFont(this.requireContext(), com.example.miappta.R.font.bubblegum)

            val fontTamano = (45 / dado.resultado.toString().length) + dado.resultado.toString().length

            txtView1.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontTamano.toFloat())
            //txtView1.setBackgroundResource(R.drawable.basedado_xikito)

            txtView1.gravity = CENTER

            fLayout?.addView(ivDice)
            fLayout?.addView(txtView1)
            fLayout?.setOnClickListener {
                Toast.makeText(requireContext(), R.string.tocar, Toast.LENGTH_LONG).show()
            }
            gridLayout.addView(fLayout)
            //Log.i("Control:", "---------" + dado.caras.toString())
        }

        return root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ResultadosFragment.
         */
        lateinit var lista: MutableList<Dados>
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            ResultadosFragment().apply {
                arguments = Bundle().apply {
                    //lista = param1
//                    putString(ARG_PARAM1, param1)
                    //putString(ARG_PARAM2, param2)
                }
            }

    }
}