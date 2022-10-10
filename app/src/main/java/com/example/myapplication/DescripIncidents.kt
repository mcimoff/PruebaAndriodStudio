package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

      lateinit var vistaDescripIncidentes : View
      lateinit var btnHomeGoToHome: Button
      lateinit var titleIncidente: TextView
/**
 * A simple [Fragment] subclass.
 * Use the [DescripIncidents.newInstance] factory method to
 * create an instance of this fragment.
 */
class DescripIncidents : Fragment() {
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
        vistaDescripIncidentes = inflater.inflate(R.layout.descrip_incidents, container, false)

        btnHomeGoToHome = vistaDescripIncidentes.findViewById((R.id.buttonGoToHome))

        titleIncidente = vistaDescripIncidentes.findViewById(R.id.textIncidente)



        return vistaDescripIncidentes
    }

    override fun onStart() {
        super.onStart()

        //var programsid = Fragment2Args.fromBundle(requireArguments()).programsid

        var incidenteText = DescripIncidentsArgs.fromBundle(requireArguments()).incidenteID

        titleIncidente.text = incidenteText

        btnHomeGoToHome.setOnClickListener{


            var action6 = DescripIncidentsDirections.actionDescripIncidentsToHomeIncidents(contenidoID = "contenido")

            vistaDescripIncidentes.findNavController().navigate(action6)
        }
    }

}