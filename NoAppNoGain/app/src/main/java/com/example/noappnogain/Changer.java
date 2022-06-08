package com.example.noappnogain;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Changer extends AppCompatActivity {

    private Button changePass;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_changer_mdp);

        mAuth= FirebaseAuth.getInstance();
        FirebaseUser user=mAuth.getCurrentUser();

        changePass=findViewById(R.id.btnChange);

        EditText oldPass=findViewById(R.id.old_pass);
        EditText NewPass=findViewById(R.id.new_pass);

        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldString=oldPass.getText().toString().trim();
                String newString=NewPass.getText().toString().trim();

                if(TextUtils.isEmpty(oldString)){
                    oldPass.setError("Champs requis..",null);
                    return;
                }
                if(TextUtils.isEmpty(newString)){
                    NewPass.setError("Champs requis..",null);
                    return;
                }

                if(newString.length()<6)
                {
                    NewPass.setError("Doit contenir au moins 6 caractères..",null);
                    return;
                }

                if(mAuth.getCurrentUser()!=null) {
                    AuthCredential credential= EmailAuthProvider.getCredential(user.getEmail(),oldString);
                    user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                user.updatePassword(newString).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful())
                                        {
                                            Toast.makeText(Changer.this,"Le mot de passe a été changé avec succès..",Toast.LENGTH_LONG).show();
                                            Intent intent=new Intent(Changer.this,Sidentifier.class);
                                            startActivity(intent);
                                        }
                                        else
                                        {
                                            Toast.makeText(Changer.this,"Une erreur s'est produite. Veuillez réessayer plus tard....",Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                            }
                        }
                    });
                }
            }
        });

        ImageView back_arrow=findViewById(R.id.back);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
