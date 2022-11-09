package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

enum class ProviderType {
    BASIC
}

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navHostFragment: NavHostFragment
    lateinit var receptorTV: TextView
    lateinit var providerTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.navController)

        val bundle: Bundle? = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")
        setup(email ?:"", provider ?: "")
        
        //val bottomNav = findViewById<BottomNavigationView>(id.bottomNavigationView)
    }
    private fun setup(email: String, provider: String) {
        title = "Inicio"

        receptorTV = this.findViewById(R.id.receptorTextView)
        providerTV = this.findViewById(R.id.providerTextView)

        receptorTV.text = email
        providerTV.text = provider


    }
}