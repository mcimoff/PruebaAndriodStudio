package com.example.myapplication.holders

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Api.Model.IncidentesResponse
import com.example.myapplication.BusquedaIncidentesDirections
import com.example.myapplication.HomeIncidentsDirections
import com.example.myapplication.R

class IdHolder(v: View): RecyclerView.ViewHolder(v) {

    private var view: View
    lateinit var btnDescrip: Button


    init {
        this.view = v
        this.btnDescrip = view.findViewById((R.id.btnGoToDescirp))
    }

    fun setName(name: String){
        val txt: TextView = view.findViewById(R.id.text_name_item_id)
        txt.text = name
    }



    fun getCardLayout (): CardView {
        return view.findViewById(R.id.card_package_item2)
    }


}