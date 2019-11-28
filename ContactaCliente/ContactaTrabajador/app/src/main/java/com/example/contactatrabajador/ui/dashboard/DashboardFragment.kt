package com.example.contactatrabajador.ui.dashboard

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.contactatrabajador.*
import com.google.firebase.firestore.model.Document
import kotlinx.android.synthetic.main.fragment_dashboard.view.*
import java.util.ArrayList
import java.io.Serializable


class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /*dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)*/
        val root = inflater.inflate(com.example.contactatrabajador.R.layout.fragment_dashboard, container, false)
        /*val textView: TextView = root.findViewById(R.id.text_dashboard)
        dashboardViewModel.text.observe(this, Observer {
            textView.text = it
        })*/
        ///mejorar despues
        var db = FirestoreBD
        //Toast.makeText(getActivity(), "/Trabajador_modelo/"+ TrabajadorPrueba.data.verDatos("id").toString(), Toast.LENGTH_SHORT).show()
        db.db.collection("Trabajador_modelo")
            .get()
            .addOnSuccessListener { documents ->


                // add TextView to LinearLayout

                for (document in documents) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    val servicio = Button(activity)
                    servicio.textSize = 15f
                    servicio.text = document.data["NOMBRE"].toString()
                    servicio.setOnClickListener{
                        startActivity(Intent(getActivity(), TrabajadorActivity::class.java).
                            putExtra("descripcion",document.data["NOMBRE"].toString()).
                            putExtra("direccion",document.data["DOCUMENTO"].toString()).
                            putExtra("id",document.id).
                            putExtra("costo",document.data["TRABAJOSOFRECIDOS"].toString()).
                            putExtra("estado",document.data["NUMERO"].toString()))
                    }
                    root.linearLayoutServicios.addView(servicio)
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }

        //root.recyclerViewServicios.adapter = Adapter(activity)
        return root
    }
}