package com.example.contactatrabajador

import android.app.Activity

object FirestoreBD : BaseDeDatos {

    // objeto utilizado para el manejo del usuario
    var auth : Autentificacion? = null
    fun singleton(autentificacion : Autentificacion) : BaseDeDatos{
        this.auth  = autentificacion
        return this
    }

    override fun retornarUsuario() : Any?{
        if (this.auth!=null){
            return this.auth!!.retornarUsuario()
        }
        else{
            return null
        }

    }

    override fun ingresar(valor : String?, activity : Activity?) {
        this.auth!!.ingresar(valor,activity)
    }

    override fun registrar() {
        this.auth!!.registrar()
    }

    override fun salir() {
        this.auth!!.salir()
    }

    override fun obtener() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun enviar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun reemplazar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun escuchar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun conectar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}