package com.example.myapplication.Api.Model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UsuarioResponse (
    @field:SerializedName("_id")val _id :Int,
    @field:SerializedName("email")val email: String,
    @field:SerializedName("nombre")val nombre: String,
    @field:SerializedName("apellido")val apellido: String,
    @field:SerializedName("pais")val pais: String,
    @field:SerializedName("area")val area: Area,
    //@field:SerializedName("unidadnegocio")val unidadnegocio: String,
    @field:SerializedName("celular")val celular: String,
    @field:SerializedName("interno")val interno: String,
    //@field:SerializedName("incidentes")val incidentes: String,
    @field:SerializedName("esVip")val esVip: Boolean,
    @field:SerializedName("puesto")val puesto: String,
    @field:SerializedName("esResolutor")val esResolutor: Boolean,
) : Serializable