package com.example.noappnogain;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Reinitialiser extends AppCompatActivity {
    private EditText passwordEmail;
    private Button resetpassword;
    private FirebaseAuth firebaseAuth;

    public Reinitialiser() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reinitialiser_mdp);

        passwordEmail = (EditText)findViewById(R.id.pass_email);
        resetpassword = (Button)findViewById(R.id.reset_pass);
        firebaseAuth = FirebaseAuth.getInstance();

        resetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail = passwordEmail.getText().toString().trim();

                if(useremail.equals(""))
                {
                    passwordEmail.setError("E-mail obligatoire...",null);
                    return;
                }
                else
                {
                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(Reinitialiser.this,"E-mail envoyé avec succès..",Toast.LENGTH_LONG).show();
                                finish();
                                startActivity(new Intent(Reinitialiser.this,Sidentifier.class));
                            }
                            else
                            {
                                Toast.makeText(Reinitialiser.this,"Erreur lors de l'envoi de l'e-mail..",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
