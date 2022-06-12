package com.example.econo_misons;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.econo_misons.database.DBViewModel;
import com.example.econo_misons.database.models.Budget;
import com.example.econo_misons.database.models.User;
import com.example.econo_misons.database.ViewModelFactory;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText username;
    Button add, view;
    private DBViewModel dbViewModel;

    User mainUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        add = findViewById(R.id.addbutton);
        view = findViewById(R.id.viewbutton);

        configureViewModel();




        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameTXT = username.getText().toString();
                newUser(usernameTXT);
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllUsers();
            }

        });

    }

    private void configureViewModel(){
        this.dbViewModel = new ViewModelProvider(this, ViewModelFactory.getInstance(this)).get(DBViewModel.class);
    }

    private void getAllUsers() {
        this.dbViewModel.getAllUsers().observe(this, this::showUserList);

    }

    private void newUser(String username) {
        dbViewModel.newUser(new User(username));
    }

    private void getUser(int id){
        this.dbViewModel.getUser(id).observe(this, this::updateMainUser);
    }

    private void updateMainUser(List<User> user){
        Toast toast = Toast.makeText(getApplicationContext(), user.toString(), Toast.LENGTH_SHORT);
        toast.show();
        Log.d("DBM", "updateMainUser: " + user.get(0));
        this.mainUser = user.get(0);
    }
    private void showUserList(List<User> users){
        Toast toast = Toast.makeText(getApplicationContext(), users.toString(), Toast.LENGTH_SHORT);
        toast.show();
    }

}