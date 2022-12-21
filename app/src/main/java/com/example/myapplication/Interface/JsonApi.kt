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

    @GET
    suspend fun getIncidente(@Url url: String): Response<IncidentesResponse>

    @GET("/incidentes/incidentesAbiertos")
    fun getIncidentesAbiertos(): Call<List<IncidentesResponse>>

    @GET("/incidentes/incidentesResueltos")
    fun getIncidentesResueltos(): Call<List<IncidentesResponse>>

    @GET("/incidentes/incidentesAbiertosXID/{id}")
    fun getIncidentesAbiertosXID( @Path ("id")id: String): Call<List<IncidentesResponse>>

    @GET("/incidentes/incidentesResueltosXID/{id}")
    fun getIncidentesResueltosXID( @Path ("id")id: String): Call<List<IncidentesResponse>>

    @GET("/incidentes/incidenteXAreaResolutora/{area}")
    fun getIncidentesXArea( @Path ("area")area: String): Call<List<IncidentesResponse>>

    @GET("/incidentes/incidenteXAreaResolutoraAbiertos/{area}")
    fun getIncidentesXAreaAbiertos( @Path ("area")area: String): Call<List<IncidentesResponse>>

    @GET("/incidentes/incidenteXAreaResolutoraResueltos/{area}")
    fun getIncidentesXAreaResueltos( @Path ("area")area: String): Call<List<IncidentesResponse>>

    @GET("/usuarios/getusuario/{email}")
    fun getUsuarioXEmail( @Path ("email")email: String): Call<UsuarioResponse?>

}