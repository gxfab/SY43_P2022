package com.example.econo_misons;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText username;
    Button add, view;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        add = findViewById(R.id.addbutton);
        view = findViewById(R.id.viewbutton);

        DB = new DBHelper(this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameTXT = username.getText().toString();

                Boolean checkinsertdata = DB.addUser(usernameTXT);
                if (checkinsertdata){
                    Log.d("DB", "Data inserted");
                }else  {
                    Log.d("DB", "Data insertion failed");
                }



            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = DB.getUsers();
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
                builder.show();
            }
        });

    }
}