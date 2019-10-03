package com.example.contactatrabajador

import android.app.Activity
import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.model.Document

object FirestoreBD : BaseDeDatos {

    // objeto utilizado para el manejo del usuario
    private var db : FirebaseFirestore  = FirebaseFirestore.getInstance()
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

    override fun obtener(ubicacion : String) : Any? { //jalar documentos una sola vez
        var doc : Any? = null
        db.document(ubicacion).get()
            .addOnSuccessListener { document ->
                doc = document
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
        return doc
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