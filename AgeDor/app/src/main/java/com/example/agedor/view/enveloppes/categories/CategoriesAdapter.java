package com.example.agedor.view.enveloppes.categories;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agedor.R;
import com.example.agedor.data.StorageCategories;
import com.example.agedor.data.StorageDepenses;
import com.example.agedor.view.enveloppes.depenses.ListeDepensesAdapter;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyViewHolder>{

    public ArrayList<StorageCategories> categories;
    private CategoriesAdapter.RecyclerViewClickListener listener;

    CategoriesAdapter(ArrayList<StorageCategories> categories, CategoriesAdapter.RecyclerViewClickListener listener){
        this.categories = categories;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoriesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_view_item,parent,false);
        return new CategoriesAdapter.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesAdapter.MyViewHolder holder, int position) {
        holder.display(categories.get(position));
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView nomCategorie;
        private TextView montantCategorie;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nomCategorie = (TextView) itemView.findViewById(R.id.nom);
            montantCategorie = (TextView) itemView.findViewById(R.id.montant);
            itemView.setOnClickListener(this);
        }

        void display(StorageCategories categorie){

            nomCategorie.setText((String) categorie.nom+"     ");
            montantCategorie.setText(String.valueOf(categorie.montant)+"     ");

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
