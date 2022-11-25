package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import com.example.myapplication.activities.SettingsActivity

enum class ProviderType {
    BASIC,
    ADMIN
}

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val darkModeValues = resources.getStringArray(R.array.dark_mode_values)
        // The apps theme is decided depending upon the saved preferences on app startup
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
    fun getEmail(): String? {
        val bundle: Bundle? = intent.extras
        return bundle?.getString("email")
    }
    fun getTipoUsuario(): String? {
        val bundle: Bundle? = intent.extras
        return bundle?.getString("provider")
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