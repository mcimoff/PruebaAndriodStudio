package com.example.myapplication.Interface

import com.example.myapplication.Api.Model.IncidentesResponse
import retrofit2.Call
import retrofit2.http.GET

interface JsonApi {

    @GET("incidentes")
    fun getIncidentes(): Call<IncidentesResponse>



}