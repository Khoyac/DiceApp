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
import kotlin.coroutines.EmptyCoroutineContext.plus


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

    private var _binding: FragmentResultadosBinding? = null
    private val binding get() = _binding!!
    private lateinit var gridLayout: GridLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

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

            // Añadir un FragmentLayout donde ire cargando todos los componentes
            val fLayout = activity?.let { FrameLayout(it) }

//            val bg = activity?.getDrawable(com.example.miappta.R.drawable.dado_layout)
//            var bgSD: ShapeDrawable = bg as ShapeDrawable
//            val color = ContextCompat.getColor(requireContext(), R.color.black)
//            bgSD.paint.color = color
//            fLayout?.background = bgSD

            // Creo el ImageView donde metere el color del dado
            val ivDice = ImageView(activity)
            val colores = mapOf<String, Int>(
                "pink" to R.drawable.basechikita_pink,
                "red" to R.drawable.basechikita_red,
                "green" to R.drawable.basechikita_green
            )
            ivDice.setImageResource( colores.getValue(dado.color) )

//            ivDice.layoutParams.height = 10
//            ivDice.layoutParams.width = 10
//            val param = ivDice.layoutParams as ViewGroup.MarginLayoutParams
//            param.setMargins(10,10,10,10)
//            ivDice.layoutParams = param

            // Adding TextView con el resultado del dado
            val txtView1 = TextView(activity)
            txtView1.text = dado.resultado.toString()
            txtView1.setTextColor(Color.WHITE)
            // Cambio la fuente del texto
            txtView1.typeface = ResourcesCompat.getFont(this.requireContext(), R.font.bubblegum)

            // Con esta formula redimensiono el tamaño  de la letra para que quepa el resultado
            val fontTamano = (45 / dado.resultado.toString().length) + dado.resultado.toString().length
            txtView1.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontTamano.toFloat())
            txtView1.gravity = CENTER

            // Se monta cada view para mostrarlo
            fLayout?.addView(ivDice)
            fLayout?.addView(txtView1)
            fLayout?.setOnClickListener {
                Toast.makeText(requireContext(), R.string.tocar, Toast.LENGTH_LONG).show()
            }
            gridLayout.addView(fLayout)
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
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            ResultadosFragment().apply {
                arguments = Bundle().apply {

                }
            }

    }
}