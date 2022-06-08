package com.example.nomoola.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nomoola.R;

import java.util.zip.Inflater;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    private String Name;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inf = inflater.inflate(R.layout.fragment_profile, container, false);
        // Inflate the layout for this fragment
        Name = getNameFromDb();
        setProfileName(Name, inf);
        return inf;
    }
    private String getNameFromDb(){
        Name = "John Moola";
        return Name;
    }

    private void setProfileName(String Name, View inf){
        TextView tv = (TextView) inf.findViewById(R.id.profileName);
        tv.setText("Name");
        tv.setText(Name);
    }
}