package com.example.myapplication.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.Api.Model.IncidentesResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.random.Random

class IncidenteViewModel : ViewModel() {

   val incidenteModel = MutableLiveData<IncidentesResponse>()

    fun randomIncidente(){

    }

}