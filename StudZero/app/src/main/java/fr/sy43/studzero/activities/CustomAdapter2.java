package fr.sy43.studzero.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import fr.sy43.studzero.New_Budget_1;
import fr.sy43.studzero.New_Budget_5;
import fr.sy43.studzero.New_Budget_3;
import fr.sy43.studzero.R;

public class CustomAdapter2 extends RecyclerView.Adapter<CustomAdapter2.MyViewHolder> {

    private Context context;
    private ArrayList category_name;
    private ArrayList allocated;
    private float available;

    public CustomAdapter2(Context context, ArrayList category_name, ArrayList allocated, float available){
        this.category_name = category_name;
        this.context = context;
        this.allocated = allocated;
        this.available = available;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.new_budget_5_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.category_name.setText(String.valueOf(this.category_name.get(position)));
        holder.allocatedAvailable.setText(String.valueOf(this.allocated.get(position) + " / " + this.available));
        float all = (float)this.allocated.get(position);
        holder.progbar.setProgress((int)(100 * all/this.available));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((New_Budget_5)context).OnCategoryClicked(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return category_name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView category_name;
        TextView allocatedAvailable;
        ProgressBar progbar;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            category_name = itemView.findViewById(R.id.cat_name_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            allocatedAvailable = itemView.findViewById(R.id.textViewAllocatedAvailable);
            progbar = itemView.findViewById(R.id.progressBar);
        }
    }
}
