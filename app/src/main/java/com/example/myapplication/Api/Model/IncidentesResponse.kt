package com.example.myapplication.Api.Model

import android.os.Parcelable
import com.example.myapplication.Entities.Incidente
import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.properties.Delegates

 data class IncidentesResponse (
     @field:SerializedName("_id")val _id :Int,
     @field:SerializedName("fechaCreacion")val fechaCreacion: Date,
     @field:SerializedName("titulo")val titulo: String,
     @field:SerializedName("descripcionUsuario")val descripcionUsuario: String,


     )