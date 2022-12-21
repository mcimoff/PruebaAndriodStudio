package com.example.myapplication

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Api.Model.IncidentesResponse
import com.example.myapplication.Api.Model.Model
import com.example.myapplication.adapters.IncidenteListAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Historial.newInstance] factory method to
 * create an instance of this fragment.
 */
class Historial : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var mainActivity : MainActivity

    private var incidentesAbiertos: String = "Abiertos"
    private var incidentesResueltos: String = "Resueltos"

    lateinit var vistaHistorial : View

    lateinit var listIncidentes: RecyclerView
    lateinit var incidenteListAdapter: IncidenteListAdapter
    lateinit var  linearLayoutManager: LinearLayoutManager;

    lateinit var radioButtonAbiertos: RadioButton
    lateinit var radioButtonResueltos: RadioButton

    lateinit var estadoIncidentesSpinner : Spinner

    lateinit var idUsuario : String
    lateinit var areaResolutora : String

    val api = Model.create("http://192.168.0.134:3000")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        mainActivity = (activity as MainActivity?)!!

        idUsuario = mainActivity.getUsuario()._id.toString()
        areaResolutora = mainActivity.getUsuario().area.area

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        vistaHistorial = inflater.inflate(R.layout.fragment_historial, container, false)

//        radioButtonAbiertos = vistaHistorial.findViewById(R.id.rb_abiertos)
//        radioButtonResueltos = vistaHistorial.findViewById(R.id.rb_resueltos)

        estadoIncidentesSpinner = vistaHistorial.findViewById(R.id.estados_incidentes_spinner)

        ArrayAdapter.createFromResource(
            mainActivity,
            R.array.estados_incidentes_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            estadoIncidentesSpinner.adapter = adapter
        }

//        idUsuario = "1044"
//        areaResolutora = "SoporteAdministracion"


        listIncidentes = vistaHistorial.findViewById((R.id.recycleBusqueda))
        listIncidentes.setHasFixedSize(true)


        return vistaHistorial
    }

    override fun onStart() {
        super.onStart()

//        idUsuario = "1044"
//        areaResolutora = "SoporteAdministracion"

        val esResolutor = mainActivity.getUsuario().esResolutor


        estadoIncidentesSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {

                val itemSeleccionado = parent.getItemAtPosition(position)

                when (itemSeleccionado) {
                    incidentesResueltos -> if (esResolutor) {

                        api.getIncidentesXAreaResueltos(areaResolutora)?.enqueue(object : Callback<List<IncidentesResponse>?> {
                            override fun onResponse(
                                call: Call<List<IncidentesResponse>?>,
                                response: Response<List<IncidentesResponse>?>,
                            ){
                                incidentesResponse(response)
                            }

                            override fun onFailure(call: Call<List<IncidentesResponse>?>, t: Throwable) {
                                call.toString()
                            }
                        }
                        )

                    } else {
                        api.getIncidentesResueltosXID(idUsuario)?.enqueue(object : Callback<List<IncidentesResponse>?> {
                            override fun onResponse(
                                call: Call<List<IncidentesResponse>?>,
                                response: Response<List<IncidentesResponse>?>,
                            ){
                                incidentesResponse(response)
                            }

                            override fun onFailure(call: Call<List<IncidentesResponse>?>, t: Throwable) {
                                call.toString()
                            }
                        }
                        )
                    }

                    incidentesAbiertos -> if (esResolutor) {
                        api.getIncidentesXAreaAbiertos(areaResolutora)?.enqueue(object : Callback<List<IncidentesResponse>?> {
                            override fun onResponse(
                                call: Call<List<IncidentesResponse>?>,
                                response: Response<List<IncidentesResponse>?>,
                            ){
                                incidentesResponse(response)
                            }

                            override fun onFailure(call: Call<List<IncidentesResponse>?>, t: Throwable) {
                                call.toString()
                            }
                        }
                        )
                    } else {
                        api.getIncidentesAbiertosXID(idUsuario)?.enqueue(object : Callback<List<IncidentesResponse>?> {
                            override fun onResponse(
                                call: Call<List<IncidentesResponse>?>,
                                response: Response<List<IncidentesResponse>?>,
                            ){
                                incidentesResponse(response)
                            }

                            override fun onFailure(call: Call<List<IncidentesResponse>?>, t: Throwable) {
                                call.toString()
                            }
                        }
                        )
                    }
                    else -> {}
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // handle nothing being selected
            }
        }
    }

    private fun incidentesResponse(response : Response<List<IncidentesResponse>?>) {
        if (response.code() == 200) {
            val lista: List<IncidentesResponse>? = (response.body() as List<IncidentesResponse>)!!

            val incidentes = (lista as List<IncidentesResponse>).toMutableList()
            Log.i(ContentValues.TAG, "Llego: $incidentes")

            val titulos = arrayOfNulls<IncidentesResponse>(size = incidentes?.size ?: 0)

            linearLayoutManager = LinearLayoutManager(context)

            listIncidentes.layoutManager = linearLayoutManager

            incidenteListAdapter = IncidenteListAdapter(incidentes)

            listIncidentes.adapter = incidenteListAdapter
        }
    }
}