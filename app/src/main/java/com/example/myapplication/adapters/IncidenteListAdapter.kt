package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Api.Model.IncidentesResponse
import com.example.myapplication.Entities.Incidente
import com.example.myapplication.R
import com.example.myapplication.holders.IncidenteHolder
import com.example.myapplication.item_incidenteDirections

class IncidenteListAdapter(
    private var incidenteList: MutableList<IncidentesResponse>,
    val onItemClick: (Int) -> Boolean
): RecyclerView.Adapter<IncidenteHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncidenteHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_item_incidente,parent,false)
        return (IncidenteHolder(view))
    }


    override fun onBindViewHolder(holder: IncidenteHolder, position: Int) {
        holder.setName(incidenteList[position].titulo)
        holder.redirection(incidenteList[position])



    }

    override fun getItemCount(): Int {
        return  incidenteList.size
    }



    fun setData(newData: MutableList<IncidentesResponse>){
        this.incidenteList = newData
        this.notifyDataSetChanged()
    }
}