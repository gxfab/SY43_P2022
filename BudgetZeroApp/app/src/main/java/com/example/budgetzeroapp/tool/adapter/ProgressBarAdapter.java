package com.example.budgetzeroapp.tool.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.budgetzeroapp.R;
import com.example.budgetzeroapp.tool.item.ListItem;
import com.example.budgetzeroapp.tool.item.ProgressBarItem;

import java.util.List;

public class ProgressBarAdapter extends ArrayAdapter<ProgressBarItem> {

        protected int resourceLayout;
        protected Context mContext;

        public ProgressBarAdapter(Context context, int resource, List<ProgressBarItem> items) {
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
                if (tt1 != null) tt1.setText(p.getName());
            }
            return v;
        }
}
