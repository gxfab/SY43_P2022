package net.yolopix.moneyz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.slider.Slider

class PrevisionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prevision)
        var slider = findViewById<com.google.android.material.slider.Slider>(R.id.salary_slider)
        var text_salary = findViewById<TextView>(R.id.salary_text);

        slider.addOnChangeListener { slider, value, fromUser ->
            text_salary.text = String.format("%.2f", value.toDouble())
        }
    }
}