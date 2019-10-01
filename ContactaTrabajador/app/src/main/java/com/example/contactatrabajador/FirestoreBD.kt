package com.example.contactatrabajador

class FirestoreBD (autentificacion : Autentificacion) : BaseDeDatos {

    // objeto utilizado para el manejo del usuario
    var auth : Autentificacion = autentificacion

    override fun ingresar(valor : String?) {
        this.auth.ingresar(valor)
    }

    override fun registrar() {
        this.auth.registrar()
    }

    override fun salir() {
        this.auth.salir()
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