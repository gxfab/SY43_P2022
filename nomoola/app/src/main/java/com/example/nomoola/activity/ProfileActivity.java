package com.example.nomoola.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.example.nomoola.R;
import com.example.nomoola.fragment.ProfileFragment;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // below line is used to check if
        // frame layout is empty or not.
        if (findViewById(R.id.idFrameLayout) != null) {
            if (savedInstanceState != null) {
                return;
            }
            // below line is to inflate our fragment.
            getSupportFragmentManager().beginTransaction().add(R.id.idFrameLayout, new ProfileFragment()).commit();
        }
    }
}
