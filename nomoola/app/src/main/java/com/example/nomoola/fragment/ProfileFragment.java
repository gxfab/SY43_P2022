package com.example.nomoola.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.nomoola.R;
import com.example.nomoola.activity.MainActivity;
import com.example.nomoola.viewHolder.ProfileViewHolder;
import com.example.nomoola.viewModel.ProfileViewModel;

public class ProfileFragment extends Fragment {

    private ProfileViewModel mProfileViewModel;
    private ProfileViewHolder mProfileViewHolder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);

        this.mProfileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);


        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        this.mProfileViewHolder = new ProfileViewHolder(view, getParentFragmentManager(), mProfileViewModel);

        mProfileViewModel.getProfile().observe(getViewLifecycleOwner(), profile -> {mProfileViewHolder.bind(profile);});

        return view;
    }

}