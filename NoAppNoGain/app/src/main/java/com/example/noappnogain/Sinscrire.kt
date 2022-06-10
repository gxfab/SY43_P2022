package com.example.noappnogain

import androidx.appcompat.app.AppCompatActivity
import android.view.animation.Animation
import android.os.Bundle
import android.view.WindowManager
import com.example.noappnogain.R
import android.content.Intent
import com.example.noappnogain.Sidentifier
import com.example.noappnogain.Bienvenue
import android.app.ProgressDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import android.text.TextUtils
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import android.content.SharedPreferences
import android.view.View
import android.widget.*
import com.example.noappnogain.MainActivity
import com.example.noappnogain.Sinscrire
import com.example.noappnogain.Reinitialiser

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
        val mName = findViewById<EditText>(R.id.name_reg)
        mEmail = findViewById(R.id.email_reg)
        mPass = findViewById(R.id.password_reg)
        val btnReg = findViewById<Button>(R.id.btn_reg)
        val mSignin = findViewById<TextView>(R.id.signin_here)
        mSignin.setOnClickListener {
            val intent = Intent(this@Sinscrire, Sidentifier::class.java)
            startActivity(intent)
        }
        btnReg.setOnClickListener(View.OnClickListener {
            val email = mEmail.getText().toString().trim { it <= ' ' }
            val pass = mPass.getText().toString().trim { it <= ' ' }
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
            mDialog!!.setMessage("Traitement..")
            mAuth!!.createUserWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    mDialog!!.dismiss()
                    sendEmailVerification()
                    mEmail = findViewById(R.id.email_reg)
                    val email = mEmail.getText().toString().trim { it <= ' ' }
                    val mUser = mAuth!!.currentUser
                    if (mAuth != null) {
                        val uid = mUser!!.uid
                        val myRootRef =
                            FirebaseDatabase.getInstance().reference.child("UserInfo").child(uid)
                        val userNameRef = myRootRef.child("Email")
                        userNameRef.setValue(email)
                    }
                } else {
                    mDialog!!.dismiss()
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
        val firebaseUser = mAuth!!.currentUser
        firebaseUser?.sendEmailVerification()?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(
                    this@Sinscrire,
                    "Inscription réussie. E-mail de vérification envoyé avec succès..",
                    Toast.LENGTH_LONG
                ).show()
                mAuth!!.signOut()
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