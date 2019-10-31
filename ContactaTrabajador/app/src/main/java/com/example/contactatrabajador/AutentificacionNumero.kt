package com.example.contactatrabajador

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.android.synthetic.main.activity_autentificacion_numero.*

class AutentificacionNumero : AppCompatActivity() {
    var bd  = FirestoreBD

    override fun onCreate(savedInstanceState: Bundle?) {
        bd.auth = PhoneAuthFirebase.singleton(
            fun () {
                bd.obtener("Trabajador_modelo/${PhoneAuthFirebase.retornarUsuario()}",fun(map : MutableMap<String,Any>?){
                    //Toast.makeText(this, map.toString(), Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    //startActivity(Intent(this, RegistroDatosPersonales1::class.java))
                },fun(){
                    startActivity(Intent(this, RegistroDatosPersonales1::class.java))
                },fun(){
                    startActivity(Intent(this, RegistroDatosPersonales1::class.java))
                })},
            fun () {
                editText.setText(null)
                editText.setHint("Codigo de confirmacion")
                button.setText("Ingresar")
            },
            fun () {
                editText.setText(null)
                editText.setHint("Numero Celular")
                button.setText("Enviar Codigo")
            })
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_autentificacion_numero)
        button.setOnClickListener{
            val dato : String = editText.text.toString()
            bd.ingresar(dato, this)
        }
    }
}

