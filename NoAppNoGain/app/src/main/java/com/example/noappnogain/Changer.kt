package com.example.noappnogain

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth

class Changer : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_changer_mdp)
        mAuth = FirebaseAuth.getInstance()
        val user = mAuth!!.currentUser
        val changePass = findViewById<Button>(R.id.btnChange)
        val oldPass = findViewById<EditText>(R.id.old_pass)
        val NewPass = findViewById<EditText>(R.id.new_pass)
        changePass.setOnClickListener(View.OnClickListener {
            val oldString = oldPass.text.toString().trim { it <= ' ' }
            val newString = NewPass.text.toString().trim { it <= ' ' }
            if (TextUtils.isEmpty(oldString)) {
                oldPass.setError("Champs requis..", null)
                return@OnClickListener
            }
            if (TextUtils.isEmpty(newString)) {
                NewPass.setError("Champs requis..", null)
                return@OnClickListener
            }
            if (newString.length < 6) {
                NewPass.setError("Doit contenir au moins 6 caractères..", null)
                return@OnClickListener
            }
            if (mAuth!!.currentUser != null) {
                val credential = EmailAuthProvider.getCredential(user!!.email!!, oldString)
                user.reauthenticate(credential).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        user.updatePassword(newString).addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(
                                    this@Changer,
                                    "Le mot de passe a été changé avec succès..",
                                    Toast.LENGTH_LONG
                                ).show()
                                val intent = Intent(
                                    this@Changer,
                                    Sidentifier::class.java
                                )
                                startActivity(intent)
                            } else {
                                Toast.makeText(
                                    this@Changer,
                                    "Une erreur s'est produite. Veuillez réessayer plus tard..",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }
                }
            }
        })

    }
}