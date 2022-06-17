package com.example.cookutproject.data.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookutproject.R;
import com.example.cookutproject.models.Semestre;

import java.util.ArrayList;
import java.util.List;

public class SemestreAdapter extends RecyclerView.Adapter<SemestreAdapter.MyViewHolder> {

    private List<Semestre> semestreList = new ArrayList<>();

    @NonNull
    @Override
    public SemestreAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.semestre_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SemestreAdapter.MyViewHolder holder, int position) {
        Semestre current = semestreList.get(position);
        TextView tv = holder.itemView.findViewById(R.id.idSemestre_txt);
        tv.setText(String.valueOf(current.getId()));
        tv = holder.itemView.findViewById(R.id.montantPrevSemestre_txt);
        tv.setText(String.valueOf(current.getMontantPrev()));
        tv = holder.itemView.findViewById(R.id.nomSemestre_txt);
        tv.setText(String.valueOf(current.getNom()));
    }

    @Override
    public int getItemCount() {
        return semestreList.size();
    }

    public void setSemestreList(List<Semestre> semestreList) {
        this.semestreList = semestreList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        MyViewHolder(View itemView){super(itemView);}
    }
}
