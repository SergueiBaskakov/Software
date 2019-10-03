package com.example.contactatrabajador

interface Autentificacion {
    fun ingresar(valor : String? = null)
    fun salir()
    fun registrar()
    fun retornarUsuario() : Any?
}