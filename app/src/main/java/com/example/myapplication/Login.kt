package com.example.myapplication

import android.app.AlertDialog
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.compose.animation.animateColorAsState
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Api.Model.IncidentesResponse
import com.example.myapplication.Api.Model.Model
import com.example.myapplication.Api.Model.UsuarioResponse
import com.example.myapplication.adapters.IncidenteListAdapter
import com.example.myapplication.fragments.AjustesDirections
import com.google.android.gms.auth.GoogleAuthUtil.getToken
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    lateinit var activityLogin : ActivityLogin
    lateinit var vistaFragment : View
    lateinit var btnGoToResetPassword: Button
    lateinit var btnLogin: Button
    lateinit var btnRegistrarse: Button
    lateinit var idEmail: EditText
    lateinit var idPassword : EditText
    lateinit var validacionPassword : TextView
    lateinit var validacionEmail : TextView
    val api = Model.create("http://192.168.0.134:3000")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityLogin = (activity as ActivityLogin?)!!

        (activity as ActivityLogin?)!!.startFirebaseAnalyticsInstance()

    }
//    private fun login(email: String, password: String) {
//        FirebaseAuth.getInstance().
//        signInWithEmailAndPassword(idEmail.text.toString(), idPassword.text.toString()).
//        addOnCompleteListener {
//            if (it.isSuccessful) {
//
//                Log.d(TAG, "signInWithEmail:success")
//                val user = FirebaseAuth.getInstance().currentUser.toString()
//                //FirebaseAuth.getInstance().setPersistence(FirebaseAuth.Persistence.SESSION)
//                api.getUsuarioXEmail(it.result?.user?.email ?:"")?.enqueue(object : Callback<UsuarioResponse?> {
//                    override fun onResponse(
//                        call: Call<UsuarioResponse?>,
//                        response: Response<UsuarioResponse?>
//                    ){
//                        if (response.code() == 200){
//                            val response: UsuarioResponse = (response.body() as UsuarioResponse)
//                            val id = response._id.toString()
//                            val usuario = response.toString()
//                            Log.d("Usuario","Llego: $usuario")
//                            activityLogin.showHome(it.result?.user?.email ?:"", response)
//
//                            /*if(usuario != null){
//
//                                linearLayoutManager = LinearLayoutManager(context)
//
//                                listIncidentes.layoutManager = linearLayoutManager
//
//                                incidenteListAdapter = IncidenteListAdapter(incidentes)
//
//                                listIncidentes.adapter = incidenteListAdapter
//
//                            }*/
//                        }
//                    }
//                    override fun onFailure(call: Call<UsuarioResponse?>, t: Throwable) {
//                        // TODO("Not yet implemented")
//                        call.toString()
//                    }
//                }
//                )
//            } else {
//                Log.w(TAG, "signInWithEmail:failure", it.exception)
//                showAlert()
//            }
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        vistaFragment = inflater.inflate(R.layout.login, container, false)


        btnLogin = vistaFragment.findViewById(R.id.logeoButton)
        btnRegistrarse = vistaFragment.findViewById(R.id.signUpButton)

        btnGoToResetPassword = vistaFragment.findViewById(R.id.buttonGoToResetPassword)

        idEmail = vistaFragment.findViewById((R.id.idEmailText))
        idPassword = vistaFragment.findViewById(R.id.idPasswordText)

        validacionEmail = vistaFragment.findViewById((R.id.validacionEmailTV))
        validacionPassword = vistaFragment.findViewById((R.id.validacionPasswordTV))


        idEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                validacionEmail.text = ""
                validacionEmail.visibility = View.INVISIBLE
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val email = s.toString()
                activityLogin.validateEmail(email, validacionEmail)
            }

            override fun afterTextChanged(s: Editable?) {
                val password = s.toString()
            }
        })


        idPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                validacionPassword.text = ""
                validacionPassword.visibility = View.INVISIBLE
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val password = s.toString()
                validatePassword(password, validacionPassword)
            }

            override fun afterTextChanged(s: Editable?) {
                val password = s.toString()
            }
        })

        setup()

        return vistaFragment
    }
    private fun setup() {
        activityLogin.title = "Autenticacion"

        btnLogin.setOnClickListener {
            if (idEmail.text.isNotEmpty() && idPassword.text.isNotEmpty()) {
                FirebaseAuth.getInstance().
                signInWithEmailAndPassword(idEmail.text.toString(), idPassword.text.toString()).
                addOnCompleteListener {
                    if (it.isSuccessful) {
                        Log.d(TAG, "signInWithEmail:success")

//                        FirebaseAuth.getInstance().getAccessToken(true)
//                        FirebaseAuth.getInstance().currentUser?.getIdToken(true)
//                        FirebaseUser.getToken()
//                        Firebase.auth().currentUser.getIdToken()

                        api.getUsuarioXEmail(it.result?.user?.email ?:"")?.enqueue(object : Callback<UsuarioResponse?> {
                            override fun onResponse(
                                call: Call<UsuarioResponse?>,
                                response: Response<UsuarioResponse?>
                            ){
                                if (response.code() == 200){
                                    val response: UsuarioResponse = (response.body() as UsuarioResponse)
                                    val id = response._id.toString()
                                    val usuario = response.toString()
                                    Log.d("Usuario","Llego: $usuario")
                                    activityLogin.showHome(it.result?.user?.email ?:"", response)

                                    /*if(usuario != null){

                                        linearLayoutManager = LinearLayoutManager(context)

                                        listIncidentes.layoutManager = linearLayoutManager

                                        incidenteListAdapter = IncidenteListAdapter(incidentes)

                                        listIncidentes.adapter = incidenteListAdapter

                                    }*/

                                }
                            }
                            override fun onFailure(call: Call<UsuarioResponse?>, t: Throwable) {
                                // TODO("Not yet implemented")
                                call.toString()
                            }
                        }
                        )
                    } else {
                        Log.w(TAG, "signInWithEmail:failure", it.exception)
                        showAlert()
                    }
                }
            }
        }


        btnGoToResetPassword.setOnClickListener {

            var action = LoginDirections.actionLoginToResetPassword()

            vistaFragment.findNavController().navigate(action)

        }
    }

    private fun showAlert() {
        val builder = (activity as ActivityLogin?)!!.getBuilder()
        builder.setTitle("Error")
        builder.setMessage("Email o contraseña incorrectos.")
        builder.setPositiveButton("Aceptar", null)

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun validatePassword(password: String, errorTextView: TextView) {
        if (password.length < 6) {
            errorTextView.text = "La contraseña debe ser mayor o igual a 6 carácteres."
            errorTextView.visibility = View.VISIBLE
        } else {
            errorTextView.text = ""
            errorTextView.visibility = View.GONE
        }

//        if (!password.matches(Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$"))) {
//            return "Password must contain at least one lowercase letter, one uppercase letter, and one digit"
//        }
    }

//    fun validateEmail(email: String, errorTextView: TextView) {
//        if (!email.contains("@")) {
//            errorTextView.text = "El formato del email es incorrecto."
//            errorTextView.visibility = View.VISIBLE
//        } else {
//            errorTextView.text = ""
//            errorTextView.visibility = View.GONE
//        }
//    }
}