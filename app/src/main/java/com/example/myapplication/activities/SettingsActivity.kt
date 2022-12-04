package com.example.myapplication.activities

import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.example.myapplication.R
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class SettingsActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener,
    Preference.SummaryProvider<ListPreference> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings, SettingsFragment())
            .commit()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        PreferenceManager.getDefaultSharedPreferences(this)
            .registerOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        val darkModeString = getString(R.string.dark_mode)
        val resetPasswordString = "resetPasswordPreference"
        //val resetEmailString = "resetEmailPreference"

        val bundle: Bundle? = intent.extras
        val email = bundle?.getString("email")

        key?.let {
            when (it) {
                darkModeString -> sharedPreferences?.let { pref ->
                    val darkModeValues = resources.getStringArray(R.array.dark_mode_values)
                    when (pref.getString(darkModeString, darkModeValues[0])) {
                        darkModeValues[0] -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                        darkModeValues[1] -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                        darkModeValues[2] -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    }
                }
                resetPasswordString -> sharedPreferences?.let {
                    FirebaseAuth.getInstance().sendPasswordResetEmail(email!!)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(
                                    this,
                                    "El correo para reestablecer tu contraseña fue enviado.",
                                    Toast.LENGTH_LONG
                                ).show()

                                this?.finish()
                            } else {
                                Toast.makeText(
                                    this,
                                    task.exception!!.message.toString(),
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                }
//                resetEmailString -> sharedPreferences?.let {
//                    FirebaseAuth.getInstance().updateCurrentUser(email!!)
//                        .addOnCompleteListener { task ->
//                            if (task.isSuccessful) {
//                                Toast.makeText(
//                                    this,
//                                    "El correo para reestablecer tu email fue enviado.",
//                                    Toast.LENGTH_LONG
//                                ).show()
//
//                                this?.finish()
//                            } else {
//                                Toast.makeText(
//                                    this,
//                                    task.exception!!.message.toString(),
//                                    Toast.LENGTH_LONG
//                                ).show()
//                            }
//                        }
//                }
                else -> {}
            }
        }
    }

    class SettingsFragment : PreferenceFragmentCompat() {
//        val USER_ID_KEY = "user_id"
//        val USER_EMAIL_KEY = "user_email"

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.user_preferences, rootKey)
        }

//        // Save the user's ID and email using SharedPreferences
//        fun saveUser(authResult: AuthResult) {
//            val sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE)
//            val editor = sharedPreferences.edit()
//            editor.putString(USER_ID_KEY, authResult.user.uid)
//            editor.putString(USER_EMAIL_KEY, authResult.user.email)
//            editor.apply()
//        }
//
//        // Check if the user is already logged in
//        fun isUserLoggedIn(): Boolean {
//            val sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE)
//            val userId = sharedPreferences.getString(USER_ID_KEY, null)
//            val userEmail = sharedPreferences.getString(USER_EMAIL_KEY, null)
//            return userId != null && userEmail != null
//        }
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        PreferenceManager.getDefaultSharedPreferences(this)
//            .unregisterOnSharedPreferenceChangeListener(this)
//    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }


    override fun provideSummary(preference: ListPreference): CharSequence? {
        return if (preference?.key == getString(R.string.dark_mode)) preference.entry
        else "Unknown Preference"
    }
}