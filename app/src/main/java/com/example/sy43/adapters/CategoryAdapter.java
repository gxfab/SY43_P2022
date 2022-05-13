package com.example.sy43.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
            name.setText(category.getCatName());
            price.setText(category.CurrentValue() + "$");
        }

        return v;    }


}
