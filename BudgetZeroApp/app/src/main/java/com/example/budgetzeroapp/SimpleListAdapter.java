package com.example.budgetzeroapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class SimpleListAdapter extends ArrayAdapter<ListItem> {

    private int resourceLayout;
    private Context mContext;

    public SimpleListAdapter(Context context, int resource, List<ListItem> items) {
        super(context, resource, items);
        this.resourceLayout = resource;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);
        }
        ListItem p = getItem(position);

        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.ListItemName);
            if (tt1 != null) tt1.setText(p.name);
        }
        return v;
    }
}
