package com.example.contactatrabajador

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_autentificacion_numero.*

class AutentificacionNumero : AppCompatActivity() {
    var BD : BaseDeDatos = FirestoreBD.singleton(PhoneAuthFirebase.singleton(
        fun () : Unit {
            startActivity(Intent(this, MainActivity::class.java))
            Log.e("fcompletado","true")},
        fun () : Unit {
            editText.setText(null)
            editText.setHint("Codigo de confirmacion")
            button.setText("Ingresar")
            Log.e("fSmsEnviado","true")

        },
        fun () : Unit {
            editText.setText(null)
            editText.setHint("Numero Celular")
            button.setText("Enviar Codigo")
            Log.e("fFallo","true")
        },this))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_autentificacion_numero)
        button.setOnClickListener{
            val dato : String = editText.text.toString()
            BD.ingresar(dato)
        }
    }
}

