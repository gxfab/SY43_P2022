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

class Reinitialiser : AppCompatActivity() {
    private lateinit var passwordEmail: EditText
    private lateinit var resetpassword: Button
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reinitialiser_mdp)
        passwordEmail = findViewById<View>(R.id.pass_email) as EditText
        resetpassword = findViewById<View>(R.id.reset_pass) as Button
        firebaseAuth = FirebaseAuth.getInstance()
        resetpassword!!.setOnClickListener(View.OnClickListener {
            val useremail = passwordEmail!!.text.toString().trim { it <= ' ' }
            if (useremail == "") {
                passwordEmail!!.setError("E-mail obligatoire...", null)
                return@OnClickListener
            } else {
                firebaseAuth!!.sendPasswordResetEmail(useremail).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            this@Reinitialiser,
                            "E-mail envoyé avec succès..",
                            Toast.LENGTH_LONG
                        ).show()
                        finish()
                        startActivity(Intent(this@Reinitialiser, Sidentifier::class.java))
                    } else {
                        Toast.makeText(
                            this@Reinitialiser,
                            "Erreur lors de l'envoi de l'e-mail..",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })
    }
}