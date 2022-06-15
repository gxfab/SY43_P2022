package com.example.sy43;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.sy43.database.AppDatabase;
import com.example.sy43.database.Projects;

import java.util.ArrayList;
import java.util.List;

public class Define_Projects extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private RecyclerView recyclerView;
    private List<String> l1,l2,l3;
    private ProjectsAdapter projectsAdapter;
    private EditText t1, t2;
    private Spinner s3;
    private CardView add_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_define_projects);

        recyclerView = findViewById(R.id.projects_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        add_btn= findViewById(R.id.add_project_btn);

        t1 = findViewById(R.id.project_name);
        t2 = findViewById(R.id.project_cost);
        s3 = findViewById(R.id.spinner_priority);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Priorities_Array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s3.setAdapter(adapter);
        s3.setOnItemSelectedListener(this);

        l1 = new ArrayList<>();
        l2 = new ArrayList<>();
        l3 = new ArrayList<>();
        projectsAdapter = new ProjectsAdapter(l1,l2,l3);
        recyclerView.setAdapter(projectsAdapter);

        AppDatabase db = AppDatabase.getInstance(this);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = t1.getText().toString();
                String cost = t2.getText().toString() + "$";
                String priority = s3.getSelectedItem().toString() + "/10";
                l1.add(name);
                l2.add(cost);
                l3.add(priority);
                projectsAdapter.notifyDataSetChanged();

                db.projectDao().insertAll(new Projects(l1.indexOf(name)+1,name,Double.parseDouble(t2.getText().toString()), Double.parseDouble(s3.getSelectedItem().toString())*10));
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}