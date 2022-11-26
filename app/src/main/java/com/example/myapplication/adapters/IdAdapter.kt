package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Api.Model.IncidentesResponse
import com.example.myapplication.R
import com.example.myapplication.holders.IdHolder

class IdAdapter (
    private var incidenteList: MutableList<IncidentesResponse>
): RecyclerView.Adapter<IdHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IdHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_id_item,parent,false)
        return (IdHolder(view))
    }


    override fun onBindViewHolder(holder: IdHolder, position: Int) {
        holder.setName(incidenteList[position].titulo)




    }

    override fun getItemCount(): Int {
        return  incidenteList.size
    }



    fun setData(newData: MutableList<IncidentesResponse>){
        this.incidenteList = newData
        this.notifyDataSetChanged()
    }
}