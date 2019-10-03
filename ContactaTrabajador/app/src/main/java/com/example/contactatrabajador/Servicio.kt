package com.example.contactatrabajador

import java.util.*

class Servicio {
    var calificacion : Number = 0
    var chat : String = "" //clase??
    var cliente : String = ""  //clase??
    var comentario : LinkedList<Mensaje> = LinkedList()
    var costo : Number = 0
    var descripcion : String = ""
    var descuento : LinkedList<Descuento> = LinkedList()
    var direccion : String = ""
    var estado : String = "" //crear enum luego
    var fechaInicio : Date = Date(0)
    var fechaFin : Date = Date(0)
    var id : String = ""
    lateinit var trabajador : Trabajador
}