package com.example.contactatrabajador

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_trabajador.*


class TrabajadorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trabajador)
        var bd  = FirestoreBD
        descripcionServicio.setText(intent.getStringExtra("descripcion"))
        estadoServicio.setText(intent.getStringExtra("estado"))
        costoServicio.setText(intent.getStringExtra("costo"))
        direccionServicio.setText(intent.getStringExtra("direccion"))
        var id = intent.getStringExtra("id")
        var trabajador = intent.getStringExtra("id")



        if (intent.getStringExtra("estado") == "TERMINADO" || intent.getStringExtra("estado") == "CANCELADO"){
            botonConfirmar.isClickable = false
        }

        serviciosBoton.setOnClickListener{
            startActivity(Intent(this, ServiciosActivity::class.java).
                putExtra("trabajador",trabajador.toString()))
        }

        botonConfirmar.setOnClickListener{
            var id = bd.db.collection("Servicio_modelo").document().id
            var serId = "Servicio_modelo/" + id
            bd.enviar(mutableMapOf(Pair("ID",id)),serId)
            bd.enviar(mutableMapOf(Pair("DIRECCION",direccionId.text.toString())),serId)
            bd.enviar(mutableMapOf(Pair("DESCRIPCION",descripcionId.text.toString())),serId)
            bd.enviar(mutableMapOf(Pair("TRABAJADORID",intent.getStringExtra("id").toString())),serId)
            bd.enviar(mutableMapOf(Pair("CLIENTEID",bd.retornarUsuario().toString())),serId)
            bd.enviar(mutableMapOf(Pair("ESTADO","COTIZAR")),serId)
            bd.enviar(mutableMapOf(Pair("COSTO","")),serId)
            direccionId.setText("")
            descripcionId.setText("")
            Toast.makeText(this, "Servicio solicitado", Toast.LENGTH_SHORT).show()
        }
    }
}