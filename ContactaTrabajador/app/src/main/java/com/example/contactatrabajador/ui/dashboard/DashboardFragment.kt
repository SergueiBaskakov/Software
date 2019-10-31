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
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.contactatrabajador.FirestoreBD
import com.example.contactatrabajador.R
import com.example.contactatrabajador.RegistroDatosPersonales1
import com.example.contactatrabajador.ServicioActivity
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
        db.db.collection("Servicio_modelo")
            .get()
            .addOnSuccessListener { documents ->


                // add TextView to LinearLayout

                for (document in documents) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    val servicio = Button(activity)
                    servicio.textSize = 15f
                    servicio.text = document.data["ESTADO"].toString()
                    servicio.setOnClickListener{
                        startActivity(Intent(getActivity(), ServicioActivity::class.java).
                            putExtra("costo",document.data["COSTO"].toString()).
                            putExtra("descripcion",document.data["DESCRIPCION"].toString()).
                            putExtra("direccion",document.data["DIRECCION"].toString()).
                            putExtra("estado",document.data["ESTADO"].toString()))
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