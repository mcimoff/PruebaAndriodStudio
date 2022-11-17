package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

enum class ProviderType {
    BASIC,
    ADMIN
}

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
}