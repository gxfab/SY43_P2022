package com.example.sy43;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProjectsDisplayAdapter extends RecyclerView.Adapter<ProjectsDisplayAdapter.ProjectsViewHolder> {

    private List<String> titles, costs, priorites, allocations;

    public ProjectsDisplayAdapter(List<String> titles, List<String> costs, List<String> priorites, List<String> allocations) {
        this.titles = titles;
        this.costs = costs;
        this.priorites = priorites;
        this.allocations = allocations;
    }

    @NonNull
    @Override
    public ProjectsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_project_item , parent , false);
        return new ProjectsDisplayAdapter.ProjectsViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ProjectsViewHolder holder, int position) {
        holder.t1.setText(titles.get(position));
        holder.t2.setText(costs.get(position));
        holder.t3.setText(priorites.get(position));
        holder.t4.setText(allocations.get(position));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ProjectsViewHolder extends RecyclerView.ViewHolder{

        private TextView t1, t2, t3, t4;

        public ProjectsViewHolder(@NonNull View itemView) {
            super(itemView);

            t1 = itemView.findViewById(R.id.display_project_item_name);
            t2 = itemView.findViewById(R.id.display_project_item_cost);
            t3 = itemView.findViewById(R.id.display_project_item_priority);
            t4 = itemView.findViewById(R.id.display_project_item_allocation);
        }
    }
}
