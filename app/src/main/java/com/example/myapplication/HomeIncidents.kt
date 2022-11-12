package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.util.Log.INFO
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button

import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Api.Model.IncidentesResponse
import com.example.myapplication.Api.Model.Model
import com.example.myapplication.Entities.Incidente
import com.example.myapplication.adapters.IncidenteListAdapter
import com.google.android.material.snackbar.Snackbar
import okhttp3.internal.platform.Platform.INFO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.util.logging.Level.INFO
import android.widget.TextView
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment3.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeIncidents : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var vistaHomeIncidentes : View
    lateinit var btnLogout: Button

    lateinit var btnHomeGoToIncidente1: Button
    lateinit var btnHomeGoToIncidente2: Button
    lateinit var receptorTV: TextView
    lateinit var providerTV: TextView

    lateinit var btnAlta: com.google.android.material.floatingactionbutton.FloatingActionButton
     var incidents : MutableList<IncidentesResponse> = mutableListOf()
    lateinit var listIncidentes: RecyclerView
    lateinit var incidenteListAdapter: IncidenteListAdapter
    lateinit var  linearLayoutManager: LinearLayoutManager;
    val baseURL = "http://127.0.0.1:3000/"
    val api = Model.create(baseURL)




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


        /*val bundle: Bundle? = Intent.get

        setup()*/

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        vistaHomeIncidentes = inflater.inflate(R.layout.home_incidents, container, false)

        btnLogout = vistaHomeIncidentes.findViewById((R.id.buttonLogOut))
        btnAlta = vistaHomeIncidentes.findViewById((R.id.btnGoToAlta))
        listIncidentes = vistaHomeIncidentes.findViewById((R.id.recicleViewIncidentes))
        listIncidentes.setHasFixedSize(true)


        return vistaHomeIncidentes;
    }

    override fun onStart() {
        super.onStart()


        for(i in 1..5){
            incidents.add(IncidentesResponse(1,"Error compu","Carlos"))
            incidents.add(IncidentesResponse(1,"Error Impresora","Luis"))
        }
        /*btnLogout.setOnClickListener {

            //indexID = "idx" dentro del parentesis
            var action3 = HomeIncidentsDirections.actionHomeIncidentsToActivityLogin()

            vistaHomeIncidentes.findNavController().navigate(action3)

        }*/

        btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            //onBackPressed()
            //indexID = "idx" dentro del parentesis
            var action3 = HomeIncidentsDirections.actionHomeIncidentsToActivityLogin()

            vistaHomeIncidentes.findNavController().navigate(action3)

        }

        btnAlta.setOnClickListener{
            var action6 = HomeIncidentsDirections.actionHomeIncidentsToAltaIncidents(altaID = "alta")

            vistaHomeIncidentes.findNavController().navigate(action6)
        }




        api.getIncidentes()?.enqueue(object : Callback<IncidentesResponse?>{
               override fun onResponse(
                   call: Call<IncidentesResponse?>,
                   response: Response<IncidentesResponse?>
               ){
                   if (response.isSuccessful){
                       val response: IncidentesResponse? =(response.body())!!
                       val incidentes: MutableList<IncidentesResponse>? = (response as List<IncidentesResponse>).toMutableList()

                       try {
                           incidentes
                       } catch (e : Exception){
                           e.printStackTrace()
                       }

                       val titulos = arrayOfNulls<IncidentesResponse>(size = incidentes?.size ?:0)





                      if(incidentes != null){


                          linearLayoutManager = LinearLayoutManager(context)

                          listIncidentes.layoutManager = linearLayoutManager

                          incidenteListAdapter = IncidenteListAdapter(incidentes) { x ->
                              onItemClick(x)

                          }

                          listIncidentes.adapter = incidenteListAdapter

                      }

                   }

               }

               override fun onFailure(call: Call<IncidentesResponse?>, t: Throwable) {
                  // TODO("Not yet implemented")
                   call.toString()
               }



           }
           )



    }



    fun onItemClick (position: Int): Boolean{
        Snackbar.make(vistaHomeIncidentes,position.toString(),Snackbar.LENGTH_SHORT).show()
        return true
    }

}


