package com.example.contactatrabajador

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_servicio.*

class ServicioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servicio)
        descripcionServicio.setText(intent.getStringExtra("descripcion"))
        estadoServicio.setText(intent.getStringExtra("estado"))
        costoServicio.setText(intent.getStringExtra("costo"))
        direccionServicio.setText(intent.getStringExtra("direccion"))
    }
}
