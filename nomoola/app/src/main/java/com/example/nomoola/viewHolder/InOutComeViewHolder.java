package com.example.nomoola.viewHolder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nomoola.R;
import com.example.nomoola.database.converter.Converters;
import com.example.nomoola.database.entity.InOutCome;

public class InOutComeViewHolder extends RecyclerView.ViewHolder {

    private TextView dateView, nameView, amountView;
    private ImageButton editButton;

    private FragmentManager fragmentManager;
    private InOutCome inOutCome;

    public InOutComeViewHolder(@NonNull View view, FragmentManager fragmentManager) {
        super(view);
        this.fragmentManager = fragmentManager;

        this.nameView = view.findViewById(R.id.item_inoutcome_name);
        this.dateView = view.findViewById(R.id.item_inoutcome_date);
        this.amountView = view.findViewById(R.id.item_inoutcome_amount);
        this.editButton = view.findViewById(R.id.item_inoutcome_editButton);

    }

    public void bind(InOutCome inOutCome){
        this.inOutCome = inOutCome;
        this.nameView.setText(this.inOutCome.getM_INOUTCOME_NAME());
        this.dateView.setText(Converters
                .convertFromDate(this.inOutCome.getM_INOUTCOME_DATE()));
        this.amountView.setText(this.inOutCome.getM_INOUTCOME_AMOUNT() + "€");
    }

    public static InOutComeViewHolder create(ViewGroup parent, FragmentManager fragmentManager){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_in_out_come, parent, false);
        return new InOutComeViewHolder(view, fragmentManager);
    }
}
