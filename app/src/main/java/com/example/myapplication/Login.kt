package com.example.myapplication

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth.getInstance

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
    lateinit var btnLogin: Button
    lateinit var btnRegistrarse: Button
    lateinit var idEmail: EditText
    lateinit var idPassword : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setup
        setup()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        vistaFragment = inflater.inflate(R.layout.login, container, false)


        btnLogin = vistaFragment.findViewById(R.id.loginButton)
        btnRegistrarse = vistaFragment.findViewById(R.id.registroButton)

        btnGoToResetPassword = vistaFragment.findViewById(R.id.buttonIndexGoToPassword)

        idEmail = vistaFragment.findViewById((R.id.idEmail))
        idPassword = vistaFragment.findViewById(R.id.idPassword)

        return vistaFragment
    }

    /*override fun onStart() {
        super.onStart()

        btnGoToResetPassword.setOnClickListener{

            var idEmail =  idEmail.text.toString()
            var action = LoginDirections.actionLoginToResetPassword(idEmail)

            vistaFragment.findNavController().navigate(action)
        }

        btnLogin.setOnClickListener{
            //var idPassword = idPassword.text.toString() - idPassword en los parentesis
            var action2 = LoginDirections.actionLoginToMainActivity()

            vistaFragment.findNavController().navigate(action2)
        }

    }*/

    private fun showAlert() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String, provider: ProviderType) {
        val homeIntent = Intent(this.context, MainActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        this.startActivity(homeIntent)
    }

    private fun setup() {

        btnRegistrarse.setOnClickListener{
            if (idEmail.text.isNotEmpty() && idPassword.text.isNotEmpty()) {

                getInstance().
                createUserWithEmailAndPassword(idEmail.text.toString(), idPassword.text.toString()).
                addOnCompleteListener {
                    if (it.isSuccessful) {
                        showHome(it.result?.user?.email ?:"", ProviderType.BASIC) //En caso de que no se envie un email, queda vacio pero no deberia pasar
                    } else {
                        showAlert()
                    }
                }
            }
        }

        btnLogin.setOnClickListener {
            if (idEmail.text.isNotEmpty() && idPassword.text.isNotEmpty()) {

                getInstance().
                signInWithEmailAndPassword(idEmail.text.toString(), idPassword.text.toString()).
                addOnCompleteListener {
                    if (it.isSuccessful) {
                        showHome(it.result?.user?.email ?:"", ProviderType.BASIC) //En caso de que no se envie un email, queda vacio pero no deberia pasar
                    } else {
                        showAlert()
                    }
                }
            }
        }
    }
}