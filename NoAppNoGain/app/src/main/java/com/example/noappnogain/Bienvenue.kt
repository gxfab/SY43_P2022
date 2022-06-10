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
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.*
import com.example.noappnogain.MainActivity
import com.example.noappnogain.Sinscrire
import com.example.noappnogain.Reinitialiser

class Bienvenue : AppCompatActivity() {
    lateinit var imageView: ImageView
    lateinit var textView1: TextView
    lateinit var textView2: TextView
    lateinit var top: Animation
    lateinit var bottom: Animation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_bienvenue)
        imageView = findViewById(R.id.noappnogain_image)
        textView1 = findViewById(R.id.noappnogain_text1)
        textView2 = findViewById(R.id.noappnogain_text2)
        top = AnimationUtils.loadAnimation(this, R.anim.splash_top)
        bottom = AnimationUtils.loadAnimation(this, R.anim.splash_bottom)
        imageView.setAnimation(top)
        textView1.setAnimation(bottom)
        textView2.setAnimation(bottom)
        Handler().postDelayed({
            val intent = Intent(this@Bienvenue, Sidentifier::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_TIME_OUT.toLong())
    }

    companion object {
        private const val SPLASH_TIME_OUT = 3200
    }
}