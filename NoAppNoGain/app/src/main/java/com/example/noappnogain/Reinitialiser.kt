package com.example.noappnogain

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

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
        resetpassword.setOnClickListener(View.OnClickListener {
            val useremail = passwordEmail.text.toString().trim { it <= ' ' }
            if (useremail == "") {
                passwordEmail.setError("E-mail obligatoire...", null)
                return@OnClickListener
            } else {
                firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener { task ->
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