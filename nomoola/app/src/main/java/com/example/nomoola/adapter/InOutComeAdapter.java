package com.example.nomoola.adapter;

import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import com.example.nomoola.database.entity.InOutCome;
import com.example.nomoola.viewHolder.InOutComeViewHolder;

public class InOutComeAdapter extends ListAdapter<InOutCome, InOutComeViewHolder> {

    private FragmentManager fragmentManager;

    public InOutComeAdapter(@NonNull DiffUtil.ItemCallback<InOutCome> diffCallback, FragmentManager fragmentManager) {
        super(diffCallback);
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public InOutComeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("CREATION", "onCreateViewHolder from " + this.getClass().toString() + " started");

        Log.d("CREATION", "onCreateViewHolder from " + this.getClass().toString() + " finished");
        return InOutComeViewHolder.create(parent, this.fragmentManager);
    }

    @Override
    public void onBindViewHolder(@NonNull InOutComeViewHolder holder, int position) {
        InOutCome currentInOutCome = getItem(position);
        holder.bind(currentInOutCome);
    }

    public static class InOutComeDiff extends DiffUtil.ItemCallback<InOutCome>{

        @Override
        public boolean areItemsTheSame(@NonNull InOutCome oldItem, @NonNull InOutCome newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull InOutCome oldItem, @NonNull InOutCome newItem) {
            return oldItem.getM_INOUTCOME_ID() == newItem.getM_INOUTCOME_ID();
        }
    }
}
