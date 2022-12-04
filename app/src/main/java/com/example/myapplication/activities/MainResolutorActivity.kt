package com.example.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.Api.Model.UsuarioResponse
import com.example.myapplication.R
import com.google.gson.Gson
import org.json.JSONObject

class MainResolutorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_resolutor)
    }

    /*fun getDatosUsuario(): UsuarioResponse? {
        val bundle: Bundle? = intent.extras
        val usuarioString = bundle?.getString("usuario")
        usuarioString.
        val json = usuarioString?.let { JSONObject(it) }
        val topic = Gson().fromJson(json, UsuarioResponse::class.java)
        return UsuarioResponse(usuarioString)
    }*/

    fun getDatosUsuario(): String? {
        val bundle: Bundle? = intent.extras
        return bundle?.getString("usuario")
    }
}