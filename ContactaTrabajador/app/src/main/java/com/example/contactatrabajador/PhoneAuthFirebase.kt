package com.example.contactatrabajador

import android.app.Activity
import android.content.Intent
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class PhoneAuthFirebase (fCompletado : () -> Unit, fSmsEnviado : () -> Unit, fFallo : () -> Unit ) : Autentificacion {

    var auth : FirebaseAuth = FirebaseAuth.getInstance() //objeto de Firebase para almacenar los datos del usuario
    var verificationId : String = ""
    var fCompletado : () -> Unit = fCompletado
    var fSmsEnviado : () -> Unit = fSmsEnviado
    var fFallo : () -> Unit = fFallo
    lateinit var activity: Activity

    init{
        auth.useAppLanguage()
    }

    var callbacks = object: PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            auth.signInWithCredential(credential)
                .addOnCompleteListener {
                        task: Task<AuthResult> ->
                    if (task.isSuccessful) {
                        verificationId = ""
                        fCompletado()
                    }
                }
        }

        override fun onVerificationFailed(p0: FirebaseException?) {
            verificationId = ""
            fFallo()
        }

        override fun onCodeSent(verfication: String?, p1: PhoneAuthProvider.ForceResendingToken?) {
            super.onCodeSent(verfication, p1)
            verificationId = verfication.toString()
            fSmsEnviado()
        }

    }

    constructor(fCompletado : () -> Unit, fSmsEnviado : () -> Unit, fFallo : () -> Unit, activity : Activity) : this(fCompletado, fSmsEnviado, fFallo) {
        this.activity = activity
    }

    override fun ingresar( valor : String?) {
        println("verificationId.equals(\"\")")
        if (verificationId.equals("")){
            val num : String = "+51"+valor
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                num, // Phone number to verify
                60, // Timeout duration
                TimeUnit.SECONDS, // Unit of timeout
                activity, // Activity (for callback binding)
                this.callbacks) // OnVerificationStateChangedCallbacks
        }
        else{
            this.confirmarNumero(valor)
        }
    }

    override fun salir() {
        this.auth.signOut()
    }

    override fun registrar() {

    }

    fun confirmarNumero(codigo : String?){
        if (verificationId.equals("")){
            val credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(verificationId, codigo.toString())
            auth.signInWithCredential(credential)
                .addOnCompleteListener {
                        task: Task<AuthResult> ->
                    if (task.isSuccessful) {
                        verificationId = ""
                        fCompletado()
                    }
                }
        }
    }
}