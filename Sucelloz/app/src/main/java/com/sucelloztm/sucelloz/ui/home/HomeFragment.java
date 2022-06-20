package com.sucelloztm.sucelloz.ui.home;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.sucelloztm.sucelloz.R;
import com.sucelloztm.sucelloz.databinding.HomeFragmentBinding;
import com.sucelloztm.sucelloz.ui.dialogs.AddSavingsDialogFragment;
import com.sucelloztm.sucelloz.ui.dialogs.AddSpendingDialogFragment;

/**
 * Class for the home fragment
 */
public class HomeFragment extends Fragment {

    private HomeFragmentBinding binding;

    /**
     * On create view method
     * @param inflater inflater
     * @param container container
     * @param savedInstanceState saved instance state
     * @return view
     */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = HomeFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    /**
     * On view created method
     * @param view view
     * @param savedInstanceState saved instance state
     */
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        binding.myCategoriesButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.action_navigation_home_to_navigation_categories);
            }
        });
        binding.addSpendingButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddSpendingDialogFragment().show(getChildFragmentManager(),AddSpendingDialogFragment.TAG);
            }
        });
        binding.addSavingsButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddSavingsDialogFragment().show(getChildFragmentManager(),AddSpendingDialogFragment.TAG);
            }
        });
    }

    /**
     * On destroy view method
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}