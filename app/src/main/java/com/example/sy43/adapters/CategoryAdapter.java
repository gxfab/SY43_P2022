package com.example.sy43.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.example.sy43.CategoryDetailsActivity;
import com.example.sy43.CreateActivity;
import com.example.sy43.ObjectiveDetailsActivity;
import com.example.sy43.R;
import com.example.sy43.models.Category;

import org.w3c.dom.Text;

import java.util.List;
//https://stackoverflow.com/questions/8166497/custom-adapter-for-list-view
public class CategoryAdapter extends ArrayAdapter<Category> {
    private int resourceLayout;
    private Context mContext;

    public CategoryAdapter(@NonNull Context context, int resource, List<Category> categories) {
        super(context, resource, categories);
        this.resourceLayout = resource;
        this.mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);
        }

        Category category = getItem(position);

        if (category != null) {
            TextView price = v.findViewById(R.id.tvCurrentPrice);
            TextView name = v.findViewById(R.id.tvName);
            ProgressBar progressBar = v.findViewById(R.id.progressBar);
            progressBar.setMax((int) category.getMaxValue());
            progressBar.setProgress((int) category.CurrentValue(), true);

            name.setText(category.getCatName());
            price.setText("$" + category.CurrentValue() + "/$" + category.getMaxValue() );

            CardView card = v.findViewById(R.id.categoryCard);
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Class detailsPage = category.isObjective ? ObjectiveDetailsActivity.class : CategoryDetailsActivity.class;
                    Intent intent = new Intent(v.getContext(), detailsPage);
                    v.getContext().startActivity(intent);
                }
            });
        }

        return v;
    }


}
