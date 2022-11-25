package com.example.myapplication

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Api.Model.IncidentesResponse
import com.example.myapplication.Api.Model.Model
import com.example.myapplication.adapters.IncidenteListAdapter
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import android.widget.TextView
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
    lateinit var listIncidentes: RecyclerView
    lateinit var incidenteListAdapter: IncidenteListAdapter
    lateinit var  linearLayoutManager: LinearLayoutManager;
    val api = Model.create("http://192.168.0.13:3000")




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




        api.getIncidentes()?.enqueue(object : Callback<List<IncidentesResponse>?>{
               override fun onResponse(
                   call: Call<List<IncidentesResponse>?>,
                   response: Response<List<IncidentesResponse>?>
               ){
                   if (response.code() == 200){
                       val response: List<IncidentesResponse>? =(response.body() as List<IncidentesResponse>)!!


                       val incidentes = (response as List<IncidentesResponse>).toMutableList()
                       Log.i(TAG, "Llego: $incidentes")




                       val titulos = arrayOfNulls<IncidentesResponse>(size = incidentes?.size ?:0)




                      if(incidentes != null){

                          linearLayoutManager = LinearLayoutManager(context)

                          listIncidentes.layoutManager = linearLayoutManager

                          incidenteListAdapter = IncidenteListAdapter(incidentes)

                          listIncidentes.adapter = incidenteListAdapter


                      }

                   }

               }

               override fun onFailure(call: Call<List<IncidentesResponse>?>, t: Throwable) {
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


