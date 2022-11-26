package com.example.myapplication

import android.app.AlertDialog
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Api.Model.IncidentesResponse
import com.example.myapplication.Api.Model.Model
import com.example.myapplication.adapters.IncidenteListAdapter
import com.example.myapplication.fragments.AjustesDirections
import com.google.firebase.auth.FirebaseAuth
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

    lateinit var vistaFragment : View
    lateinit var btnGoToResetPassword: Button
    lateinit var btnLogin: Button
    lateinit var btnRegistrarse: Button
    lateinit var idEmail: EditText
    lateinit var idPassword : EditText
    val api = Model.create("http://192.168.0.13:3000")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity as ActivityLogin?)!!.startFirebaseAnalyticsInstance()

    }

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

        setup()

        return vistaFragment
    }

//    override fun onStart() {
//        super.onStart()
//
//        btnGoToResetPassword.setOnClickListener{
//
//            var idEmail =  idEmail.text.toString()
//            var action = LoginDirections.actionLoginToResetPassword(idEmail)
//
//            vistaFragment.findNavController().navigate(action)
//        }
//
//        btnLogin.setOnClickListener{
//            //var idPassword = idPassword.text.toString() - idPassword en los parentesis
//            var action2 = LoginDirections.actionLoginToMainActivity()
//
//            vistaFragment.findNavController().navigate(action2)
//        }
//
//    }
    private fun setup() {
        val activityLogin = (activity as ActivityLogin?)!!

        activityLogin.title = "Autenticacion"

        btnRegistrarse.setOnClickListener{
            if (idEmail.text.isNotEmpty() && idPassword.text.isNotEmpty()) {

                FirebaseAuth.getInstance().
                createUserWithEmailAndPassword(idEmail.text.toString(), idPassword.text.toString()).
                addOnCompleteListener {
                    if (it.isSuccessful) {
                        activityLogin.showHome(it.result?.user?.email ?:"") //En caso de que no se envie un email, queda vacio pero no deberia pasar
                    } else {
                        showAlert()
                    }
                }
            }
        }

        btnLogin.setOnClickListener {
            if (idEmail.text.isNotEmpty() && idPassword.text.isNotEmpty()) {
                FirebaseAuth.getInstance().
                signInWithEmailAndPassword(idEmail.text.toString(), idPassword.text.toString()).
                addOnCompleteListener {
                    if (it.isSuccessful) {
                        activityLogin.showHome(it.result?.user?.email ?:"")
                    } else {
                        showAlert()
                    }
                }
            }
        }

        btnGoToResetPassword.setOnClickListener {

            var action = LoginDirections.actionLoginToResetPassword()

            vistaFragment.findNavController().navigate(action)

        }
        /*api.getUsuarioXEmail()?.enqueue(object : Callback<List<IncidentesResponse>?> {
            override fun onResponse(
                call: Call<List<IncidentesResponse>?>,
                response: Response<List<IncidentesResponse>?>
            ){
                if (response.code() == 200){
                    val response: List<IncidentesResponse>? =(response.body() as List<IncidentesResponse>)!!


                    val incidentes = (response as List<IncidentesResponse>).toMutableList()
                    Log.i(ContentValues.TAG, "Llego: $incidentes")




                    val titulos = arrayOfNulls<IncidentesResponse>(size = incidentes?.size ?:0)





                    if(incidentes != null){

                        linearLayoutManager = LinearLayoutManager(context)

                        listIncidentes.layoutManager = linearLayoutManager

                        incidenteListAdapter = IncidenteListAdapter(incidentes)

                        listIncidentes.adapter = incidenteListAdapter


                    }

                }

            }

            override fun onFailure(call: Call<List<IncidentesResponse>?>, t: Throwable) {
                // TODO("Not yet implemented")
                call.toString()
            }
        }
        )*/
    }

    private fun showAlert() {
        val builder = (activity as ActivityLogin?)!!.getBuilder()
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}