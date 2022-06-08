package com.example.noappnogain;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Accueil extends AppCompatActivity {

    private EditText mEmail;
    private EditText mPass;
    private CheckBox remember;

    private ProgressDialog mDialog;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_accueil);

        mAuth= FirebaseAuth.getInstance();
        mDialog=new ProgressDialog(this);

    }

}
