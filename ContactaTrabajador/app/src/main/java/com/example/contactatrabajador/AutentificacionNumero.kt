package com.example.contactatrabajador

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_autentificacion_numero.*

class AutentificacionNumero : AppCompatActivity() {
    var bd = FirestoreBD

    override fun onCreate(savedInstanceState: Bundle?) {
        bd.singleton(PhoneAuthFirebase.singleton(
            fun () {
                if(bd.obtener("Trabajador/${bd.retornarUsuario()}")==null){
                    startActivity(Intent(this, RegistroDatosPersonales_1::class.java))
                }
                else{
                    startActivity(Intent(this, MainActivity::class.java))
                }
            },
            fun () {
                editText.setText(null)
                editText.setHint("Codigo de confirmacion")
                button.setText("Ingresar")
            },
            fun () {
                editText.setText(null)
                editText.setHint("Numero Celular")
                button.setText("Enviar Codigo")
            }))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_autentificacion_numero)
        button.setOnClickListener{
            val dato : String = editText.text.toString()
            bd.ingresar(dato, this)
        }
    }
}

