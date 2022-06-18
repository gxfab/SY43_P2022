package com.sucelloztm.sucelloz.ui.spendings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.sucelloztm.sucelloz.databinding.NegativeSpendingsFragmentBinding;
import com.sucelloztm.sucelloz.databinding.PositiveSpendingsFragmentBinding;
import com.sucelloztm.sucelloz.databinding.SpendingsTabsFragmentBinding;
import com.sucelloztm.sucelloz.ui.dialogs.AddSpendingDialogFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * fragment for the spendings window
 */
public class SpendingsTabsFragment extends Fragment {
    private ViewPager2 viewPager;
    private SpendingsTabsFragmentBinding binding;
    private FragmentStateAdapter pagerAdapter;

    /**
     * on create method
     * @param savedInstanceState saved instance state
     */
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    /**
     * on create view method
     * @param inflater inflater
     * @param container container
     * @param savedInstanceState saved instance state
     * @return view
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        binding=SpendingsTabsFragmentBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        viewPager = binding.pager;
        pagerAdapter= new SpendingsTabsFragmentAdapter(getChildFragmentManager(),getLifecycle());
        viewPager.setAdapter(pagerAdapter);
        TabLayout tabLayout = binding.tabLayout;
        List<String> spendingsNames= new ArrayList<>();
        String positiveSpendings = "Positive";
        String negativeSpendings = "Negative";
        spendingsNames.add(positiveSpendings);
        spendingsNames.add(negativeSpendings);
        new TabLayoutMediator(tabLayout,viewPager,(tab, position) -> tab.setText(spendingsNames.get(position))).attach();
        return root;
    }


    /**
     * on destroy view method
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
