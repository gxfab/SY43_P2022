package com.example.agedor.view.dettes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agedor.R;
import com.example.agedor.data.StorageCategories;
import com.example.agedor.data.StorageDettes;

import java.util.ArrayList;

public class DettesAdapter extends RecyclerView.Adapter<DettesAdapter.MyViewHolder>{

    public ArrayList<StorageDettes> dettes;
    private DettesAdapter.RecyclerViewClickListener listener;

    DettesAdapter(ArrayList<StorageDettes> dettes, DettesAdapter.RecyclerViewClickListener listener){
        this.dettes = dettes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public DettesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_view_item,parent,false);
        return new DettesAdapter.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull DettesAdapter.MyViewHolder holder, int position) {
        holder.display(dettes.get(position));
    }

    @Override
    public int getItemCount() {
        return dettes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView nomDette;
        private TextView montantDette;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nomDette = (TextView) itemView.findViewById(R.id.nom);
            montantDette = (TextView) itemView.findViewById(R.id.montant);
            itemView.setOnClickListener(this);
        }

        void display(StorageDettes dette){

            nomDette.setText((String) dette.nom+"     ");
            montantDette.setText(String.valueOf(dette.montant)+"     ");

        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }
}
