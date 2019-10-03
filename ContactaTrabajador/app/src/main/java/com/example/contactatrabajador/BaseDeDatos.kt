package com.example.contactatrabajador

interface BaseDeDatos {
    fun conectar()
    fun obtener()
    fun enviar()
    fun reemplazar()
    fun escuchar()
    fun ingresar(valor : String? =  null)
    fun salir()
    fun registrar()
    fun retornarUsuario() : Any?
}