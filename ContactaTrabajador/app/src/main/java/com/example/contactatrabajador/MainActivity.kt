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
        Log.i("inicio()", "0")

        if (bd.retornarUsuario() == null) {
            //Toast.makeText(this, bd.auth.toString(), Toast.LENGTH_SHORT).show()
            //Thread.sleep(5_000)
            startActivity(Intent(this, AutentificacionNumero::class.java))
        }
        else{
            var trabajador : Trabajador= TrabajadorPrueba.data
            Toast.makeText(this, PhoneAuthFirebase.retornarUsuario().toString(), Toast.LENGTH_SHORT).show()
            trabajador.obtener(bd,"Trabajador_modelo/${PhoneAuthFirebase.retornarUsuario()}",fun(){
                numeroView.setText(trabajador.verDatos("NUMERO").toString())
                nombreView.setText(trabajador.verDatos("NOMBRE").toString())
                dniView.setText(trabajador.verDatos("DOCUMENTO").toString())
            })

        }

        //especialidadView.setText(trabajador.datos!!["TRABAJOSOFRECIDOS"]!!.toString())
    }
}

