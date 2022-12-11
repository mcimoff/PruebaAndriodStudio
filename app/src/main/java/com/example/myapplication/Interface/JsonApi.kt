package com.example.myapplication.Interface

import com.example.myapplication.Api.Model.IncidentesResponse
import com.example.myapplication.Api.Model.UsuarioResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url


interface JsonApi {

    @GET("/incidentes")
    fun getIncidentes(): Call<List<IncidentesResponse>>

    //@GET("/incidentes/incidenteXID/{_id}")
    //fun getIncidente(@SafeParcelable.Para() ): Call<IncidentesResponse>

    @GET
    suspend fun getIncidente(@Url url: String): Response<IncidentesResponse>
//
//    @GET("/incidentes/incidenteXID/{id}")
//    suspend fun getIncidente( @Path ("_id")_id: Int): Call<IncidenteResponse>

    @GET("/usuarios/getusuario/{email}")
    fun getUsuarioXEmail( @Path ("email")email: String): Call<UsuarioResponse?>

}