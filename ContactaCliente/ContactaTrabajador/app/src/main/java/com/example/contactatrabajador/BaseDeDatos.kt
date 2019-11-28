package com.example.contactatrabajador

import android.app.Activity
import com.google.firebase.firestore.DocumentSnapshot

interface BaseDeDatos {
    fun conectar()
    fun obtener(ubicacion : String, fCompletado : (map: MutableMap<String, Any>?)->Unit, fInexistente : ()->Unit = fun(){},fFallo : ()->Unit = fun(){}) : Unit /////
    //fun obtener(ubicacion : String) : MutableMap<String, Any>?
    fun enviar(map : MutableMap<String, Any>, ubicacion : String)
    fun reemplazar()
    fun escuchar()
    fun ingresar(valor : String? =  null, activity : Activity? = null)
    fun salir()
    fun registrar()
    fun retornarUsuario() : Any?
    fun retornarUsuarioNumero() : Any?
}