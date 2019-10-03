package com.example.contactatrabajador

import android.app.Activity
import android.content.Intent
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

object PhoneAuthFirebase  : Autentificacion {

    private var auth : FirebaseAuth = FirebaseAuth.getInstance() //objeto de Firebase para almacenar los datos del usuario
    var verificationId : String = ""
    var fCompletado : () -> Unit = {}
    var fSmsEnviado : () -> Unit = {}
    var fFallo : () -> Unit = {}
    lateinit var activity: Activity
    lateinit var callbacks : PhoneAuthProvider.OnVerificationStateChangedCallbacks

    fun singleton(fCompletado : () -> Unit, fSmsEnviado : () -> Unit, fFallo : () -> Unit, activity : Activity) : Autentificacion {
        this.activity = activity
        this.fCompletado = fCompletado
        this.fSmsEnviado = fSmsEnviado
        this.fFallo = fFallo
        auth.useAppLanguage()

        callbacks = object: PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
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
        return this
    }

    override fun retornarUsuario() : Any?{
        return this.auth.currentUser
    }

    override fun ingresar( valor : String?) {
        if (this.verificationId.equals("")){
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
        val credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(this.verificationId, codigo.toString())
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