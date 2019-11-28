package com.example.contactatrabajador

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import kotlinx.android.synthetic.main.activity_servicios.*
import kotlinx.android.synthetic.main.fragment_dashboard.view.*

class ServiciosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servicios)
        var db = FirestoreBD
        //Toast.makeText(getActivity(), "/Trabajador_modelo/"+ TrabajadorPrueba.data.verDatos("id").toString(), Toast.LENGTH_SHORT).show()
        db.db.collection("Servicio_modelo").
            whereEqualTo("TRABAJADORID",intent.getStringExtra("trabajador").toString()).
            whereEqualTo("CLIENTEID",db.retornarUsuario().toString())
            .get()
            .addOnSuccessListener { documents ->


                // add TextView to LinearLayout

                for (document in documents) {
                    Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
                    val servicio = Button(this)
                    servicio.textSize = 15f
                    servicio.text = document.data["DIRECCION"].toString()+" - "+document.data["ESTADO"].toString()
                    servicio.setOnClickListener{
                        startActivity(
                            Intent(this, ServicioActivity::class.java).
                                putExtra("costo",document.data["COSTO"].toString()).
                                putExtra("descripcion",document.data["DESCRIPCION"].toString()).
                                putExtra("direccion",document.data["DIRECCION"].toString()).
                                putExtra("id",document.id).
                                putExtra("estado",document.data["ESTADO"].toString()).
                                putExtra("chat",document.data["CHAT"].toString()))
                        //estados : Solicitud, Cotizacion, Aceptado, Finalizado
                    }
                    this.linearLayoutServicios.addView(servicio)
                }
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents: ", exception)
            }

        //root.recyclerViewServicios.adapter = Adapter(activity)
    }
}
