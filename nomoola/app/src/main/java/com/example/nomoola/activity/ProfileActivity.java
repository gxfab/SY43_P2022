package com.example.nomoola.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.example.nomoola.R;
import com.example.nomoola.database.entity.Profile;
import com.example.nomoola.fragment.ProfileFragment;

public class ProfileActivity extends AppCompatActivity {

    Profile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (findViewById(R.id.idFrameLayout) != null) {
            if (savedInstanceState != null) {
                return;
            }

            getSupportFragmentManager().beginTransaction().add(R.id.idFrameLayout, new ProfileFragment()).commit();
        }

    }
}
