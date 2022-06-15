package com.example.sy43;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class OverviewFragmentAdapter extends FragmentStateAdapter {
    public OverviewFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position==1){
            return new chart2();
        }
        return new chart1();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
