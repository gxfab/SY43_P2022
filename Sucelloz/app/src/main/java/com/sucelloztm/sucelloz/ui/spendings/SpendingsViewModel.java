package com.sucelloztm.sucelloz.ui.spendings;

import android.app.Application;
import android.graphics.Color;
import android.graphics.Typeface;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.sucelloztm.sucelloz.models.InfrequentExpensesAndIncome;
import com.sucelloztm.sucelloz.models.SubCategories;
import com.sucelloztm.sucelloz.repositories.InfrequentExpensesAndIncomeRepository;
import com.sucelloztm.sucelloz.repositories.SubCategoriesRepository;

import java.util.ArrayList;
import java.util.List;

public class SpendingsViewModel extends AndroidViewModel {
    private InfrequentExpensesAndIncomeRepository spendingsRepository;
    private SubCategoriesRepository subCategoriesRepository;

    private LiveData<List<InfrequentExpensesAndIncome>> currentPositiveSpendings;

    private LiveData<List<InfrequentExpensesAndIncome>> currentNegativeSpendings;

    public SpendingsViewModel(@NonNull Application application) {
        super(application);
        this.spendingsRepository = new InfrequentExpensesAndIncomeRepository(application);
        this.subCategoriesRepository = new SubCategoriesRepository(application);
    }

    public LiveData<List<InfrequentExpensesAndIncome>> getAllPositiveSpendings(){
        if (this.currentPositiveSpendings == null){
            this.currentPositiveSpendings = spendingsRepository.getAllPositiveInfrequent();
        }
        return this.currentPositiveSpendings;
    }

    public LiveData<List<InfrequentExpensesAndIncome>> getAllNegativeSpendings(){
        if(this.currentNegativeSpendings==null){
            this.currentNegativeSpendings = spendingsRepository.getAllNegativeInfrequent();
        }
        return  this.currentNegativeSpendings;
    }

    public SubCategories getSubCategoryByName(String nameOfSubCategory){
        return this.subCategoriesRepository.getSubCategoryWithName(nameOfSubCategory);
    }

    public String getSubCategoryNameWithId(long idOfSubCategory){
        return subCategoriesRepository.getSubCategoryNameWithId(idOfSubCategory);
    }

    public PieData generatePieData(Typeface tf) {

        int count = 4;

        ArrayList<PieEntry> entries1 = new ArrayList<>();

        for(int i = 0; i < count; i++) {
            entries1.add(new PieEntry((float) ((Math.random() * 60) + 40), "Quarter " + (i+1)));
        }

        PieDataSet ds1 = new PieDataSet(entries1, "Quarterly Revenues 2015");
        ds1.setColors(ColorTemplate.VORDIPLOM_COLORS);
        ds1.setSliceSpace(2f);
        ds1.setValueTextColor(Color.WHITE);
        ds1.setValueTextSize(12f);

        PieData d = new PieData(ds1);
        d.setValueTypeface(tf);

        return d;
    }
}
