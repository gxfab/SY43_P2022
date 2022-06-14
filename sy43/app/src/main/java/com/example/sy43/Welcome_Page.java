package com.example.sy43;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Welcome_Page extends AppCompatActivity {

    Button get_started;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        /*SharedPreferences sharedPreferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE );

        String firstTime = sharedPreferences.getString("First Time Install","");

        if(firstTime.equals("Yes")){
            Intent intent = new Intent(Welcome_Page.this, Define_categories.class);
            startActivity(intent);
        }
        else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("First Time Install", "Yes");
            editor.apply();
        }*/

        toDefineCategories();
    }

    private void toDefineCategories(){
        get_started = findViewById(R.id.getStarted_button);
        get_started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Welcome_Page.this, Define_categories.class));
            }
        });
    }
}