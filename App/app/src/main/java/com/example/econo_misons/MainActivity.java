package com.example.econo_misons;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.econo_misons.database.DBViewModel;
import com.example.econo_misons.database.MoneyDB;
import com.example.econo_misons.database.User;
import com.example.econo_misons.database.ViewModelFactory;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText username;
    Button add, view;
    private DBViewModel dbViewModel;


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

               /* Boolean checkinsertdata = DB.addUser(usernameTXT);
                if (checkinsertdata){
                    Log.d("DB", "Data inserted");
                }else  {
                    Log.d("DB", "Data insertion failed");
                }*/
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllUsers();
                /*Cursor res = DB.getUsers();
                if (res.getCount()==0){
                    Log.d("DB", "No data to display");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while ((res.moveToNext())){
                    buffer.append("id : " + res.getString(0)+"\n");
                    buffer.append("username : " + res.getString(1)+"\n\n");

                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Users List");
                builder.setMessage(buffer.toString());
                builder.show();*/
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

    private void showUserList(List<User> users){
        Toast toast = Toast.makeText(getApplicationContext(), users.toString(), Toast.LENGTH_SHORT);
        toast.show();
    }

}