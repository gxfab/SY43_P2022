package com.sy43.savecur;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;

import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class CategoriesGridAdapter extends BaseAdapter {

    private List<Category> categories;
    private LayoutInflater layoutInflater;
    private Context context;

    public CategoriesGridAdapter(Context aContext, List<Category> categories) {
        this.context = aContext;
        this.categories = categories;
        this.layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.category_item_layout, null);
            holder = new ViewHolder();
            holder.categoryNameView = (MaterialTextView) convertView.findViewById(R.id.category_name);
            holder.moneySpentView = (MaterialTextView) convertView.findViewById(R.id.money_spent);
            holder.moneyLimitView = (MaterialTextView) convertView.findViewById(R.id.money_limit);
            holder.progressBar = (ProgressBar) convertView.findViewById(R.id.category_progress_bar);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Category category = this.categories.get(position);
        holder.categoryNameView.setText(category.getName());
        holder.moneyLimitView.setText(String.valueOf(category.getMoneyLimit()) + "€");
        holder.moneySpentView.setText(String.valueOf(category.getMoneySpent()) + "€");
        holder.progressBar.setProgress(category.getProgress());

        return convertView;
    }

    static class ViewHolder {
        MaterialTextView categoryNameView;
        MaterialTextView moneySpentView;
        MaterialTextView moneyLimitView;
        ProgressBar progressBar;
    }
}
