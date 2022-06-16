package com.sucelloztm.sucelloz.ui.subZeroBudget;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.sucelloztm.sucelloz.databinding.SubCategoriesFragmentBinding;
import com.sucelloztm.sucelloz.models.SubCategories;
import com.sucelloztm.sucelloz.ui.subcategories.SubCategoriesViewModel;

import java.util.List;

public class SubZeroBudgetFragment extends Fragment {

    private SubCategoriesFragmentBinding binding;
    private SubCategoriesViewModel subCategoriesViewModel;
    private List<SubCategories> currentSpendingsList;
    private RecyclerView recyclerView;
    private  int itemIndex;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
