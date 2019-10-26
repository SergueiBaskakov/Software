package com.example.contactatrabajador

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_registro_datos_personales.*

class RegistroDatosPersonales1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var bd : BaseDeDatos = FirestoreBD
        setContentView(R.layout.activity_registro_datos_personales)
        siguienteBoton.setOnClickListener {
            var trabajador: Trabajador = TrabajadorPrueba.data
            trabajador.agregarDatos("documento",dniText.text.toString())
            trabajador.agregarDatos("id",bd.retornarUsuario().toString())
            trabajador.agregarDatos("nombre",nombreText.text.toString())
            trabajador.agregarDatos("numero",bd.retornarUsuarioNumero()!!)
            trabajador.agregarDatos("trabajosofrecidos",especialidadText.text.toString(),true)
            trabajador.enviar(bd,"Trabajador_modelo/${trabajador.datos!!["ID"].toString()}")
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
