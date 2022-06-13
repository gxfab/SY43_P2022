package com.example.budgetzeroapp.tool.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.content.res.AppCompatResources;

import com.example.budgetzeroapp.AppContext;
import com.example.budgetzeroapp.R;
import com.example.budgetzeroapp.tool.item.ProgressBarItem;

import java.util.List;

public class ProgressBarAdapter extends ArrayAdapter<ProgressBarItem> {

        protected int resourceLayout;
        public ProgressBarAdapter(List<ProgressBarItem> items) {
            super(AppContext.getContext(), R.layout.progress_bar_cat, items);
            resourceLayout = R.layout.progress_bar_cat;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View v = convertView;

            if (v == null) {
                LayoutInflater vi;
                vi = LayoutInflater.from(AppContext.getContext());
                v = vi.inflate(resourceLayout, null);
            }
            ProgressBarItem p = getItem(position);
            if (p != null) {
                TextView catName = (TextView) v.findViewById(R.id.category_name);
                if (catName != null) catName.setText(p.getName());
                TextView amount = (TextView) v.findViewById(R.id.amount);
                if (amount != null) {
                    String mess = p.getTotal() + " €";
                    amount.setText(mess);
                }
                SeekBar seekbar = v.findViewById(R.id.seekbar);
                if (seekbar != null) {
                    seekbar.setThumb(AppCompatResources.getDrawable(AppContext.getContext(), p.getDrawable()));
                    seekbar.setProgress((int) p.getPercent());
                    seekbar.setEnabled(false);
                }
            }
            return v;
        }
}
