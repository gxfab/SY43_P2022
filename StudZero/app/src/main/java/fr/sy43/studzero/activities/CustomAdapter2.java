package fr.sy43.studzero.activities;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fr.sy43.studzero.R;

/**
 * CustomAdapter for the new budget 5 recycler view
 */
public class CustomAdapter2 extends RecyclerView.Adapter<CustomAdapter2.MyViewHolder> {

    private Context context;
    private ArrayList category_name;
    private ArrayList allocated;
    private float available;

    /**
     * constructor : init the class variables with the ones given in argument
     * @param context
     * @param category_name
     * @param allocated
     * @param available
     */
    public CustomAdapter2(Context context, ArrayList category_name, ArrayList allocated, float available){
        this.category_name = category_name;
        this.context = context;
        this.allocated = allocated;
        this.available = available;
    }

    /**
     * Create a view holder
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.new_budget_5_row, parent, false);
        return new MyViewHolder(view);
    }

    /**
     * manage the view holder
     * @param holder
     * @param position
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.category_name.setText(String.valueOf(this.category_name.get(position)));
        holder.allocatedAvailable.setText(String.valueOf(this.allocated.get(position) + " / " + this.available));
        float all = (float)this.allocated.get(position);
        holder.progbar.setProgress((int)(100 * all/this.available));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            /**
             * return to the new budget 3 screen to modify the allocated amount of the category which the user clicked ons
             * @param view
             */
            @Override
            public void onClick(View view) {
                ((New_Budget_5)context).OnCategoryClicked(holder.getAdapterPosition());
            }
        });

    }

    /**
     * return the size of the category_name arraylist
     * @return
     */
    @Override
    public int getItemCount() {
        return category_name.size();
    }

    /**
     * class that contains the textViews and the progress bar
     */
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView category_name;
        TextView allocatedAvailable;
        ProgressBar progbar;
        LinearLayout mainLayout;

        /**
         * init the class variables with their corresponding views
         * @param itemView
         */
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            category_name = itemView.findViewById(R.id.cat_name_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            allocatedAvailable = itemView.findViewById(R.id.textViewAllocatedAvailable);
            progbar = itemView.findViewById(R.id.progressBar);
        }
    }
}
