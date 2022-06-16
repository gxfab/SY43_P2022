package com.example.agedor.view.enveloppes;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agedor.R;
import com.example.agedor.data.StorageDepenses;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListeDepensesAdapter extends RecyclerView.Adapter<ListeDepensesAdapter.MyViewHolder> {

    public ArrayList<StorageDepenses> depenses;
    private RecyclerViewClickListener listener;

    ListeDepensesAdapter(ArrayList<StorageDepenses> depenses, RecyclerViewClickListener listener){
        this.depenses = depenses;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.liste_depenses_item,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.display(depenses.get(position));
    }

    @Override
    public int getItemCount() {
        return depenses.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView nomDepense;
        private TextView dateDepense;
        private TextView categorieDepense;
        private TextView montantDepense;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nomDepense = (TextView) itemView.findViewById(R.id.nom_depense);
            dateDepense = (TextView) itemView.findViewById(R.id.date_depense);
            categorieDepense = (TextView) itemView.findViewById(R.id.categorie_depense);
            montantDepense = (TextView) itemView.findViewById(R.id.montant_depense);
            itemView.setOnClickListener(this);
        }

        void display(StorageDepenses depense){

            nomDepense.setText((String) depense.nom+"     ");
            categorieDepense.setText(String.valueOf(depense.categorie)+"     ");
            dateDepense.setText((String) depense.date_depense+"     ");
            montantDepense.setText(String.valueOf(depense.montant)+"     ");

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
