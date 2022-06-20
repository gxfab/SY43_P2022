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
    /**
     *créer un objet de la class myviewholder
     */
    public SemestreAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.semestre_row,parent,false));
    }

    @Override
    /**
     * View affichant les valeurs contenues dans la BDD
     */
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
    /**
     * Récupère la liste des semestres
     */
    public int getItemCount() {
        return semestreList.size();
    }

    /**
     *Setter de la liste des semestres
     * @param semestreList
     */
    public void setSemestreList(List<Semestre> semestreList) {
        this.semestreList = semestreList;
        notifyDataSetChanged();
    }

    /**
     * Constructeur et déclaration de la classe MyViewHolder
     */
    public class MyViewHolder extends RecyclerView.ViewHolder{
        MyViewHolder(View itemView){super(itemView);}
    }
}
