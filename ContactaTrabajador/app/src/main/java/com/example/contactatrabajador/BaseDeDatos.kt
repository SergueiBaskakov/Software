package com.example.contactatrabajador

import android.app.Activity

interface BaseDeDatos {
    fun conectar()
    fun obtener(ubicacion : String) : Any?
    fun enviar()
    fun reemplazar()
    fun escuchar()
    fun ingresar(valor : String? =  null, activity : Activity? = null)
    fun salir()
    fun registrar()
    fun retornarUsuario() : Any?
}