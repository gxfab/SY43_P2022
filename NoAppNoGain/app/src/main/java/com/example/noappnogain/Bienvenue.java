package com.example.noappnogain;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Bienvenue extends AppCompatActivity {
        private static final int SPLASH_TIME_OUT = 3200;
        ImageView imageView;
        TextView textView1,textView2;
        Animation top,bottom;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);

                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

                setContentView(R.layout.activity_bienvenue);

                imageView=findViewById(R.id.noappnogain_image);
                textView1=findViewById(R.id.noappnogain_text1);
                textView2=findViewById(R.id.noappnogain_text2);

                top=AnimationUtils.loadAnimation(this,R.anim.splash_top);
                bottom=AnimationUtils.loadAnimation(this,R.anim.splash_bottom);

                imageView.setAnimation(top);
                textView1.setAnimation(bottom);
                textView2.setAnimation(bottom);

                new Handler().postDelayed(new Runnable() {
                        public void run()
                        {
                                Intent intent=new Intent(Bienvenue.this,Sidentifier.class);
                                startActivity(intent);
                                finish();
                        }
                },SPLASH_TIME_OUT);
        }
}
