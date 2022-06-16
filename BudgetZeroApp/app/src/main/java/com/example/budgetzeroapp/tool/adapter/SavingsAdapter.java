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
import com.example.budgetzeroapp.tool.item.CategoryItem;
import com.example.budgetzeroapp.tool.item.SavingsItem;

import java.util.List;

public class SavingsAdapter extends ArrayAdapter<SavingsItem> {

    protected int resourceLayout;
    public SavingsAdapter(List<SavingsItem> items) {
        super(AppContext.getContext(), 0    , items);
        resourceLayout = R.layout.progress_bar_savings_debt;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(AppContext.getContext());
            v = vi.inflate(resourceLayout, null);
        }
        SavingsItem p = getItem(position);
        if (p != null) {
            TextView catName = (TextView) v.findViewById(R.id.category_name);
            if (catName != null) catName.setText(p.getName());
            TextView amount = (TextView) v.findViewById(R.id.amount);
            if (amount != null) {
                String mess = p.getObjective() + " €";
                amount.setText(mess);
            }
            SeekBar seekbar = v.findViewById(R.id.seekbar);
            if (seekbar != null) {
                seekbar.setThumb(AppCompatResources.getDrawable(AppContext.getContext(), R.drawable.ic_default_thumb));
                seekbar.setProgress((int) p.getProgress());
                seekbar.setEnabled(false);
            }
        }
        return v;
    }
}
