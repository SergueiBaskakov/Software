package com.example.contactatrabajador.ui.dashboard

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.contactatrabajador.FirestoreBD

import com.example.contactatrabajador.R
import kotlinx.android.synthetic.main.fragment_dashboard.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ServicioFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ServicioFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ServicioFragment : Fragment() {
    //private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /*dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)*/
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        /*val textView: TextView = root.findViewById(R.id.text_dashboard)
        dashboardViewModel.text.observe(this, Observer {
            textView.text = it
        })*/
        ///mejorar despues


        //root.recyclerViewServicios.adapter = Adapter(activity)
        return root
    }
}
