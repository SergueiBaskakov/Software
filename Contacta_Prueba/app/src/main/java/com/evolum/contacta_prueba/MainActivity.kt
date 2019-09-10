package com.evolum.contacta_prueba

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.evolum.contacta_prueba.R.id.button
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText.setText("")



        button.setOnClickListener{
            var algo = editText.text.toString()
            var ref = FirebaseDatabase.getInstance().getReference("prueba")
            val id = ref.push().key.toString()
            //var dato = Dato(algo, algo)
            ref.child(id).setValue(algo)
            editText.setText("")
        }
    }

}
