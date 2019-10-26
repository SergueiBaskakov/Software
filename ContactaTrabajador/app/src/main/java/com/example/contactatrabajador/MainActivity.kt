package com.example.contactatrabajador

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var bd  = FirestoreBD
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        salir.setOnClickListener {
            bd.salir()
            startActivity(Intent(this, AutentificacionNumero::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        if (bd.retornarUsuario() == null) {
            startActivity(Intent(this, AutentificacionNumero::class.java))
        }
        else{
            var trabajador : Trabajador= TrabajadorPrueba.data
            trabajador.obtener(bd,"Trabajador_modelo/${PhoneAuthFirebase.retornarUsuario()}",fun(){
                numeroView.setText(trabajador.verDatos("NUMERO").toString())
                nombreView.setText(trabajador.verDatos("NOMBRE").toString())
                dniView.setText(trabajador.verDatos("DOCUMENTO").toString())
                especialidadView.setText(trabajador.verDatos("trabajosofrecidos").toString())
            })

        }
    }
}

