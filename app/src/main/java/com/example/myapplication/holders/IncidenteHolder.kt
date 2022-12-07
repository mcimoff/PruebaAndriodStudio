package com.example.myapplication.holders

import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Api.Model.IncidentesResponse
import com.example.myapplication.Api.Model.UsuarioResponse
import com.example.myapplication.Entities.Incidente
import com.example.myapplication.HomeIncidentsDirections
import com.example.myapplication.R
import com.google.gson.Gson
import java.text.FieldPosition

class IncidenteHolder(v: View): RecyclerView.ViewHolder(v) {

    private var view: View
    lateinit var btnDescrip: Button
    private lateinit var usuario: UsuarioResponse
    lateinit var context: Context


    init {
        context = v.context
        this.view = v
        this.btnDescrip = view.findViewById((R.id.btnGoToDescirp))
    }

    fun setName(name: String){
        val txt: TextView = view.findViewById(R.id.text_name_item)
        txt.text = name
    }



    fun getCardLayout (): CardView{
        return view.findViewById(R.id.card_package_item)
    }

    fun redirection (incidente: IncidentesResponse){

        btnDescrip.setOnClickListener {

            if (getData(context).esResolutor) {
                val txt: TextView = view.findViewById(R.id.text_name_item)
                var action = HomeIncidentsDirections.actionHomeIncidentsToDescripIncidentesResolutor(incidente)

                view.findNavController().navigate(action)
            } else {
                val txt: TextView = view.findViewById(R.id.text_name_item)
                var action = HomeIncidentsDirections.actionHomeIncidentsToDescripIncidents(incidente)

                view.findNavController().navigate(action)
            }
        }
    }

    fun getData(context: Context): UsuarioResponse {
        val preferences = context.getSharedPreferences("user_preferences", Context.MODE_PRIVATE)
        usuario = getObject(preferences, "USUARIO_KEY", UsuarioResponse::class.java) as UsuarioResponse
        return usuario
    }

    fun getObject(preferences: SharedPreferences, key: String, clazz: Class<*>): Any? {
        val gson = Gson()
        val json = preferences.getString(key, null)
        return gson.fromJson(json, clazz)
    }

//    fun getData(context: Context): UsuarioResponse {
//        if (usuario == null) {
//
//            // Get a reference to the preferences
//            val preferences = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
//            // Retrieve the values of the data class fields from the preferences
//            val field1 = preferences.getString("field1", "")
//            val field2 = preferences.getInt("field2", 0)
//
//            // Use the data class constructor to create a new instance of the object
//            myData = MyData(field1, field2)
//        }
//            return myData
//    }

//    // Define a function to save the data class instance to preferences
//    fun saveData(context: Context, data: MyData) {
//        // Get a reference to the preferences
//        val preferences = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
//
//        // Use the put* methods of the SharedPreferences.Editor to save the values of the data class fields
//        preferences.edit()
//            .putString("field1", data.field1)
//            .putInt("field2", data.field2)
//            .apply()
//
//        // Save the data class instance to the holder variable
//        myData = data
//        }
//    }

}