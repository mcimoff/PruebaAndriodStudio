package com.example.myapplication.holders

import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Api.Model.IncidentesResponse
import com.example.myapplication.Api.Model.UsuarioResponse
import com.example.myapplication.BusquedaIncidentes
import com.example.myapplication.BusquedaIncidentesDirections
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
        this.btnDescrip = view.findViewById((R.id.btnGoToDescrip))
    }

    fun setName(name: String){
        val txt: TextView = view.findViewById(R.id.text_name_item)
        txt.text = name
    }

    fun getCardLayout (): CardView{
        return view.findViewById(R.id.card_package_item)
    }

    fun redirection (incidente: IncidentesResponse){

        //val navController = view.findNavController()
        var action: Any?
        var actionResolutor: Any?


        btnDescrip.setOnClickListener {

            if (view.findNavController().currentDestination?.id == R.id.busquedaIncidentes) {

                action = BusquedaIncidentesDirections.actionBusquedaIncidentesToDescripIncidents(incidente)
                actionResolutor = BusquedaIncidentesDirections.actionBusquedaIncidentesToDescripIncidentsResolutor(incidente)

            } else {
                action = HomeIncidentsDirections.actionHomeIncidentsToDescripIncidents(incidente)
                actionResolutor = HomeIncidentsDirections.actionHomeIncidentsToDescripIncidentesResolutor(incidente)
            }

            if (getData(context).esResolutor) {
                val txt: TextView = view.findViewById(R.id.text_name_item)

                view.findNavController().navigate(actionResolutor as NavDirections)
            } else {
                val txt: TextView = view.findViewById(R.id.text_name_item)

                view.findNavController().navigate(action as NavDirections)
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

}