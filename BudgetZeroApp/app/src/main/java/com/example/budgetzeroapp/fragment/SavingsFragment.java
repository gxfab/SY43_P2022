package com.example.budgetzeroapp.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.budgetzeroapp.R;
import com.example.budgetzeroapp.tool.ToolBar;
import com.example.budgetzeroapp.tool.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;


public class SavingsFragment extends DataBaseFragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private boolean defaultTab;

    public SavingsFragment() {
        super();
        defaultTab = true;
    }

    public SavingsFragment(boolean tab){
        super();
        defaultTab = tab;
    }

    public static SavingsFragment newInstance(String param1, String param2) {
        return new SavingsFragment();
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
        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.viewPager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new SavingsTabFragment(true), "Savings");
        adapter.addFragment(new SavingsTabFragment(false), "Debts");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        if(!defaultTab) selectPage(2);

        ToolBar.getInstance().initToolBar(view, R.id.toolbar_savings);
        /**Navigation
        Toolbar toolbar = view.findViewById(R.id.toolbar_savings);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                NavController navController = Navigation.findNavController(view);
                switch(item.getItemId()){
                    case R.id.next_day:
                        //change day
                        break;
                }
                return true;
            }
        });**/


    }

    public void selectPage(int pageIndex){
        tabLayout.setScrollPosition(pageIndex,0f,true);
        viewPager.setCurrentItem(pageIndex);
    }
}