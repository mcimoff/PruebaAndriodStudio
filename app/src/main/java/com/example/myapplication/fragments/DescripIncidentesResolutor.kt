package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.navigation.findNavController
import com.example.myapplication.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DescripIncidentesResolutor.newInstance] factory method to
 * create an instance of this fragment.
 */
class DescripIncidentesResolutor : Fragment() {
    // TODO: Rename and change types of parameters

    lateinit var vistaDescripIncidentes : View
    lateinit var btnHomeGoToHome: Button
    lateinit var titleIncidente: TextView
    lateinit var dato2: TextView
    lateinit var dato3: TextView
    lateinit var check: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vistaDescripIncidentes = inflater.inflate(R.layout.descrip_incidentes_resolutor, container, false)

        btnHomeGoToHome = vistaDescripIncidentes.findViewById((R.id.buttonGoToHome))

        titleIncidente = vistaDescripIncidentes.findViewById(R.id.textIncidente)

        dato2 = vistaDescripIncidentes.findViewById(R.id.textIncidente2)

        dato3 = vistaDescripIncidentes.findViewById(R.id.textIncidente3)

        check = vistaDescripIncidentes.findViewById(R.id.checkBox)


        return vistaDescripIncidentes
    }

    override fun onStart() {
        super.onStart()

        //var programsid = Fragment2Args.fromBundle(requireArguments()).programsid

        var incidenteText =
            DescripIncidentsArgs.fromBundle(requireArguments()).incidenteObject.titulo

        var incidentsText2 =
            DescripIncidentsArgs.fromBundle(requireArguments()).incidenteObject.descripcionUsuario

        var incidentsText3 = DescripIncidentsArgs.fromBundle(requireArguments()).incidenteObject._id

        var incidentsText4 = DescripIncidentsArgs.fromBundle(requireArguments()).incidenteObject.estadoActual

        titleIncidente.text = incidenteText

        dato2.text = incidentsText2

        dato3.text = incidentsText3.toString()

        check.text = incidentsText4

        btnHomeGoToHome.setOnClickListener {


            var action6 =
                DescripIncidentesResolutorDirections.actionDescripIncidentsResolutorToHomeIncidents(contenidoID = "contenido")

            vistaDescripIncidentes.findNavController().navigate(action6)
        }

        check.setOnClickListener {
            if (check.isChecked()) {
                check.text = "Cerrado"
            } else {
                check.text = "Abierto"
            }
        }

    }
}