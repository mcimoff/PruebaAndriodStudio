package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.findFragment
import androidx.navigation.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment1.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment1 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var vistaFragment : View
    lateinit var btnFragment1IndexGoToPassword: Button
    lateinit var btnFragment1IndexGoToHome: Button
    lateinit var idEmail: EditText
    lateinit var idPassword : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment



        vistaFragment = inflater.inflate(R.layout.fragment_1, container, false)

        btnFragment1IndexGoToPassword = vistaFragment.findViewById(R.id.buttonIndexGoToPassword)
        btnFragment1IndexGoToHome = vistaFragment.findViewById(R.id.buttonIndexGoToHome)
        idEmail = vistaFragment.findViewById((R.id.idEmail))
        idPassword = vistaFragment.findViewById(R.id.idPassword)

        return vistaFragment;
    }

    override fun onStart() {
        super.onStart()

        btnFragment1IndexGoToPassword.setOnClickListener{

            var idEmail =  idEmail.text.toString()
            var action = Fragment1Directions.actionFragment1ToFragment2(idEmail)

            vistaFragment.findNavController().navigate(action)
        }

        btnFragment1IndexGoToHome.setOnClickListener{
            var idPassword = idPassword.text.toString()
            var action2 = Fragment1Directions.actionFragment1ToFragment3(idPassword)

            vistaFragment.findNavController().navigate(action2)
        }

    }


}