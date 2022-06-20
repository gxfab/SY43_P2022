package com.example.zeroday.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.zeroday.R;
import com.example.zeroday.models.Expense;
import com.example.zeroday.services.ExpenseService;

import java.util.List;

//Fragment permettant d'afficher les dépenses d'une catégorie sour sforme de liste
public class ListExpenseFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_expense, container, false);
        ExpenseService service = new ExpenseService(getActivity());
        List<Expense> expenses = service.getAll();

        GridView gridView = view.findViewById(R.id.item_list);

        gridView.setAdapter(new ListExpenseAdapter(getContext(),expenses));

        return view;
    }
}