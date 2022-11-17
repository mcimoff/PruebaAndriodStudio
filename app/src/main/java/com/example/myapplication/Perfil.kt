package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Perfil : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var viewFragment : View
    lateinit var receptorTV : TextView
    lateinit var providerTV : TextView
    lateinit var btnAjustes : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewFragment = inflater.inflate(R.layout.fragment_perfil, container, false)

        return viewFragment
    }

    override fun onStart() {
        super.onStart()
        receptorTV = viewFragment.findViewById(R.id.receptorTextView)
        providerTV = viewFragment.findViewById(R.id.providerTextView)
        btnAjustes = viewFragment.findViewById(R.id.buttonAjustes)

        receptorTV.text = (activity as MainActivity?)!!.getEmail()
        providerTV.text = (activity as MainActivity?)!!.getTipoUsuario()

        btnAjustes.setOnClickListener {

            var action = PerfilDirections.actionPerfilToAjustes()

            viewFragment.findNavController().navigate(action)
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