package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment3.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeIncidents : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var vistaHomeIncidentes : View
    lateinit var btnLogout: Button
    lateinit var btnHomeGoToIncidente1: Button
    lateinit var btnHomeGoToIncidente2: Button
    lateinit var receptorTV: TextView
    lateinit var providerTV: TextView
    lateinit var btnAlta: com.google.android.material.floatingactionbutton.FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        /*val bundle: Bundle? = Intent.get

        setup()*/
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        vistaHomeIncidentes = inflater.inflate(R.layout.home_incidents, container, false)

        btnLogout = vistaHomeIncidentes.findViewById((R.id.buttonLogOut))
        btnHomeGoToIncidente1 = vistaHomeIncidentes.findViewById((R.id.buttonIncidente1))
        btnHomeGoToIncidente2 = vistaHomeIncidentes.findViewById((R.id.buttonIncidente2))
        btnAlta = vistaHomeIncidentes.findViewById((R.id.btnGoToAlta))

        return vistaHomeIncidentes;
    }

    override fun onStart() {
        super.onStart()


        /*btnLogout.setOnClickListener {

            //indexID = "idx" dentro del parentesis
            var action3 = HomeIncidentsDirections.actionHomeIncidentsToActivityLogin()

            vistaHomeIncidentes.findNavController().navigate(action3)

        }*/

        btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            //onBackPressed()
            //indexID = "idx" dentro del parentesis
            var action3 = HomeIncidentsDirections.actionHomeIncidentsToActivityLogin()

            vistaHomeIncidentes.findNavController().navigate(action3)

        }

        btnHomeGoToIncidente1.setOnClickListener{

            var textIncidente = btnHomeGoToIncidente1.text.toString()
            var action4 = HomeIncidentsDirections.actionHomeIncidentsToDescripIncidents(textIncidente)

            vistaHomeIncidentes.findNavController().navigate(action4)
        }

        btnHomeGoToIncidente2.setOnClickListener{

            var textIncidente2 = btnHomeGoToIncidente2.text.toString()
            var action5 = HomeIncidentsDirections.actionHomeIncidentsToDescripIncidents(textIncidente2)

            vistaHomeIncidentes.findNavController().navigate(action5)
        }

        btnAlta.setOnClickListener{
            var action6 = HomeIncidentsDirections.actionHomeIncidentsToAltaIncidents(altaID = "alta")

            vistaHomeIncidentes.findNavController().navigate(action6)
        }


    }

}