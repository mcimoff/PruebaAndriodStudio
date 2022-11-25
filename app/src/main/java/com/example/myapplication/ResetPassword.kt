package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.reset_password.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ResetPassword.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResetPassword : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var vistaFragmentResetPassword : View
    lateinit var btnGoToLogin: Button
    lateinit var btnSubmit: Button
    lateinit var title: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        vistaFragmentResetPassword = inflater.inflate(R.layout.reset_password, container, false)

        btnGoToLogin = vistaFragmentResetPassword.findViewById((R.id.buttonVolverToLogin))
        btnSubmit = vistaFragmentResetPassword.findViewById((R.id.btn_submit))

        return vistaFragmentResetPassword;
    }

    override fun onStart() {
        super.onStart()

        btnSubmit.setOnClickListener {
            val email: String = et_forgot_email.text.toString().trim { it <= ' ' }
            if (email.isEmpty()) {
                Toast.makeText(
                    activity,
                    "Por favor, ingresa tu email.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                activity,
                                "El correo para reestablecer tu contraseña fue enviado.",
                                Toast.LENGTH_LONG
                            ).show()

                            activity?.finish()
                        } else {
                            Toast.makeText(
                                activity,
                                task.exception!!.message.toString(),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
            }
        }

        btnGoToLogin.setOnClickListener{

            var action = ResetPasswordDirections.actionResetPasswordToLogin(indexID = "index")

            vistaFragmentResetPassword.findNavController().navigate(action)

        }
    }
}