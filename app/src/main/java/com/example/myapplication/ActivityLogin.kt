package com.example.myapplication

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Api.Model.UsuarioResponse
import com.example.myapplication.activities.MainResolutorActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson


class ActivityLogin : AppCompatActivity() {
    lateinit var btnLogin: Button
    lateinit var btnRegistrarse: Button
    lateinit var idEmail: EditText
    lateinit var idPassword : EditText
    lateinit var btnResetPassword : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (isUserSignedIn()) {
            val preferences = getSharedPreferencesFromActivity()
            val usuario = getObject(preferences, "USUARIO_KEY", UsuarioResponse::class.java) as UsuarioResponse
            showHome("", usuario)
        }


        //Analytics
//        val analytics = FirebaseAnalytics.getInstance(this)
//        val bundle = Bundle()
//        bundle.putString("message", "Integracion de Firebase completa")
//        analytics.logEvent("InitScreen", bundle)

        // Setup
        //setup()
    }

    fun isUserSignedIn(): Boolean {
        return FirebaseAuth.getInstance().currentUser != null
    }

    fun startFirebaseAnalyticsInstance() {
        val analytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message", "Integracion de Firebase completa")
        analytics.logEvent("InitScreen", bundle)
    }

    fun getSharedPreferencesFromActivity(): SharedPreferences {
        return getSharedPreferences("user_preferences", Context.MODE_PRIVATE)
    }

    /*override fun onStart() {
        super.onStart()

        btnGoToResetPassword.setOnClickListener{

            var idEmail =  idEmail.text.toString()
            var action = LoginDirections.actionLoginToResetPassword(idEmail)

            vistaFragment.findNavController().navigate(action)
        }*/

        /*btnLogin.setOnClickListener{
            //var idPassword = idPassword.text.toString() - idPassword en los parentesis
            var action2 = LoginDirections.actionLoginToMainActivity()

            vistaFragment.findNavController().navigate(action2)
        }

    }*/

//    private fun setup() {
//        title = "Autenticacion"
//
//        btnLogin = this.findViewById(R.id.logeoButton)
//        btnRegistrarse = this.findViewById(R.id.signUpButton)
//        idEmail = this.findViewById((R.id.idEmailText))
//        idPassword = this.findViewById(R.id.idPasswordText)
//        btnResetPassword = this.findViewById(R.id.buttonGoToResetPassword)
//
//        btnRegistrarse.setOnClickListener{
//            if (idEmail.text.isNotEmpty() && idPassword.text.isNotEmpty()) {
//
//                FirebaseAuth.getInstance().
//                createUserWithEmailAndPassword(idEmail.text.toString(), idPassword.text.toString()).
//                addOnCompleteListener {
//                    if (it.isSuccessful) {
//                        showHome(it.result?.user?.email ?:"", ProviderType.BASIC) //En caso de que no se envie un email, queda vacio pero no deberia pasar
//                    } else {
//                        showAlert()
//                    }
//                }
//            }
//        }
//
//        btnLogin.setOnClickListener {
//            if (idEmail.text.isNotEmpty() && idPassword.text.isNotEmpty()) {
//
//                FirebaseAuth.getInstance().
//                signInWithEmailAndPassword(idEmail.text.toString(), idPassword.text.toString()).
//                addOnCompleteListener {
//                    if (it.isSuccessful) {
//                        showHome(it.result?.user?.email ?:"", ProviderType.BASIC) //En caso de que no se envie un email, queda vacio pero no deberia pasar
//                    } else {
//                        showAlert()
//                    }
//                }
//            }
//        }
//
//        btnResetPassword.setOnClickListener {
//
//            var action = AjustesDirections.actionAjustesToPerfil()
//
//            viewFragment.findNavController().navigate(action)
//
//            // Launch the forgot password screen when the user clicks on the forgot password text.
//            startActivity(Intent(this@ActivityLogin, supportFragmentManager.::class.java))
//
//        }
//    }

    fun getBuilder() : AlertDialog.Builder{
        return AlertDialog.Builder(this)
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun showHome(email: String, usuario: UsuarioResponse) {
        var homeIntent: Intent? = null
        if (usuario.esResolutor) {
//            homeIntent = Intent(this, MainResolutorActivity::class.java).apply {
//                putExtra("usuario", usuario)
//            }
            homeIntent = Intent(this, MainResolutorActivity::class.java)
            homeIntent.putExtra("usuario", usuario)
        } else {
            homeIntent = Intent(this, MainActivity::class.java).apply {
                putExtra("usuario", usuario)
            }
        }
        startActivity(homeIntent)
    }

    fun getObject(preferences: SharedPreferences, key: String, clazz: Class<*>): Any? {
        val gson = Gson()
        val json = preferences.getString(key, null)
        return gson.fromJson(json, clazz)
    }

//    fun showHomeWOResponse() {
//        val homeIntent = Intent(this, MainActivity::class.java)
//        startActivity(homeIntent)
//    }







//    private fun showHome(email: String, provider: ProviderType) {
//        val homeIntent = Intent(this, MainActivity::class.java).apply {
//            putExtra("email", email)
//            putExtra("provider", provider.name)
//        }
//        startActivity(homeIntent)
//    }
}
