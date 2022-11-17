package com.example.myapplication.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.example.myapplication.PerfilDirections
import com.example.myapplication.R
import com.example.myapplication.btnHomeGoToHome
import com.example.myapplication.vistaDescripIncidentes

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Ajustes.newInstance] factory method to
 * create an instance of this fragment.
 */
class Ajustes : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var viewFragment : View
    lateinit var btnVolverAjustes: Button

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

        viewFragment = inflater.inflate(R.layout.fragment_ajustes, container, false)
        btnVolverAjustes = viewFragment.findViewById((R.id.buttonVolverAjustes))

        return viewFragment
    }

    override fun onStart() {
        super.onStart()

        btnVolverAjustes.setOnClickListener() {

            var action = AjustesDirections.actionAjustesToPerfil()

            viewFragment.findNavController().navigate(action)
        }

    val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())

    Log.d("Preferencias", prefs.getBoolean("night_mode", false).toString() + " - night mode")
    Log.d("Preferencias", prefs.getString("edit_email", "email default") + " - edit email")
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.user_preferences, rootKey)
        }

    }
}