package com.example.contactatrabajador.ui.notifications

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.contactatrabajador.FirestoreBD
import com.example.contactatrabajador.R
import com.example.contactatrabajador.TrabajadorPrueba
import com.google.firebase.firestore.FieldValue
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_notifications.*
import kotlinx.android.synthetic.main.fragment_notifications.view.*
import android.widget.RelativeLayout
import androidx.core.view.marginLeft
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.android.gms.tasks.Tasks.await
import com.google.firebase.firestore.model.value.IntegerValue
import java.util.ArrayList
import java.util.concurrent.TimeUnit
import java.util.zip.Inflater


class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProviders.of(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(com.example.contactatrabajador.R.layout.fragment_notifications, container, false)
        var bd  = FirestoreBD
        var cantMen = 0

        lateinit var chatAdmin : String
        if(TrabajadorPrueba.data.verDatos("chatAdmin")==null){

            var chatId = bd.db.collection("Chat_modelo").document().id
            chatAdmin = "Chat_modelo/" + chatId
            bd.enviar(mutableMapOf(Pair("ID",chatId)),chatAdmin)
            TrabajadorPrueba.data.agregarDatos("CHATADMIN",chatAdmin)
            TrabajadorPrueba.data.enviar(bd,"Cliente_modelo/"+TrabajadorPrueba.data.verDatos("id").toString())

        }
        else{

            chatAdmin = TrabajadorPrueba.data.verDatos("chatAdmin").toString()
        }



        bd.db.document(chatAdmin)
            .addSnapshotListener { value, e ->
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e)
                    return@addSnapshotListener
                }
                botonEnviar.setOnClickListener{
                    var mId = bd.db.collection("Mensaje_modelo").document().id
                    bd.enviar(mutableMapOf(Pair("ID",mId),
                        Pair("MENSAJE",mensajeEnviar.text.toString()),
                        Pair("CHAT",chatAdmin),
                        Pair("USUARIO","Cliente_modelo/"+TrabajadorPrueba.data.verDatos("id").toString())
                    ),"Mensaje_modelo/"+mId)
                    if(value!!.data!!["MENSAJES"]==null) {
                        bd.db.document(chatAdmin).update("MENSAJES", FieldValue.arrayUnion("Mensaje_modelo/"+mId.toString()))
                        /*bd.enviar(mutableMapOf(Pair("MENSAJES",
                            arrayOf("Mensaje_modelo/"+mId.toString())
                        )),chatAdmin)*/
                    }
                    else{
                        bd.db.document(chatAdmin).update("MENSAJES", FieldValue.arrayUnion("Mensaje_modelo/"+mId.toString()))
                        /*bd.enviar(mutableMapOf(Pair("MENSAJES",
                            arrayOf(mutableListOf<Any?>(value!!.data!!["MENSAJES"]).add("Mensaje_modelo/"+mId.toString()))
                        )),chatAdmin)*/
                    }
                    mensajeEnviar.setText("")

                }

                if(value!!.data!!["MENSAJES"]!=null){
                    //Toast.makeText(getActivity(), "0", Toast.LENGTH_SHORT).show()
                    //root.mensajesLayout.removeAllViewsInLayout()
                    var arreglo = (value!!.data!!["MENSAJES"].toString()).split(", ","[","]")
                    //Toast.makeText(getActivity(), arreglo[1].toString(), Toast.LENGTH_SHORT).show()
                    //Toast.makeText(getActivity(), arreglo[1].toString(), Toast.LENGTH_SHORT).show()
                    /*bd.obtener(arreglo[1].toString(),fun(map : MutableMap<String,Any>?){n(map,1,arreglo.count()-2,arreglo,TextView(activity))},fun(){
                        Toast.makeText(getActivity(), "panico", Toast.LENGTH_SHORT).show()
                    },fun(){
                        Toast.makeText(getActivity(), "panico x2", Toast.LENGTH_SHORT).show()
                    })*/
                    var arregloViews : ArrayList<View> = arrayListOf<View>()

                    for(i in cantMen+1..(arreglo.count()-2)){
                        var m = TextView(activity)
                        //Toast.makeText(getActivity(), i.toString(), Toast.LENGTH_SHORT).show()
                        TimeUnit.MILLISECONDS.sleep(50L)
                        bd.obtener(arreglo[i].toString(),fun(map : MutableMap<String,Any>?){

                            m.setText(map!!["MENSAJE"].toString())
                            m.width = 300
                            if(map!!["USUARIO"]=="Cliente_modelo/"+TrabajadorPrueba.data.verDatos("id")){
                                m.right = 5
                                m.setPadding(400,0,0,0)
                            }
                            else{
                                m.left = 5
                                //m.setPadding(0,0,300,0)
                            }
                            //Toast.makeText(getActivity(), m.text.toString(), Toast.LENGTH_SHORT).show()
                            //root.mensajesLayout.addView(m,i-1)
                            arregloViews.add(m)

                            if(i==arreglo.count()-2){
                                TimeUnit.MILLISECONDS.sleep(500L)
                                for(j in 0..(arregloViews.count()-1)){

                                    root.mensajesLayout.addView(arregloViews[j])
                                }
                                cantMen = arreglo.count()-2
                            }
                        },fun(){},fun(){})
                    }

                }
                else{
                    Toast.makeText(getActivity(), "1", Toast.LENGTH_SHORT).show()
                }
            }
        return root
    }

}



