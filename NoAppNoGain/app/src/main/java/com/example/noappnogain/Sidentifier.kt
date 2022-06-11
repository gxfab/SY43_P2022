package com.example.noappnogain

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Sidentifier : AppCompatActivity() {
    private lateinit var mEmail: EditText
    private lateinit var mPass: EditText
    private lateinit var remember: CheckBox
    private lateinit var mDialog: ProgressDialog
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sidentifier)
        mAuth = FirebaseAuth.getInstance()
        mDialog = ProgressDialog(this)
        loginDetails()
    }

    private fun loginDetails() {

        mEmail = findViewById(R.id.email_login)
        mPass = findViewById(R.id.password_login)
        val btnLogin = findViewById<Button>(R.id.btn_login)
        val mforget_password = findViewById<TextView>(R.id.forgot_password)
        val mSignUpHere = findViewById<TextView>(R.id.signup_reg)
        remember = findViewById(R.id.checkBox2)
        val preferences = getSharedPreferences("checkbox", MODE_PRIVATE)
        val checkbox = preferences.getString("remember", "")
        if (checkbox == "true") {
            try {
                val intent = Intent(this@Sidentifier, MainActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this@Sidentifier, "Erreur!!!", Toast.LENGTH_LONG).show()
            }
        } else if (checkbox != "false") {
        }
        remember.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if (buttonView.isChecked) {
                val preferences = getSharedPreferences("checkbox", MODE_PRIVATE)
                val editor = preferences.edit()
                editor.putString("remember", "true")
                editor.apply()
                Toast.makeText(
                    this@Sidentifier,
                    "Laissez-moi en session ouverte cochée..",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (!buttonView.isChecked) {
                val preferences = getSharedPreferences("checkbox", MODE_PRIVATE)
                val editor = preferences.edit()
                editor.putString("remember", "false")
                editor.apply()
                Toast.makeText(
                    this@Sidentifier,
                    "Laissez-moi en session ouverte cochée..",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        mSignUpHere.setOnClickListener {
            val intent = Intent(this@Sidentifier, Sinscrire::class.java)
            startActivity(intent)
        }
        mforget_password.setOnClickListener {
            val intent = Intent(this@Sidentifier, Reinitialiser::class.java)
            startActivity(intent)
        }
        btnLogin.setOnClickListener(View.OnClickListener {
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
            mDialog.setMessage("Se connecter..")
            mDialog.show()
            mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    mDialog.dismiss()
                    checkEmailVerification()
                } else {
                    mDialog.dismiss()
                    Toast.makeText(
                        applicationContext,
                        "Échec de la connexion..",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    private fun checkEmailVerification() {
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        val emailflag = firebaseUser!!.isEmailVerified
        if (emailflag) {
            finish()
            Toast.makeText(applicationContext, "Connexion réussie..", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@Sidentifier, MainActivity::class.java))
        } else {
            Toast.makeText(this, "Veuillez vérifier votre e-mail..", Toast.LENGTH_LONG).show()
            mAuth.signOut()
        }
    }
}