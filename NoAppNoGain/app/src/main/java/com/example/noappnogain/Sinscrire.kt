package com.example.noappnogain

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class Sinscrire : AppCompatActivity() {
    private lateinit var mEmail: EditText
    private lateinit var mPass: EditText
    private lateinit var mDialog: ProgressDialog
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sinscrire)
        mAuth = FirebaseAuth.getInstance()
        mDialog = ProgressDialog(this)
        registration()
    }

    private fun registration() {
        findViewById<EditText>(R.id.name_reg)
        mEmail = findViewById(R.id.email_reg)
        mPass = findViewById(R.id.password_reg)
        val btnReg = findViewById<Button>(R.id.btn_reg)
        val mSignin = findViewById<TextView>(R.id.signin_here)
        mSignin.setOnClickListener {
            val intent = Intent(this@Sinscrire, Sidentifier::class.java)
            startActivity(intent)
        }
        btnReg.setOnClickListener(View.OnClickListener {
            val email = mEmail.text.toString().trim { it <= ' ' }
            val pass = mPass.text.toString().trim { it <= ' ' }
            if (TextUtils.isEmpty(email)) {
                mEmail.setError("E-mail obligatoire..", null)
                return@OnClickListener
            }
            if (TextUtils.isEmpty(pass)) {
                mPass.setError("Mot de passe obligatoire..", null)
                return@OnClickListener
            }
            if (pass.length < 6) {
                mPass.setError("Doit contenir au moins 6 caractères..", null)
                return@OnClickListener
            }
            mDialog.setMessage("Traitement..")
            mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    mDialog.dismiss()
                    sendEmailVerification()
                    mEmail = findViewById(R.id.email_reg)
                    val email = mEmail.text.toString().trim { it <= ' ' }
                    val mUser = mAuth.currentUser
                    val uid = mUser!!.uid
                    val myRootRef =
                        FirebaseDatabase.getInstance().reference.child("UserInfo").child(uid)
                    val userNameRef = myRootRef.child("Email")
                    userNameRef.setValue(email)
                } else {
                    mDialog.dismiss()
                    Toast.makeText(
                        applicationContext,
                        "Échec de l'enregistrement..",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    private fun sendEmailVerification() {
        val firebaseUser = mAuth.currentUser
        firebaseUser?.sendEmailVerification()?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(
                    this@Sinscrire,
                    "Inscription réussie. E-mail de vérification envoyé avec succès..",
                    Toast.LENGTH_LONG
                ).show()
                mAuth.signOut()
                finish()
                startActivity(Intent(this@Sinscrire, Sidentifier::class.java))
            } else {
                Toast.makeText(
                    this@Sinscrire,
                    "Une erreur s'est produite lors de l'envoi de l'e-mail de vérification..",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}