package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.edit
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.preference.PreferenceManager
import com.example.myapplication.Api.Model.UsuarioResponse
import com.example.myapplication.activities.SettingsActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson

enum class ProviderType {
    BASIC,
    ADMIN
}

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navHostFragment: NavHostFragment

    private fun putObject(editor: SharedPreferences.Editor, key: String, `object`: Any) {
        val gson = Gson()
        val json = gson.toJson(`object`)
        editor.putString(key, json)
    }

    fun getObject(preferences: SharedPreferences, key: String, clazz: Class<*>): Any? {
        val gson = Gson()
        val json = preferences.getString(key, null)
        return gson.fromJson(json, clazz)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        Firebase.database.setPersistenceEnabled(true)

//        FirebaseAuth.getInstance().setPersistence(firebase.auth.Auth.Persistence.SESSION)
//        FirebaseAuth.getInstance().setPersistenceEnabled(true)
//        FirebaseAuth.getInstance().setPersistenceEnabled(true)
//
//        Firebase.auth().setPersistence(firebase.auth.Auth.Persistence.SESSION)
//
//        FirebaseDatabase.getInstance().setPersistenceEnabled(true)


        val prefs = PreferenceManager.getDefaultSharedPreferences(this)

        val preferences = getSharedPreferences("user_preferences", Context.MODE_PRIVATE)

        // Store the object in the SharedPreferences file
        val editor = preferences.edit()
        putObject(editor, "USUARIO_KEY", getUsuario())
        editor.apply()

        // Retrieve the object from the SharedPreferences file
        //val usuario = getObject(preferences, "USUARIO_KEY", UsuarioResponse::class.java) as UsuarioResponse


        val darkModeValues = resources.getStringArray(R.array.dark_mode_values)

        when (PreferenceManager.getDefaultSharedPreferences(this)
            .getString(getString(R.string.dark_mode), getString(R.string.dark_mode_def_value))) {
            darkModeValues[0] -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            darkModeValues[1] -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            darkModeValues[2] -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            darkModeValues[3] -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
        }
        setContentView(R.layout.activity_main)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.navController)
        
        //val bottomNav = findViewById<BottomNavigationView>(id.bottomNavigationView)
    }

//    fun getDatosUsuario(): String? {
//        val bundle: Bundle? = intent.extras
//        return bundle?.getString("usuario")
//    }

    fun getEmail(): String? {
        val bundle: Bundle? = intent.extras
        return bundle?.getString("email")
    }

    fun getUsuario(): UsuarioResponse {
        val usuario = intent.getSerializableExtra("usuario") as UsuarioResponse

        // Check if the extra is of the correct type
//        if (usuario is UsuarioResponse) {
//            // Cast the extra to the correct type
//            val usuarioResponse = usuario as UsuarioResponse
//
//            // Use the usuarioResponse object in your code
//            println("Name: ${usuarioResponse.nombre}, Email: ${usuarioResponse.email}")
//        } else {
//            // Handle the case where the extra is not of the correct type
//            println("The extra is not a UsuarioResponse object")
//        }
        return usuario
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        if (item.itemId == R.id.settings) {
            val intent = Intent(this, SettingsActivity::class.java).apply {
                putExtra("email", getEmail())
            }
            startActivity(intent)
            true
        } else super.onOptionsItemSelected(item)

}