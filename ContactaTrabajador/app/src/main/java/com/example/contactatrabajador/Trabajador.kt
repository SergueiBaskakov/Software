package com.example.contactatrabajador

import com.google.firebase.firestore.model.value.ReferenceValue
import com.google.rpc.Help

class Trabajador {
    var calificacion : Double = 0.000
    var certificados : LinkedList<Certificado> = LinkedList()
    var chatAdmin : String = "" //clase??
    var descuentos : LinkedList<Descuento> = LinkedList()
    var documento : Number = 0
    var foto : String = ""
    var horario : LinkedList<Horario> = LinkedList()
    var id : String = ""
    var imagenes : LinkedList<String> = LinkedList()
    var nombre : String = ""
    var numero : Number = 0
    var serviciosRealizados : LinkedList<Servicio> = LinkedList() //crear un enum luego
    var trabajosOfrecidos : LinkedList<String> = LinkedList()
}