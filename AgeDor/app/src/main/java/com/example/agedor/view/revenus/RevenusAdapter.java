package com.example.agedor.view.revenus;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agedor.R;
import com.example.agedor.data.StorageDettes;
import com.example.agedor.data.StorageRevenus;

import java.util.ArrayList;

public class RevenusAdapter extends RecyclerView.Adapter<RevenusAdapter.MyViewHolder>{

    public ArrayList<StorageRevenus> revenus;
    private RevenusAdapter.RecyclerViewClickListener listener;

    RevenusAdapter(ArrayList<StorageRevenus> revenus, RevenusAdapter.RecyclerViewClickListener listener){
        this.revenus = revenus;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RevenusAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_view_item,parent,false);
        return new RevenusAdapter.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RevenusAdapter.MyViewHolder holder, int position) {
        holder.display(revenus.get(position));
    }

    @Override
    public int getItemCount() {
        return revenus.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView nomRevenu;
        private TextView montantRevenu;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nomRevenu = (TextView) itemView.findViewById(R.id.nom);
            montantRevenu = (TextView) itemView.findViewById(R.id.montant);
            itemView.setOnClickListener(this);
        }

        void display(StorageRevenus revenu){

            nomRevenu.setText((String) revenu.nom+"     ");
            montantRevenu.setText(String.valueOf(revenu.montant)+"     ");

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
