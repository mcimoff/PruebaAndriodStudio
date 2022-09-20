package com.example.myapplication

import android.icu.text.CaseMap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

      lateinit var vistaFragment4 : View
      lateinit var btnHomeGoToHome: Button
      lateinit var titleIncidente: TextView
/**
 * A simple [Fragment] subclass.
 * Use the [Fragment4.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment4 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        vistaFragment4 = inflater.inflate(R.layout.fragment_4, container, false)

        btnHomeGoToHome = vistaFragment4.findViewById((R.id.buttonGoToHome))

        titleIncidente = vistaFragment4.findViewById(R.id.textIncidente)



        return vistaFragment4;
    }

    override fun onStart() {
        super.onStart()

        //var programsid = Fragment2Args.fromBundle(requireArguments()).programsid

        var incidenteText = Fragment4Args.fromBundle(requireArguments()).incidenteID

        titleIncidente.text = incidenteText

        btnHomeGoToHome.setOnClickListener{


            var action6 = Fragment4Directions.actionFragment4ToFragment3(contenidoID = "contenido")

            vistaFragment4.findNavController().navigate(action6)
        }
    }

}