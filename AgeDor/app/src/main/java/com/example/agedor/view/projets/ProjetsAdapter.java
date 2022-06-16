package com.example.agedor.view.projets;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agedor.R;
import com.example.agedor.data.StorageDettes;
import com.example.agedor.data.StorageProjets;

import java.util.ArrayList;

public class ProjetsAdapter extends RecyclerView.Adapter<ProjetsAdapter.MyViewHolder>{

    public ArrayList<StorageProjets> projets;
    private ProjetsAdapter.RecyclerViewClickListener listener;

    ProjetsAdapter(ArrayList<StorageProjets> projets, ProjetsAdapter.RecyclerViewClickListener listener){
        this.projets = projets;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProjetsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_view_item,parent,false);
        return new ProjetsAdapter.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ProjetsAdapter.MyViewHolder holder, int position) {
        holder.display(projets.get(position));
    }

    @Override
    public int getItemCount() {
        return projets.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView nomprojet;
        private TextView montantDprojet;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nomprojet = (TextView) itemView.findViewById(R.id.nom);
            montantDprojet = (TextView) itemView.findViewById(R.id.montant);
            itemView.setOnClickListener(this);
        }

        void display(StorageProjets projet){

            nomprojet.setText((String) projet.nom+"     ");
            montantDprojet.setText(String.valueOf(projet.montant)+"     ");

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
