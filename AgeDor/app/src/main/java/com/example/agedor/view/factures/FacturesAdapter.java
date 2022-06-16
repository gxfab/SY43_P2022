package com.example.agedor.view.factures;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agedor.R;
import com.example.agedor.data.StorageDettes;
import com.example.agedor.data.StorageFactures;

import java.util.ArrayList;

public class FacturesAdapter extends RecyclerView.Adapter<FacturesAdapter.MyViewHolder>{

    public ArrayList<StorageFactures> factures;
    private FacturesAdapter.RecyclerViewClickListener listener;

    FacturesAdapter(ArrayList<StorageFactures> factures, FacturesAdapter.RecyclerViewClickListener listener){
        this.factures = factures;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FacturesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_view_item,parent,false);
        return new FacturesAdapter.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull FacturesAdapter.MyViewHolder holder, int position) {
        holder.display(factures.get(position));
    }

    @Override
    public int getItemCount() {
        return factures.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView nomFacture;
        private TextView montantFacture;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nomFacture = (TextView) itemView.findViewById(R.id.nom);
            montantFacture = (TextView) itemView.findViewById(R.id.montant);
            itemView.setOnClickListener(this);
        }

        void display(StorageFactures facture){

            nomFacture.setText((String) facture.nom+"     ");
            montantFacture.setText(String.valueOf(facture.montant)+"     ");

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
