package com.example.budgetzeroapp.tool.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.budgetzeroapp.AppContext;
import com.example.budgetzeroapp.R;
import com.example.budgetzeroapp.tool.item.ListItem;

import java.util.List;

public class SimpleListAdapter extends ArrayAdapter<ListItem> {

    protected int resourceLayout;
    public SimpleListAdapter(int resource, List<ListItem> items) {
        super(AppContext.getContext(), resource, items);
        this.resourceLayout = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(AppContext.getContext());
            v = vi.inflate(resourceLayout, null);
        }
        ListItem p = getItem(position);

        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.list_view_name);
            if (tt1 != null) tt1.setText(p.getName());
        }
        return v;
    }
}


