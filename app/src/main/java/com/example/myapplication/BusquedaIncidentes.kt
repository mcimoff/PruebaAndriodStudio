package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Api.Model.IncidentesResponse
import com.example.myapplication.Api.Model.Model
import com.example.myapplication.adapters.IncidenteListAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BusquedaIncidentes.newInstance] factory method to
 * create an instance of this fragment.
 */
class BusquedaIncidentes : Fragment(), SearchView.OnQueryTextListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var vistaBusqueda : View
    lateinit var btnBusqueda : SearchView
    val apiBusqueda = Model.create("http://192.168.0.13:3000/incidentes/incidenteXID/")
    lateinit var recycleBusqueda : RecyclerView
    private val incidentelist = mutableListOf<IncidentesResponse>()
    var incidents : MutableList<IncidentesResponse> = mutableListOf()
    lateinit var  linearLayoutManager: LinearLayoutManager;
    lateinit var incidenteListAdapter: IncidenteListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vistaBusqueda = inflater.inflate(R.layout.busqueda_incidentes, container, false)

        btnBusqueda = vistaBusqueda.findViewById((R.id.simpleSearchView))
        recycleBusqueda = vistaBusqueda.findViewById((R.id.recycleBusqueda))

        if (::recycleBusqueda.isInitialized) {
            // Use the property
            recycleBusqueda.layoutManager = LinearLayoutManager(activity)
        }

        incidenteListAdapter = IncidenteListAdapter(incidentelist)

        recycleBusqueda.adapter = incidenteListAdapter


        return vistaBusqueda
    }

    override fun onStart() {
        super.onStart()

        btnBusqueda.setOnQueryTextListener(this)
    }

    private fun searchById(query: String){
        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<IncidentesResponse> = apiBusqueda.getIncidente( "$query")
            val incidenteBody: IncidentesResponse? = call.body()
            activity?.runOnUiThread{
                if (call.isSuccessful){
                    val lista : List<IncidentesResponse> = listOf(incidenteBody) as List<IncidentesResponse>
//                    val incidente: List<IncidentesResponse> = (incidenteBody?._id   as List<IncidentesResponse>)
//                    val incidenteBuscado : List<IncidentesResponse> = (incidente) as List<IncidentesResponse>
                    incidentelist.clear()
                    incidentelist.addAll(lista)
                    incidenteListAdapter.notifyDataSetChanged()
                }else{
                    showError()
                }


            }
        }
    }


    override fun onQueryTextSubmit(query: String?): Boolean {
        if(!query.isNullOrEmpty()){
            searchById(query.lowercase())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    private fun showError() {
        Toast.makeText(context,"Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

}