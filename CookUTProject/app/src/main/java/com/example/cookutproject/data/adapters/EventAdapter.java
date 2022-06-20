package com.example.cookutproject.data.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookutproject.R;
import com.example.cookutproject.models.Evenement;
import com.example.cookutproject.models.Semestre;

import java.util.ArrayList;
import java.util.List;

/**
 * Pour le fonctionnement de cette classe, se référer à SemestreAdapter dont le fonctionnement est identique
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {
    private List<Evenement> eventList = new ArrayList<>();

    @NonNull
    @Override
    public EventAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EventAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.semestre_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Evenement current = eventList.get(position);
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public void setEvenementList(List<Evenement> eventList) {
        this.eventList = eventList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        MyViewHolder(View itemView){super(itemView);}
    }
}
