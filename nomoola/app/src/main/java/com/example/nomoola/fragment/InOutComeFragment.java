package com.example.nomoola.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nomoola.R;
import com.example.nomoola.adapter.InOutComeAdapter;
import com.example.nomoola.database.entity.SubCategory;
import com.example.nomoola.viewModel.InOutComeViewModel;

public class InOutComeFragment extends Fragment {

    private InOutComeViewModel mInOutViewModel;
    private InOutComeAdapter inOutComeAdapter;
    private SubCategory subCategory;

    private RecyclerView inOutComeRecyclerView;
    //private TextView subCategory_titleName;

    public InOutComeFragment(SubCategory subCategory){
        super();
        this.subCategory = subCategory;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        this.mInOutViewModel = new ViewModelProvider(this).get(InOutComeViewModel.class);
        this.inOutComeAdapter = new InOutComeAdapter(new InOutComeAdapter.InOutComeDiff(), this.getParentFragmentManager());
        View view = inflater.inflate(R.layout.fragment_in_out_come, container, false);

        this.inOutComeRecyclerView = view.findViewById(R.id.inOutCome_recyclerView);
        this.inOutComeRecyclerView.setAdapter(this.inOutComeAdapter);


        mInOutViewModel.getInOutComeOf(subCategory.getM_SUBCAT_ID()).observe(getViewLifecycleOwner(), subCategories -> {
            inOutComeAdapter.submitList(subCategories);
        });

        return view;
    }
}
