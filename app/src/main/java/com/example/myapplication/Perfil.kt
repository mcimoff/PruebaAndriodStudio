package com.example.myapplication

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Perfil : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.user_preferences, rootKey)
    }
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    /*override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil, container, false)
    }*/

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