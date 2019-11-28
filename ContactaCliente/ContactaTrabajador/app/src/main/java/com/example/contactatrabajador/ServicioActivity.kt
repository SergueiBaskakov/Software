package com.example.contactatrabajador

import android.content.Intent
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
        var estado = intent.getStringExtra("estado")
        var chat = intent.getStringExtra("chat")
        Toast.makeText(this, estado.toString(), Toast.LENGTH_SHORT).show()


        if (estado == "ACEPTADO" || estado == "TERMINADO"){
            botonRechazar.setText("Chat")
        }
        if (estado == "TERMINADO" || estado == "CANCELADO"){
            botonConfirmar.isClickable = false
        }
        costoServicio.isEnabled = false
        botonRechazar.setOnClickListener{
            if(estado=="COTIZAR" || estado=="CANCELADO"){
                bd.enviar(mutableMapOf(Pair("ESTADO","CANCELADO")),"Servicio_modelo/"+id)
                estadoServicio.setText("Cancelado")
            }
            else{
                startActivity(
                    Intent(this, ChatActivity::class.java).
                        putExtra("chat",chat).
                        putExtra("id",id))
                //estados : Solicitud, Cotizacion, Aceptado, Finalizado
            }


        }
        botonConfirmar.setOnClickListener{
            if(estado == "COTIZADO"){
                bd.enviar(mutableMapOf(Pair("ESTADO","ACEPTADO")),"Servicio_modelo/"+id)
                estadoServicio.setText("Aceptado")
                costoServicio.isEnabled = false
            }
            else if(estado == "ACEPTADO"){
                bd.enviar(mutableMapOf(Pair("ESTADO","TERMINADO")),"Servicio_modelo/"+id)
                estadoServicio.setText("Terminado")
                botonConfirmar.isClickable = false
            }

        }
    }
}
