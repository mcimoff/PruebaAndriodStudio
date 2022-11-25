package com.example.myapplication.Api.Model

import android.os.Parcel
import android.os.Parcelable
import com.example.myapplication.Entities.Incidente
import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.properties.Delegates

 data class IncidentesResponse (
     @field:SerializedName("_id")val _id :Int,
     //@field:SerializedName("fechaCreacion")val fechaCreacion: Date,
     @field:SerializedName("titulo")val titulo: String,
     @field:SerializedName("descripcionUsuario")val descripcionUsuario: String,
     @field:SerializedName("estadoActual")val estadoActual: String,


     ) : Parcelable {
     constructor(parcel: Parcel) : this(
         parcel.readInt(),
         parcel.readString().toString(),
         parcel.readString().toString(),
         parcel.readString().toString()
     ) {
     }

     override fun writeToParcel(parcel: Parcel, flags: Int) {
         parcel.writeInt(_id)
         parcel.writeString(titulo)
         parcel.writeString(descripcionUsuario)
         parcel.writeString(estadoActual)
     }

     override fun describeContents(): Int {
         return 0
     }

     companion object CREATOR : Parcelable.Creator<IncidentesResponse> {
         override fun createFromParcel(parcel: Parcel): IncidentesResponse {
             return IncidentesResponse(parcel)
         }

         override fun newArray(size: Int): Array<IncidentesResponse?> {
             return arrayOfNulls(size)
         }
     }
 }