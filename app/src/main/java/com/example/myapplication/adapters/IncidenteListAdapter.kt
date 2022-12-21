package com.example.myapplication.adapters

import android.R.attr.data
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Api.Model.IncidentesResponse
import com.example.myapplication.R
import com.example.myapplication.holders.IncidenteHolder
import kotlinx.android.synthetic.main.fragment_item_incidente.view.*


class IncidenteListAdapter(
    private var incidenteList: MutableList<IncidentesResponse>,
): RecyclerView.Adapter<IncidenteHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncidenteHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_item_incidente,parent,false)
        return (IncidenteHolder(view))
    }


    override fun onBindViewHolder(holder: IncidenteHolder, position: Int) {

        holder.setName(incidenteList[position].titulo)
        holder.redirection(incidenteList[position])

        if (incidenteList[position].estadoActual == "Resuelto") {
            holder.itemView.constraintLayout.
            setBackgroundColor(ContextCompat.getColor(holder.context, R.color.incidenteResueltoBackground))
        }
    }

    override fun getItemCount(): Int {
        return  incidenteList.size
    }



    fun setData(newData: MutableList<IncidentesResponse>){
        this.incidenteList = newData
        this.notifyDataSetChanged()
    }
}