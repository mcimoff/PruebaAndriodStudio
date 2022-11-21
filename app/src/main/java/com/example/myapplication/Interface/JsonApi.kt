package com.example.myapplication.Interface

import com.example.myapplication.Api.Model.IncidentesResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface JsonApi {

    @GET("/incidentes")
    fun getIncidentes(): Call<List<IncidentesResponse>>

    @GET
    suspend fun getIncidente( @Url url: String): Response<IncidentesResponse>

}