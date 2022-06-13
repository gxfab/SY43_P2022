package com.example.budgetzeroapp.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.budgetzeroapp.R;
import com.example.budgetzeroapp.SavingsDebtsTabFragment;
import com.example.budgetzeroapp.SavingstTabFragment;
import com.example.budgetzeroapp.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;


public class SavingsFragment extends DataBaseFragment {
    TabLayout tablayout;
    ViewPager viewPager;
    public SavingsFragment() {
        super();
    }

    public static SavingsFragment newInstance(String param1, String param2) {
        SavingsFragment fragment = new SavingsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_savings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addFragment(view);
    }

    private void addFragment(View view)
    {
        tablayout = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.viewPager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new SavingstTabFragment(), "Savings");
        adapter.addFragment(new SavingsDebtsTabFragment(), "Debts");
        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);
    }
}