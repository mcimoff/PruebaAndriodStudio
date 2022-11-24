package com.example.myapplication.Api.Model

import com.google.gson.annotations.SerializedName

data class UsuarioResponse (
    @field:SerializedName("_id")val _id :Int,
    @field:SerializedName("email")val titulo: String,
    @field:SerializedName("nombre")val nombre: String,
    @field:SerializedName("apellido")val apellido: String,
    @field:SerializedName("pais")val pais: String,
    //@field:SerializedName("area")val area: Area,
    //@field:SerializedName("unidadnegocio")val unidadnegocio: String,
    @field:SerializedName("celular")val celular: String,
    @field:SerializedName("interno")val interno: String,
    //@field:SerializedName("incidentes")val incidentes: String,
    @field:SerializedName("esVip")val esVip: Boolean,
    @field:SerializedName("puesto")val puesto: String,
    @field:SerializedName("resolutor")val resolutor: Boolean,

//    "_id": 5489,
//    "email": "juanperez@gmail.com",
//"nombre": "Juan",
//"apellido": "Perez",
//"pais": "Argentina",
//"area": {
//    "area": "RRHH",
//    "ubicacion": {
//        "ubicacion": "Oficina Central"
//    }
//},
//"unidadnegocio": {
//    "unidadnegocio": "kiosko24hs"
//},
//"celuar": 1125256898,
//"interno": 5862,
//"incidentes": [
//{}
//],
//"esVip": true,
//"puesto": "Gerente",
//"esReesolutor": false
)