package com.example.contactatrabajador

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_servicio.*

class ServicioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servicio)
        var bd  = FirestoreBD
        descripcionServicio.setText(intent.getStringExtra("descripcion"))
        estadoServicio.setText(intent.getStringExtra("estado"))
        costoServicio.setText(intent.getStringExtra("costo"))
        direccionServicio.setText(intent.getStringExtra("direccion"))
        var id = intent.getStringExtra("id")


        if (intent.getStringExtra("estado") == "ACEPTADO" || intent.getStringExtra("estado") == "TERMINADO" || intent.getStringExtra("estado") == "CANCELADO"){
            botonRechazar.isClickable = false
        }
        if (intent.getStringExtra("estado") == "TERMINADO" || intent.getStringExtra("estado") == "CANCELADO"){
            botonConfirmar.isClickable = false
        }
        botonRechazar.setOnClickListener{
            bd.enviar(mutableMapOf(Pair("ESTADO","cancelado")),"Servicio_modelo/"+id)
            estadoServicio.setText("Cancelado")
            botonRechazar.isClickable = false
            botonConfirmar.isClickable = false
        }
        botonConfirmar.setOnClickListener{
            if(intent.getStringExtra("estado") == "cotizar"){
                bd.enviar(mutableMapOf(Pair("ESTADO","cotizado")),"Servicio_modelo/"+id)
                estadoServicio.setText("Cotizado")
            }
            else if(intent.getStringExtra("estado") == "aceptado"){
                bd.enviar(mutableMapOf(Pair("ESTADO","terminado")),"Servicio_modelo/"+id)
                estadoServicio.setText("Terminado")
                botonConfirmar.isClickable = false
            }

        }
    }
}
