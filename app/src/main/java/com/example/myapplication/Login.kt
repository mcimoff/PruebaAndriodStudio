package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Login.newInstance] factory method to
 * create an instance of this fragment.
 */
class Login : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var vistaFragment : View
    lateinit var btnGoToResetPassword: Button
    lateinit var btnGoToHomeIncidentes: Button
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



        vistaFragment = inflater.inflate(R.layout.login, container, false)

        btnGoToResetPassword = vistaFragment.findViewById(R.id.buttonIndexGoToPassword)
        btnGoToHomeIncidentes = vistaFragment.findViewById(R.id.buttonLoginHomeIncidentes)
        idEmail = vistaFragment.findViewById((R.id.idEmail))
        idPassword = vistaFragment.findViewById(R.id.idPassword)

        return vistaFragment
    }

    override fun onStart() {
        super.onStart()

        btnGoToResetPassword.setOnClickListener{

            var idEmail =  idEmail.text.toString()
            var action = LoginDirections.actionLoginToResetPassword(idEmail)

            vistaFragment.findNavController().navigate(action)
        }

        btnGoToHomeIncidentes.setOnClickListener{
            //var idPassword = idPassword.text.toString() - idPassword en los parentesis
            var action2 = LoginDirections.actionLoginToMainActivity()

            vistaFragment.findNavController().navigate(action2)
        }

    }


}