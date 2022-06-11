package com.example.noappnogain

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

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
        imageView.animation = top
        textView1.animation = bottom
        textView2.animation = bottom
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