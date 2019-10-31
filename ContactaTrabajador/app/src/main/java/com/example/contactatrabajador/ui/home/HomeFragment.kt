package com.example.contactatrabajador.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.contactatrabajador.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import android.content.Intent
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /*homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)*/
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        /*val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(this, Observer {
            textView.text = it
        })*/
        var bd  = FirestoreBD

        root.salirPerfil.setOnClickListener {
            bd.salir()
            TrabajadorPrueba.data.datos=null
            startActivity(Intent(getActivity(), AutentificacionNumero::class.java))
        }
        root.editarPerfil.setOnClickListener{
            startActivity(Intent(getActivity(), RegistroDatosPersonales1::class.java))
        }
        var trabajador : Trabajador = TrabajadorPrueba.data
        if(trabajador.datos==null){
            trabajador.obtener(bd,"Trabajador_modelo/${PhoneAuthFirebase.retornarUsuario()}",fun(){
                root.numeroPerfil.setText(trabajador.verDatos("NUMERO").toString())
                root.nombrePerfil.setText(trabajador.verDatos("NOMBRE").toString())
                root.dniPerfil.setText(trabajador.verDatos("DOCUMENTO").toString())
                root.especialidadPerfil.setText(trabajador.verDatos("trabajosofrecidos").toString())
            })
        }
        else{
            root.numeroPerfil.setText(trabajador.verDatos("NUMERO").toString())
            root.nombrePerfil.setText(trabajador.verDatos("NOMBRE").toString())
            root.dniPerfil.setText(trabajador.verDatos("DOCUMENTO").toString())
            root.especialidadPerfil.setText(trabajador.verDatos("trabajosofrecidos").toString())
        }

        return root
    }
}