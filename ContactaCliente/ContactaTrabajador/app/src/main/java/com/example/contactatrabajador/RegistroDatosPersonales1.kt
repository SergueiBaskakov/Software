package com.example.contactatrabajador

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_registro_datos_personales.*


class RegistroDatosPersonales1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var trabajador : Trabajador = TrabajadorPrueba.data
        var bd : BaseDeDatos = FirestoreBD
        setContentView(R.layout.activity_registro_datos_personales)
        if(trabajador != null){
            if(trabajador.verDatos("documento").toString()!="null"){
                dniText.setText(trabajador.verDatos("documento").toString())
            }
            if(trabajador.verDatos("nombre").toString()!="null"){
                nombreText.setText(trabajador.verDatos("nombre").toString())
            }
            //especialidadText.setText(trabajador.verDatos("trabajosofrecidos").toString())
        }
        siguienteBoton.setOnClickListener {
            //var trabajador: Trabajador = TrabajadorPrueba.data
            trabajador.agregarDatos("documento",dniText.text.toString())
            trabajador.agregarDatos("id",bd.retornarUsuario().toString())
            trabajador.agregarDatos("nombre",nombreText.text.toString())
            trabajador.agregarDatos("numero",bd.retornarUsuarioNumero()!!)
            //trabajador.agregarDatos("trabajosofrecidos",especialidadText.text.toString(),true)
            trabajador.enviar(bd,"Cliente_modelo/${trabajador.datos!!["ID"].toString()}")
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
