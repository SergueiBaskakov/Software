package com.example.contactatrabajador

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Tasks.await
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.model.Document


object FirestoreBD : BaseDeDatos {

    // objeto utilizado para el manejo del usuario
    var db : FirebaseFirestore  = FirebaseFirestore.getInstance()
    var auth : Autentificacion? = PhoneAuthFirebase

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

    override fun retornarUsuarioNumero() : Any?{
        if (this.auth!=null){
            return this.auth!!.retornarUsuarioNumero()
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



    /*override fun obtener(ubicacion : String) : MutableMap<String, Any>? { //jalar documentos una sola vez //modificar lueguito
        var map: MutableMap<String, Any>? = null
        val doc = db.document(ubicacion)
        await(doc.get().addOnCompleteListener(OnCompleteListener<DocumentSnapshot> { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document != null) {
                    map = document.data
                } else {
                    map = null
                }
            } else {
                map = null
            }
        }))
        return map
    }*/

    override fun obtener(ubicacion : String, fCompletado : (map: MutableMap<String, Any>?)->Unit, fInexistente : ()->Unit,fFallo : ()->Unit) : Unit { //jalar documentos una sola vez //modificar lueguito
        var map: MutableMap<String, Any>? = null
        val doc = db.document(ubicacion)
        doc.get().addOnCompleteListener(OnCompleteListener<DocumentSnapshot> { task ->
            if (task.isSuccessful) {
                map = null
                val document = task.result
                if (document != null) {
                    map = document.data
                    if(map!=null){
                        fCompletado(map)
                    }
                    else{
                        map = null
                        fInexistente()
                    }
                } else {
                    map = null
                    fInexistente()
                }
            } else {
                map = null
                fFallo()
            }
        })
    }

    override fun enviar(map : MutableMap<String, Any>, ubicacion : String) {
        val doc = db.document(ubicacion)
        doc.set(map, SetOptions.merge())
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