package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
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

class Perfil : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var viewFragment : View
    lateinit var btnLogout: Button
    lateinit var receptorTV : TextView
    //lateinit var providerTV : TextView
    lateinit var holaNombre : TextView
    lateinit var esResolutorTV : TextView
    lateinit var puestoTV : TextView
    lateinit var btnAjustes : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        viewFragment = inflater.inflate(R.layout.fragment_perfil, container, false)

        return viewFragment
    }

    @SuppressLint("SetTextI18n")
    override fun onStart() {
        super.onStart()

        val mainActivity = (activity as MainActivity?)!!

        btnLogout = viewFragment.findViewById((R.id.buttonLogOut))

        receptorTV = viewFragment.findViewById(R.id.emailTextView)
        //providerTV = viewFragment.findViewById(R.id.providerTextView)
        btnAjustes = viewFragment.findViewById(R.id.buttonAjustes)
        holaNombre = viewFragment.findViewById(R.id.holaNombreTextView)
        esResolutorTV = viewFragment.findViewById(R.id.esResolutorTextView)
        puestoTV = viewFragment.findViewById(R.id.puestoTextView)


        holaNombre.text = ("Hola ${mainActivity.getUsuario().nombre}!")

        receptorTV.text = ("Email: ${mainActivity.getUsuario().email}")
        //providerTV.text = (activity as MainActivity?)!!.getTipoUsuario()
        puestoTV.text = ("Puesto: ${mainActivity.getUsuario().puesto}")

        if (mainActivity.getUsuario().esResolutor) {
            esResolutorTV.text = "Tipo de usuario: Resolutor"
        } else {
            esResolutorTV.text = "Tipo de usuario: Usuario"
        }



        btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()

            // Get a reference to the SharedPreferences object
            val prefs: SharedPreferences = mainActivity.getSharedPreferences("user_preferences", Context.MODE_PRIVATE)
            val editor = prefs.edit()
            editor.remove("USUARIO_KEY")
            editor.apply()

            var action3 = PerfilDirections.actionPerfilToActivityLogin()

            viewFragment.findNavController().navigate(action3)
        }
    }

    /*override fun onStart() {
        super.onStart()

        val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())

        Log.d("Preferencias", prefs.getBoolean("night_mode", false).toString() + " - night mode")
        Log.d("Preferencias", prefs.getString("edit_email", "email default") + " - edit email")
    }*/

    /*class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.user_preferences, rootKey)
        }

    }*/
}