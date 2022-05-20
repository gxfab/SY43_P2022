package fr.sy43.studzero.activities;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fr.sy43.studzero.R;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList category_name;
    public ArrayList<Integer> state = new ArrayList<Integer>();

    public CustomAdapter(Context context, ArrayList category_name){
        this.category_name = category_name;
        this.context = context;
        for(int i = 0; i < category_name.size(); i++){
            state.add(i, 1);//set a 1 par défaut
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.new_budget_2_my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.category_name.setText(String.valueOf(this.category_name.get(position)));
        holder.button.setChecked(true);

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.button.isChecked()){
                    state.set(holder.getAdapterPosition(), 1);
                }
                else{
                    state.set(holder.getAdapterPosition(), 0);
                }
                Log.i("OnClick", String.valueOf(category_name.get(holder.getAdapterPosition())));
                Log.i("state", String.valueOf(state.get(holder.getAdapterPosition())));
            }
        });
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //change le state du button / switch
                if(holder.button.isChecked()){
                    holder.button.setChecked(false);
                    state.set(holder.getAdapterPosition(), 0);
                }
                else{
                    holder.button.setChecked(true);
                    state.set(holder.getAdapterPosition(), 1);
                }
                Log.i("OnClick", String.valueOf(category_name.get(holder.getAdapterPosition())));
                Log.i("state", String.valueOf(state.get(holder.getAdapterPosition())));
            }
        });

    }

    @Override
    public int getItemCount() {
        return category_name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView category_name;
        CompoundButton button;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            category_name = itemView.findViewById(R.id.cat_name_txt);
            button = itemView.findViewById(R.id.switch1);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
