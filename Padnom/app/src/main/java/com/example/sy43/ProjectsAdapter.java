package com.example.sy43;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.ProjectViewHolder> {

    private List<String> titles, costs, priorites;

    public ProjectsAdapter(List<String> titles, List<String> costs, List<String> priorites) {
        this.titles = titles;
        this.costs = costs;
        this.priorites = priorites;
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_item , parent , false);
        return new ProjectsAdapter.ProjectViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        holder.t1.setText(titles.get(position));
        holder.t2.setText(costs.get(position));
        holder.t3.setText(priorites.get(position));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ProjectViewHolder extends RecyclerView.ViewHolder{

        private TextView t1, t2, t3;
        public ProjectViewHolder(@NonNull View itemView) {
            super(itemView);

            t1 = itemView.findViewById(R.id.project_item_name);
            t2 = itemView.findViewById(R.id.project_item_cost);
            t3 = itemView.findViewById(R.id.project_item_priority);
        }
    }
}
