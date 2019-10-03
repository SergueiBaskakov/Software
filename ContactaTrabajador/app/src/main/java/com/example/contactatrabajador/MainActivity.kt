package com.example.contactatrabajador

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var BD : BaseDeDatos = FirestoreBD
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        salir.setOnClickListener {
            BD.salir()
            startActivity(Intent(this, AutentificacionNumero::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        if (BD.retornarUsuario() == null) {
            startActivity(Intent(this, AutentificacionNumero::class.java))
        }else {

        }
    }
}

