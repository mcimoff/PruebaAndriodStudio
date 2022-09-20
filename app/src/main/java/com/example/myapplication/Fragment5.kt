package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment5.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment5 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var vistaFragment5 : View
    lateinit var buttonHomeGoToHomeAlta : Button

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

        vistaFragment5 = inflater.inflate(R.layout.fragment_5, container, false)

        buttonHomeGoToHomeAlta = vistaFragment5.findViewById((R.id.buttonGoToHomeAlta))


        return vistaFragment5;

    }

    override fun onStart() {
        super.onStart()

        buttonHomeGoToHomeAlta.setOnClickListener{
            var action = Fragment5Directions.actionFragment5ToFragment3(contenidoID = "contenido")

            vistaFragment5.findNavController().navigate(action)
        }
    }



}

