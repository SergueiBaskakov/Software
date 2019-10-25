package com.example.contactatrabajador

import com.google.firebase.firestore.model.value.ReferenceValue
import com.google.rpc.Help
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class Trabajador {
    var datos : MutableMap<String,Any>? = null
    /*
    var calificacion : Double = 0.000
    var certificados : LinkedList<String> = LinkedList()
    var chatAdmin : String = "" //clase??
    var descuentos : LinkedList<String> = LinkedList()
    var documento : String = ""
    var foto : String = ""
    var horario : LinkedList<String> = LinkedList()
    var id : String = ""
    var imagenes : LinkedList<String> = LinkedList()
    var nombre : String = ""
    var numero : Number = 0
    var serviciosRealizados : LinkedList<String> = LinkedList() //crear un enum luego
    var trabajosOfrecidos : LinkedList<String> = LinkedList()*/

    fun obtener(db : BaseDeDatos, ubicacion : String, fCompletado : () -> Unit){
        db.obtener(ubicacion, fun(map : MutableMap<String,Any>?){
            this.datos=map
            fCompletado()})
    }

    fun enviar(db: BaseDeDatos, ubicacion : String){
        db.enviar(this.datos!!, ubicacion)
    }

    fun verDatos(llave: String) : Any?{
        if(this.datos != null){
            return this.datos!![llave]
        }
        else{
            return null
        }
    }

    fun agregarDatos(llave : String, dato : Any, arreglo : Boolean = false){
        if(this.datos != null){
            if(this.datos!![llave.toUpperCase()] is Array<*>){
                (this.datos!![llave.toUpperCase()] as Array<Any?>).plus(dato)
            }
            else{
                this.datos!![llave.toUpperCase()] = dato
            }
        }
        else{
            if(arreglo == true){
                this.datos = mutableMapOf(Pair(llave.toUpperCase(),arrayOf<Any?>(dato)))
            }
            else{
                this.datos = mutableMapOf(Pair(llave.toUpperCase(),dato))
            }
        }
    }


}

