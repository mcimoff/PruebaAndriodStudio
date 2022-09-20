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
 * Use the [Fragment3.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment3 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var vistaFragment3 : View
    lateinit var btnHomeGoToIndex: Button
    lateinit var btnHomeGoToIncidente1: Button
    lateinit var btnHomeGoToIncidente2: Button
    lateinit var btnFloat: com.google.android.material.floatingactionbutton.FloatingActionButton

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
        vistaFragment3 = inflater.inflate(R.layout.fragment_3, container, false)

        btnHomeGoToIndex = vistaFragment3.findViewById((R.id.buttonHomeGoToIndex))
        btnHomeGoToIncidente1 = vistaFragment3.findViewById((R.id.buttonIncidente1))
        btnHomeGoToIncidente2 = vistaFragment3.findViewById((R.id.buttonIncidente2))
        btnFloat = vistaFragment3.findViewById((R.id.btnGoToAlta))

        return vistaFragment3;
    }

    override fun onStart() {
        super.onStart()


        btnHomeGoToIndex.setOnClickListener {


            var action3 = Fragment3Directions.actionFragment3ToFragment1(indexID = "idx")


            vistaFragment3.findNavController().navigate(action3)

        }

        btnHomeGoToIncidente1.setOnClickListener{

            var textIncidente = btnHomeGoToIncidente1.text.toString()
            var action4 = Fragment3Directions.actionFragment3ToFragment4(textIncidente)

            vistaFragment3.findNavController().navigate(action4)
        }

        btnHomeGoToIncidente2.setOnClickListener{

            var textIncidente2 = btnHomeGoToIncidente2.text.toString()
            var action5 = Fragment3Directions.actionFragment3ToFragment4(textIncidente2)

            vistaFragment3.findNavController().navigate(action5)
        }

        btnFloat.setOnClickListener{
            var action6 = Fragment3Directions.actionFragment3ToFragment5(altaID = "alta")

            vistaFragment3.findNavController().navigate(action6)
        }


    }

}